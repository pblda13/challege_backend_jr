package com.challege.backend_jr.service;

import com.challege.backend_jr.entity.Client;
import com.challege.backend_jr.entity.Exercise;
import com.challege.backend_jr.exception.ClientNotFoundException;
import com.challege.backend_jr.exception.ExerciseNotFoundException;
import com.challege.backend_jr.producer.KafkaProducer;
import com.challege.backend_jr.repositories.ClientRepository;
import com.challege.backend_jr.repositories.ExerciseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private KafkaProducer kafkaProducer;

    @Transactional
    public Exercise createExercise(Exercise exercise) {
        Exercise savedExercise = exerciseRepository.save(exercise);

        CompletableFuture.runAsync(() -> kafkaProducer.sendMessage("Exercise registered successfully"));
        return savedExercise;
    }

    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    @Transactional
    public Exercise updateExercise(Long id, Exercise exercise) {
        Exercise existingExercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new ExerciseNotFoundException("Exercise not found with id: " + id));

        existingExercise.setName(exercise.getName());
        existingExercise.setDevice(exercise.getDevice());
        existingExercise.setSerie(exercise.getSerie());

        return exerciseRepository.save(existingExercise);
    }

    @Transactional
    public void deleteExercise(Long id) {
        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new ExerciseNotFoundException("Exercise not found with id: " + id));
        exerciseRepository.delete(exercise);
    }
}
