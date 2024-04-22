package com.challege.backend_jr.service;

import com.challege.backend_jr.dto.ClientRequestDto;
import com.challege.backend_jr.entity.Client;
import com.challege.backend_jr.entity.Exercise;
import com.challege.backend_jr.entity.Teacher;
import com.challege.backend_jr.entity.TrainingForm;
import com.challege.backend_jr.producer.KafkaProducer;
import com.challege.backend_jr.repositories.ExerciseRepository;
import com.challege.backend_jr.repositories.TrainingFormRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class TrainingFormService {

    @Autowired
    private TrainingFormRepository trainingFormRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private ClientService clientService;

    @Autowired
    private TeacherService teacherService;

    @Transactional
    public TrainingForm createTrainingForm(TrainingForm trainingForm) {
        Optional<Client> client = clientService.getClientById(trainingForm.getClient().getId());
        Optional<Teacher> teacher = teacherService.getTeacherById(trainingForm.getTeacher().getId());

        if (!client.get().isContractActive()) {
            throw new IllegalArgumentException("The client does not have an active contract.");
        }

        if (trainingForm.getExercises() == null || trainingForm.getExercises().isEmpty()) {
            throw new IllegalArgumentException("The training form must contain at least one exercise.");
        }

        trainingForm.setDateCreation(LocalDate.now());
        trainingForm.setExpirationDate(LocalDate.now().plusMonths(3));

        CompletableFuture.runAsync(() -> kafkaProducer.sendMessage("TrainingForm registered successfully"));

        return trainingFormRepository.save(trainingForm);
    }

    public TrainingForm includeExercisesInTrainingForm(Long id, List<Exercise> exercises) {
        TrainingForm form = trainingFormRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Training form not found with id: " + id));

        form.getExercises().addAll(exercises);

        return trainingFormRepository.save(form);
    }

    public TrainingForm includeExerciseInTrainingForm(Long id, Exercise exercise) {
        TrainingForm form = trainingFormRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Training form not found with id: " + id));

        form.getExercises().add(exercise);

        return trainingFormRepository.save(form);
    }

    public TrainingForm getTrainingFormById(Long id) {
        return trainingFormRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Training form not found with id: " + id));
    }

    public TrainingForm getTrainingFormByIdAndClient(Long id, ClientRequestDto client) throws AccessDeniedException {
        TrainingForm form = getTrainingFormById(id);

        if (!form.getClient().getName().equals(client.name()) || !form.getClient().getRegistration().equals(client.Registration())) {
            throw new AccessDeniedException("You do not have permission to access this training form.");
        }

        return form;
    }

    public TrainingForm updateTrainingForm(Long id, TrainingForm trainingForm) {
        TrainingForm form = getTrainingFormById(id);

        form.setExercises(trainingForm.getExercises());

        return trainingFormRepository.save(form);
    }

    @Transactional
    public TrainingForm updateTrainingFormByClient(ClientRequestDto clientDto, Long id, TrainingForm trainingUpdatedForm) throws AccessDeniedException {
        TrainingForm existingForm = getTrainingFormByIdAndClient(id, clientDto);

        for (Exercise exercise : trainingUpdatedForm.getExercises()) {
            Exercise existingExercise = exerciseRepository.findById(exercise.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Exercise not found with ID: " + exercise.getId()));

            if (!existingForm.getExercises().contains(existingExercise)) {
                throw new IllegalArgumentException("The exercise does not belong to this training form.");
            }

            existingExercise.setCharge(exercise.getCharge());
        }

        return trainingFormRepository.save(existingForm);
    }

    @Transactional
    public void deleteTrainingForm(Long id) {
        TrainingForm trainingForm = getTrainingFormById(id);
        trainingFormRepository.delete(trainingForm);
    }

    public List<TrainingForm> getTrainingFormByClientName(String name) {
        return trainingFormRepository.findByClient_Name(name);
    }

    public List<TrainingForm> getTrainingFormByClientRegistration(String registration) {
        return trainingFormRepository.findByClient_Registration(registration);
    }

    public List<TrainingForm> getTrainingFormByClientNameAndRegistration(String name, String registration) {
        return trainingFormRepository.findByClient_NameAndClient_Registration(name, registration);
    }

}
