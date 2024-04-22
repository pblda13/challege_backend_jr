package com.challege.backend_jr.repositories;

import com.challege.backend_jr.entity.TrainingForm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainingFormRepository extends JpaRepository<TrainingForm, Long> {
    List<TrainingForm> findByClient_Name(String name);

    List<TrainingForm> findByClient_Registration(String registration);

    List<TrainingForm> findByClient_NameAndClient_Registration(String name, String registration);
}
