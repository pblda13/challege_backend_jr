package com.challege.backend_jr.service;

import com.challege.backend_jr.entity.Teacher;
import com.challege.backend_jr.exception.TeacherNotFoundException;
import com.challege.backend_jr.producer.KafkaProducer;
import com.challege.backend_jr.repositories.TeacherRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private KafkaProducer kafkaProducer;

    @Transactional
    public Teacher createTeacher(Teacher teacher) {
        Teacher savedTeacher = teacherRepository.save(teacher);

        CompletableFuture.runAsync(() -> kafkaProducer.sendMessage("Teacher registered successfully"));
        return savedTeacher;
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Optional<Teacher> getTeacherById(Long id) {
        return teacherRepository.findById(id);
    }

    @Transactional
    public Teacher updateTeacher(Long id, Teacher teacher) {
        Teacher existingTeacher = teacherRepository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException("Teacher not found with id: " + id));

        existingTeacher.setName(teacher.getName());
        existingTeacher.setUsername(teacher.getUsername());
        existingTeacher.setPassword(teacher.getPassword());
        existingTeacher.setRole(teacher.getRole());

        return teacherRepository.save(existingTeacher);
    }

    @Transactional
    public void deleteTeacher(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException("Teacher not found with id: " + id));
        teacherRepository.delete(teacher);
    }
}



