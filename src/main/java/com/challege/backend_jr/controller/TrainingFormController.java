package com.challege.backend_jr.controller;

import com.challege.backend_jr.entity.Client;
import com.challege.backend_jr.entity.TrainingForm;
import com.challege.backend_jr.service.ClientService;
import com.challege.backend_jr.service.TrainingFormService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainingForm")
public class TrainingFormController {

    @Autowired
    private TrainingFormService trainingFormService;


    @PostMapping
    public ResponseEntity<TrainingForm> createTrainingForm(@RequestBody @Valid TrainingForm trainingForm) {
        TrainingForm newTrainingForm = trainingFormService.createTrainigForm(trainingForm);
        return new ResponseEntity(newTrainingForm, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainingForm> updateTrainingForm(@PathVariable Long id, @RequestBody TrainingForm trainingForm) {
        TrainingForm updateTrainingForm = trainingFormService.updateTrainigForm(id, trainingForm);
        return ResponseEntity.ok().body(updateTrainingForm);
    }

    @GetMapping
    public ResponseEntity<List<TrainingForm>> ListTrainingForm() {
        List<TrainingForm> trainingFormList = trainingFormService.getAllTrainigForm();
        return ResponseEntity.ok().body(trainingFormList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TrainingForm> delete(@PathVariable Long id) {
        trainingFormService.trainigFormDelete(id);
        return ResponseEntity.noContent().build();
    }
}
