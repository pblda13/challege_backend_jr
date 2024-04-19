package com.challege.backend_jr.service;

import com.challege.backend_jr.entity.Teacher;
import com.challege.backend_jr.entity.TrainingForm;
import com.challege.backend_jr.exception.TeacherNotFoundException;
import com.challege.backend_jr.exception.TrainigFormNotFoundException;
import com.challege.backend_jr.producer.KafkaProducer;
import com.challege.backend_jr.repositories.TeacherRepository;
import com.challege.backend_jr.repositories.TrainingFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingFormService {

    @Autowired
    private TrainingFormRepository trainingFormRepository;
    @Autowired
    private KafkaProducer kafkaProducer;

    public TrainingForm createTrainigForm(TrainingForm trainingForm) {
        kafkaProducer.sendMessage("TrainigForm registered successfully");
        return trainingFormRepository.save(trainingForm);
    }

    public List<TrainingForm> getAllTrainigForm() {
        List<TrainingForm> trainingFormList = trainingFormRepository.findAll();
        return trainingFormList;
    }

    public TrainingForm updateTrainigForm(Long id, TrainingForm trainingForm) {
        TrainingForm trainingForm1 = trainingFormRepository.findById(id)
                .orElseThrow(() -> new TrainigFormNotFoundException("TrainigForm not found with id: " + id));

        trainingForm1.setClient(trainingForm.getClient());
        trainingForm1.setTeacher(trainingForm.getTeacher());
        trainingForm1.setSeries(trainingForm.getSeries());
        trainingForm1.setDateCreation(trainingForm.getDateCreation());
        trainingForm1.setExpirationDate(trainingForm.getDateCreation());

        return trainingFormRepository.save(trainingForm1);
    }

    public void trainigFormDelete(Long id) {
        TrainingForm trainingForm = trainingFormRepository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException("TrainigForm not found with id: " + id));
        trainingFormRepository.delete(trainingForm);
    }
}


