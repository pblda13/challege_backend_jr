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
        try {
            TrainingPerformed newTrainingPerformed = trainingPerformedService.createTrainingPerformed(trainingPerformed);
            return new ResponseEntity<>(newTrainingPerformed, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainingPerformed> updateTrainingPerformed(@PathVariable Long id, @RequestBody TrainingPerformed trainingPerformed) {
        try {
            TrainingPerformed updatedTrainingPerformed = trainingPerformedService.updateTrainingPerformed(id, trainingPerformed);
            return ResponseEntity.ok().body(updatedTrainingPerformed);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<TrainingPerformed>> listTrainingPerformed() {
        List<TrainingPerformed> trainingPerformedList = trainingPerformedService.getAllTrainingPerformed();
        return ResponseEntity.ok().body(trainingPerformedList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainingPerformed(@PathVariable Long id) {
        trainingPerformedService.deleteTrainingPerformed(id);
        return ResponseEntity.noContent().build();
    }
}
