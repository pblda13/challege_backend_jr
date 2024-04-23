package com.challege.backend_jr.repositories;

import com.challege.backend_jr.entity.Client;
import com.challege.backend_jr.entity.TrainingForm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,Long> {
    Optional<Client> findByUsername(String username);
}


