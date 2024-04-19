package com.challege.backend_jr.service;

import com.challege.backend_jr.entity.Serie;
import com.challege.backend_jr.exception.SerieNotFoundException;
import com.challege.backend_jr.producer.KafkaProducer;
import com.challege.backend_jr.repositories.SerieRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class SerieService {

    @Autowired
    private SerieRepository serieRepository;

    @Autowired
    private KafkaProducer kafkaProducer;

    @Transactional
    public Serie createSerie(Serie serie) {
        Serie savedSerie = serieRepository.save(serie);

        CompletableFuture.runAsync(() -> kafkaProducer.sendMessage("Serie registered successfully"));
        return savedSerie;
    }

    public List<Serie> getAllSeries() {
        return serieRepository.findAll();
    }

    @Transactional
    public Serie updateSerie(Long id, Serie serie) {
        Serie existingSerie = serieRepository.findById(id)
                .orElseThrow(() -> new SerieNotFoundException("Serie not found with id: " + id));

        existingSerie.setTrainingForm(serie.getTrainingForm());
        existingSerie.setExercises(serie.getExercises());
        existingSerie.setExecutionOrder(serie.getExecutionOrder());

        return serieRepository.save(existingSerie);
    }

    @Transactional
    public void deleteSerie(Long id) {
        Serie serie = serieRepository.findById(id)
                .orElseThrow(() -> new SerieNotFoundException("Serie not found with id: " + id));
        serieRepository.delete(serie);
    }
}



