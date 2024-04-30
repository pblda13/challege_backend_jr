package com.challege.backend_jr.controller;

import com.challege.backend_jr.dto.ClientRequestDto;
import com.challege.backend_jr.entity.Exercise;
import com.challege.backend_jr.entity.TrainingForm;
import com.challege.backend_jr.service.TrainingFormService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/trainingForm")
public class TrainingFormController {

    @Autowired
    private TrainingFormService trainingFormService;


    @PostMapping("/create")
    public ResponseEntity<TrainingForm> createTrainingForm(@Valid @RequestBody TrainingForm trainingForm) {
        TrainingForm createdForm = trainingFormService.createTrainingForm(trainingForm);
        return new ResponseEntity<>(createdForm, HttpStatus.CREATED);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<TrainingForm> updateTrainingForm(@PathVariable Long id, @Valid @RequestBody TrainingForm trainingForm) {
        TrainingForm updatedForm = trainingFormService.updateTrainingForm(id, trainingForm);
        return ResponseEntity.ok(updatedForm);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTrainingForm(@PathVariable Long id) {
        trainingFormService.deleteTrainingForm(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<TrainingForm> getTrainingFormById(@PathVariable Long id) {
        TrainingForm form = trainingFormService.getTrainingFormById(id);
        return ResponseEntity.ok(form);
    }


    @PostMapping("/{id}/add-exercises")
    public ResponseEntity<TrainingForm> addExercisesToTrainingForm(@PathVariable Long id, @Valid @RequestBody List<Exercise> exercises) {
        TrainingForm form = trainingFormService.addExercisesToTrainingForm(id, exercises);
        return ResponseEntity.ok(form);
    }


    @PutMapping("/{id}/add-exercise")
    public ResponseEntity<TrainingForm> addExerciseToTrainingForm(@PathVariable Long id, @Valid @RequestBody Exercise exercise) {
        TrainingForm form = trainingFormService.addExerciseToTrainingForm(id, exercise);
        return ResponseEntity.ok(form);
    }

    @PutMapping("/{id}/update-by-client")
    public ResponseEntity<TrainingForm> updateTrainingFormByClient(@PathVariable Long id, @Valid @RequestBody ClientRequestDto clientDto, @Valid @RequestBody TrainingForm trainingForm) throws AccessDeniedException {
        TrainingForm updatedForm = trainingFormService.updateTrainingFormByClient(clientDto, id, trainingForm);
        return ResponseEntity.ok(updatedForm);
    }


    @GetMapping("/client/by-name/{name}")
    public ResponseEntity<List<TrainingForm>> getTrainingFormsByClientName(@PathVariable String name) {
        List<TrainingForm> forms = trainingFormService.getTrainingFormsByClientName(name);
        return ResponseEntity.ok(forms);
    }


    @GetMapping("/client/by-registration/{registration}")
    public ResponseEntity<List<TrainingForm>> getTrainingFormsByClientRegistration(@PathVariable String registration) {
        List<TrainingForm> forms = trainingFormService.getTrainingFormsByClientRegistration(registration);
        return ResponseEntity.ok(forms);
    }


    @GetMapping("/by-name-and-registration/{name}/{registration}")
    public ResponseEntity<List<TrainingForm>> getTrainingFormsByClientNameAndRegistration(@PathVariable String name, @PathVariable String registration) {
        List<TrainingForm> forms = trainingFormService.getTrainingFormsByClientNameAndRegistration(name, registration);
        return ResponseEntity.ok(forms);
    }


    @GetMapping("/{id}/client")
    public ResponseEntity<TrainingForm> getTrainingFormByIdAndClient(@PathVariable Long id, @PathVariable ClientRequestDto clientRequestDto) throws AccessDeniedException {
        TrainingForm form = trainingFormService.getTrainingFormByIdAndClient(id, clientRequestDto);
        return ResponseEntity.ok(form);
    }
}