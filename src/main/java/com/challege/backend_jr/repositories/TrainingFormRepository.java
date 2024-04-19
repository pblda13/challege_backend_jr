package com.challege.backend_jr.repositories;

import com.challege.backend_jr.entity.Client;
import com.challege.backend_jr.entity.TrainingForm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainingFormRepository extends JpaRepository<TrainingForm,Long> {

}
