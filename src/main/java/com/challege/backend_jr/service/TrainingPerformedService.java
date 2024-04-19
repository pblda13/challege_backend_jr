package com.challege.backend_jr.service;

import com.challege.backend_jr.entity.TrainingPerformed;
import com.challege.backend_jr.exception.TrainingPerformedNotFoundException;
import com.challege.backend_jr.producer.KafkaProducer;
import com.challege.backend_jr.repositories.TrainingPerformedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingPerformedService {

    @Autowired
    private TrainingPerformedRepository trainingPerformedRepository;
    @Autowired
    private KafkaProducer kafkaProducer;


    public TrainingPerformed createTrainingPerformed(TrainingPerformed trainingPerformed) {
        kafkaProducer.sendMessage("TrainingPerformed registered successfully");
        return trainingPerformedRepository.save(trainingPerformed);
    }

    public List<TrainingPerformed> getAllTrainingPerformed() {
        List<TrainingPerformed> teacherList = trainingPerformedRepository.findAll();
        return teacherList;
    }

    public TrainingPerformed updateTrainingPerformed(Long id, TrainingPerformed trainingPerfor) {
        TrainingPerformed trainingPerfor1 = trainingPerformedRepository.findById(id)
                .orElseThrow(() -> new TrainingPerformedNotFoundException("TrainingPerformed not found with id: " + id));

        trainingPerfor1.setSerie(trainingPerfor.getSerie());
        trainingPerfor1.setDateRealization(trainingPerfor.getDateRealization());

        return trainingPerformedRepository.save(trainingPerfor1);
    }

    public void trainingPerformedDelete(Long id) {
        TrainingPerformed teacher = trainingPerformedRepository.findById(id)
                .orElseThrow(() -> new TrainingPerformedNotFoundException("TrainingPerformed not found with id: " + id));
        trainingPerformedRepository.delete(teacher);
    }
}


