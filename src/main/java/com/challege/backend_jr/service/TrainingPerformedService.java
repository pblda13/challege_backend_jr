package com.challege.backend_jr.service;

import com.challege.backend_jr.entity.TrainingPerformed;
import com.challege.backend_jr.exception.TrainingPerformedNotFoundException;
import com.challege.backend_jr.producer.KafkaProducer;
import com.challege.backend_jr.repositories.TrainingPerformedRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class TrainingPerformedService {

    @Autowired
    private TrainingPerformedRepository trainingPerformedRepository;

    @Autowired
    private KafkaProducer kafkaProducer;

    @Transactional
    public TrainingPerformed createTrainingPerformed(TrainingPerformed trainingPerformed) {
        TrainingPerformed savedTrainingPerformed = trainingPerformedRepository.save(trainingPerformed);

        CompletableFuture.runAsync(() -> kafkaProducer.sendMessage("TrainingPerformed registered successfully"));
        return savedTrainingPerformed;
    }

    public List<TrainingPerformed> getAllTrainingPerformed() {
        return trainingPerformedRepository.findAll();
    }

    @Transactional
    public TrainingPerformed updateTrainingPerformed(Long id, TrainingPerformed trainingPerformed) {
        TrainingPerformed existingTrainingPerformed = trainingPerformedRepository.findById(id)
                .orElseThrow(() -> new TrainingPerformedNotFoundException("TrainingPerformed not found with id: " + id));

        existingTrainingPerformed.setSerie(trainingPerformed.getSerie());
        existingTrainingPerformed.setDateRealization(trainingPerformed.getDateRealization());

        return trainingPerformedRepository.save(existingTrainingPerformed);
    }

    @Transactional
    public void deleteTrainingPerformed(Long id) {
        TrainingPerformed trainingPerformed = trainingPerformedRepository.findById(id)
                .orElseThrow(() -> new TrainingPerformedNotFoundException("TrainingPerformed not found with id: " + id));
        trainingPerformedRepository.delete(trainingPerformed);
    }
}


