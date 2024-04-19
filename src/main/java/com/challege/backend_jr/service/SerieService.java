package com.challege.backend_jr.service;

import com.challege.backend_jr.entity.Serie;
import com.challege.backend_jr.entity.Serie;
import com.challege.backend_jr.exception.SerieNotFoundException;
import com.challege.backend_jr.exception.TeacherNotFoundException;
import com.challege.backend_jr.exception.TrainigFormNotFoundException;
import com.challege.backend_jr.producer.KafkaProducer;
import com.challege.backend_jr.repositories.SerieRepository;
import com.challege.backend_jr.repositories.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SerieService {

    @Autowired
    private SerieRepository serieRepository;

    @Autowired
    private KafkaProducer kafkaProducer;


    public Serie createSerie(Serie serie) {
        kafkaProducer.sendMessage("Serie registered successfully");
        return serieRepository.save(serie);
    }

    public List<Serie> getAllSerie() {
        List<Serie> serieList = serieRepository.findAll();
        return serieList;
    }

    public Serie updateserie(Long id, Serie serie) {
        Serie serie1 = serieRepository.findById(id)
                .orElseThrow(() -> new SerieNotFoundException("serie not found with id: " + id));

        serie1.setTrainingForm(serie.getTrainingForm());
        serie1.setExercise(serie.getExercise());
        serie1.setExecutionOrder(serie.getExecutionOrder());

        return serieRepository.save(serie1);
    }

    public void serieDelete(Long id) {
        Serie Serie = serieRepository.findById(id)
                .orElseThrow(() -> new SerieNotFoundException("serie not found with id: " + id));
        serieRepository.delete(Serie);
    }
}


