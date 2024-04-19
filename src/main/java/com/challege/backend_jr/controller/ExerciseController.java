package com.challege.backend_jr.controller;

import com.challege.backend_jr.entity.Exercise;
import com.challege.backend_jr.entity.Teacher;
import com.challege.backend_jr.repositories.ExerciseRepository;
import com.challege.backend_jr.service.ExerciseService;
import com.challege.backend_jr.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercise")
public class ExerciseController {


    @Autowired
    private ExerciseService exerciseService;


    @PostMapping
    public ResponseEntity<Exercise> createExercise(@RequestBody @Valid Exercise exercise) {
        Exercise newExercise = exerciseService.createExercise(exercise);
        return new ResponseEntity(newExercise, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exercise> updateExercise (@PathVariable Long id, @RequestBody Exercise exercise) {
        Exercise updateExercise = exerciseService.updateExercise(id, exercise);
        return ResponseEntity.ok().body(updateExercise);
    }

    @GetMapping
    public ResponseEntity<List<Exercise>> ListExercise() {
        List<Exercise> exerciseList = exerciseService.getAllExercise();
        return ResponseEntity.ok().body(exerciseList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Exercise> delete(@PathVariable Long id) {
        exerciseService.DeleteExercise(id);
        return ResponseEntity.noContent().build();
    }
}
