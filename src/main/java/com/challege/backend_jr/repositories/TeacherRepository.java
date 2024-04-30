package com.challege.backend_jr.repositories;

import com.challege.backend_jr.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {

}
