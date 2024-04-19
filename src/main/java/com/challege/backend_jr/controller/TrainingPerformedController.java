package com.challege.backend_jr.controller;

import com.challege.backend_jr.entity.TrainingPerformed;
import com.challege.backend_jr.repositories.TrainingPerformedRepository;
import com.challege.backend_jr.service.TrainingPerformedService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainingPerformed")
public class TrainingPerformedController {

    @Autowired
    private TrainingPerformedService trainingPerformedService;


    @PostMapping
    public ResponseEntity<TrainingPerformed> createTrainingPerformed(@RequestBody @Valid TrainingPerformed trainingPerformed) {
        TrainingPerformed newTrainingPerformed = trainingPerformedService.createTrainingPerformed(trainingPerformed);
        return new ResponseEntity(newTrainingPerformed, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainingPerformed> updateTrainingPerformed(@PathVariable Long id, @RequestBody TrainingPerformed trainingPerformed) {
        TrainingPerformed updateTrainingPerformed = trainingPerformedService.updateTrainingPerformed(id, trainingPerformed);
        return ResponseEntity.ok().body(updateTrainingPerformed);
    }

    @GetMapping
    public ResponseEntity<List<TrainingPerformed>> ListTrainingPerformed() {
        List<TrainingPerformed> trainingPerformedList = trainingPerformedService.getAllTrainingPerformed();
        return ResponseEntity.ok().body(trainingPerformedList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TrainingPerformed> delete(@PathVariable Long id) {
        trainingPerformedService.trainingPerformedDelete(id);
        return ResponseEntity.noContent().build();
    }
}
