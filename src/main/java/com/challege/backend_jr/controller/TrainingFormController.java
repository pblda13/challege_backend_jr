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
        try {
            TrainingForm newTrainingForm = trainingFormService.createTrainingForm(trainingForm);
            return new ResponseEntity<>(newTrainingForm, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainingForm> updateTrainingForm(@PathVariable Long id, @RequestBody TrainingForm trainingForm) {
        try {
            TrainingForm updatedTrainingForm = trainingFormService.updateTrainingForm(id, trainingForm);
            return ResponseEntity.ok().body(updatedTrainingForm);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<TrainingForm>> listTrainingForms() {
        List<TrainingForm> trainingFormList = trainingFormService.getAllTrainingForms();
        return ResponseEntity.ok().body(trainingFormList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainingForm(@PathVariable Long id) {
        trainingFormService.deleteTrainingForm(id);
        return ResponseEntity.noContent().build();
    }
}

