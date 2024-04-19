package com.challege.backend_jr.repositories;

import com.challege.backend_jr.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise,Long> {
}
