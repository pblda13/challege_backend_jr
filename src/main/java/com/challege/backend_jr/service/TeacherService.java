package com.challege.backend_jr.service;

import com.challege.backend_jr.entity.Teacher;
import com.challege.backend_jr.exception.TeacherNotFoundException;
import com.challege.backend_jr.producer.KafkaProducer;
import com.challege.backend_jr.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private KafkaProducer kafkaProducer;


    public Teacher createTeacher(Teacher teacher) {
        kafkaProducer.sendMessage("Teacher registered successfully");
        return teacherRepository.save(teacher);
    }

    public List<Teacher> getAllTeacher() {
        List<Teacher> teacherList = teacherRepository.findAll();
        return teacherList;
    }

    public Teacher updateTeacher(Long id, Teacher teacher) {
        Teacher teacher1 = teacherRepository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException("Teacher not found with id: " + id));

        teacher1.setName(teacher.getName());
        teacher1.setUsername(teacher.getUsername());
        teacher1.setPassword(teacher.getPassword());
        teacher1.setRole(teacher.getRole());

        return teacherRepository.save(teacher1);
    }

    public void
    teacherDelete(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException("Teacher not found with id: " + id));
        teacherRepository.delete(teacher);
    }
}


