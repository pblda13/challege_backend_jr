package com.challege.backend_jr.service;

import com.challege.backend_jr.entity.TrainingForm;
import com.challege.backend_jr.exception.TrainingFormNotFoundException;
import com.challege.backend_jr.producer.KafkaProducer;
import com.challege.backend_jr.repositories.TrainingFormRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class TrainingFormService {

    @Autowired
    private TrainingFormRepository trainingFormRepository;

    @Autowired
    private KafkaProducer kafkaProducer;

    @Transactional
    public TrainingForm createTrainingForm(TrainingForm trainingForm) {
        TrainingForm savedTrainingForm = trainingFormRepository.save(trainingForm);

        CompletableFuture.runAsync(() -> kafkaProducer.sendMessage("TrainingForm registered successfully"));
        return savedTrainingForm;
    }

    public List<TrainingForm> getAllTrainingForms() {
        return trainingFormRepository.findAll();
    }

    @Transactional
    public TrainingForm updateTrainingForm(Long id, TrainingForm trainingForm) {
        TrainingForm existingTrainingForm = trainingFormRepository.findById(id)
                .orElseThrow(() -> new TrainingFormNotFoundException("TrainingForm not found with id: " + id));

        existingTrainingForm.setClient(trainingForm.getClient());
        existingTrainingForm.setTeacher(trainingForm.getTeacher());
        existingTrainingForm.setSeries(trainingForm.getSeries());
        existingTrainingForm.setDateCreation(trainingForm.getDateCreation());
        existingTrainingForm.setExpirationDate(trainingForm.getExpirationDate());

        return trainingFormRepository.save(existingTrainingForm);
    }

    @Transactional
    public void deleteTrainingForm(Long id) {
        TrainingForm trainingForm = trainingFormRepository.findById(id)
                .orElseThrow(() -> new TrainingFormNotFoundException("TrainingForm not found with id: " + id));
        trainingFormRepository.delete(trainingForm);
    }
}



