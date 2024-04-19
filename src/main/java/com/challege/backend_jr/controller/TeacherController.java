package com.challege.backend_jr.controller;

import com.challege.backend_jr.entity.Client;
import com.challege.backend_jr.entity.Teacher;
import com.challege.backend_jr.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {


    @Autowired
    private TeacherService teacherService;


    @PostMapping
    public ResponseEntity<Teacher> createTeacher(@RequestBody @Valid Teacher teacher) {
        Teacher newTeacher = teacherService.createTeacher(teacher);
        return new ResponseEntity(newTeacher, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable Long id, @RequestBody Teacher teacher) {
        Teacher updateTeacher = teacherService.updateTeacher(id, teacher);
        return ResponseEntity.ok().body(updateTeacher);
    }

    @GetMapping
    public ResponseEntity<List<Teacher>> ListTeacher() {
        List<Teacher> teacherList = teacherService.getAllTeacher();
        return ResponseEntity.ok().body(teacherList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Teacher> delete(@PathVariable Long id) {
        teacherService.teacherDelete(id);
        return ResponseEntity.noContent().build();
    }
}
