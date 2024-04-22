package com.challege.backend_jr.controller;

import com.challege.backend_jr.dto.ClientRequestDto;
import com.challege.backend_jr.entity.Exercise;
import com.challege.backend_jr.entity.TrainingForm;
import com.challege.backend_jr.service.TrainingFormService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/trainingForm")
public class TrainingFormController {

    @Autowired
    private TrainingFormService trainingFormService;

    @PostMapping
    public ResponseEntity<TrainingForm> createTrainingForm(@Valid @RequestBody TrainingForm trainingForm) {
        TrainingForm createdForm = trainingFormService.createTrainingForm(trainingForm);
        return new ResponseEntity<>(createdForm, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainingForm> updateTrainingForm(@PathVariable Long id, @Valid @RequestBody TrainingForm trainingForm) {
        TrainingForm updatedForm = trainingFormService.updateTrainingForm(id, trainingForm);
        return ResponseEntity.ok(updatedForm);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainingForm(@PathVariable Long id) {
        trainingFormService.deleteTrainingForm(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainingForm> getTrainingFormById(@PathVariable Long id) {
        TrainingForm form = trainingFormService.getTrainingFormById(id);
        return ResponseEntity.ok(form);
    }

    @PostMapping("/{id}/exercises")
    public ResponseEntity<TrainingForm> includeExercisesInTrainingForm(@PathVariable Long id, @Valid @RequestBody List<Exercise> exercises) {
        TrainingForm form = trainingFormService.includeExercisesInTrainingForm(id, exercises);
        return ResponseEntity.ok(form);
    }

    @PutMapping("/{id}/exercise")
    public ResponseEntity<TrainingForm> includeExerciseInTrainingForm(@PathVariable Long id, @Valid @RequestBody Exercise exercise) {
        TrainingForm form = trainingFormService.includeExerciseInTrainingForm(id, exercise);
        return ResponseEntity.ok(form);
    }

    @PutMapping("/{id}/client")
    public ResponseEntity<TrainingForm> updateTrainingFormByClient(@PathVariable Long id, @Valid @RequestBody ClientRequestDto clientDto, @RequestBody TrainingForm trainingForm) throws AccessDeniedException {
        TrainingForm updatedForm = trainingFormService.updateTrainingFormByClient(clientDto, id, trainingForm);
        return ResponseEntity.ok(updatedForm);
    }

    @GetMapping("/client/{name}")
    public ResponseEntity<List<TrainingForm>> getTrainingFormsByClientName(@PathVariable String name) {
        List<TrainingForm> forms = trainingFormService.getTrainingFormByClientName(name);
        return ResponseEntity.ok(forms);
    }

    @GetMapping("/client/registration/{registration}")
    public ResponseEntity<List<TrainingForm>> getTrainingFormsByClientRegistration(@PathVariable String registration) {
        List<TrainingForm> forms = trainingFormService.getTrainingFormByClientRegistration(registration);
        return ResponseEntity.ok(forms);
    }

    @GetMapping("/by-name-and-registration/{name}/{registration}")
    public ResponseEntity<List<TrainingForm>> getTrainingFormsByClientNameAndRegistration(@PathVariable String name, @PathVariable String registration) {
        List<TrainingForm> forms = trainingFormService.getTrainingFormByClientNameAndRegistration(name, registration);
        return ResponseEntity.ok(forms);
    }

}