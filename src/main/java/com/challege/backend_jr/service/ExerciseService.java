package com.challege.backend_jr.service;

import com.challege.backend_jr.entity.Client;
import com.challege.backend_jr.entity.Exercise;
import com.challege.backend_jr.exception.ClientNotFoundException;
import com.challege.backend_jr.exception.ExerciseNotFoundException;
import com.challege.backend_jr.producer.KafkaProducer;
import com.challege.backend_jr.repositories.ClientRepository;
import com.challege.backend_jr.repositories.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private KafkaProducer kafkaProducer;


    public Exercise createExercise(Exercise exercise) {
        kafkaProducer.sendMessage("Exercise registered successfully");
        return exerciseRepository.save(exercise);
    }

    public List<Exercise> getAllExercise() {
        List<Exercise> exerciseList = exerciseRepository.findAll();
        return exerciseList;
    }

    public Exercise updateExercise(Long id, Exercise exercise) {
        Exercise exercise1 = exerciseRepository.findById(id)
                .orElseThrow(() -> new ExerciseNotFoundException("Exercise not found with id: " + id));

        exercise1.setName(exercise.getName());
        exercise1.setDevice(exercise.getDevice());

        return exerciseRepository.save(exercise);
    }

    public void DeleteExercise(Long id) {
        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new ExerciseNotFoundException("Exercise not found with id: " + id));
        exerciseRepository.delete(exercise);
    }
}
