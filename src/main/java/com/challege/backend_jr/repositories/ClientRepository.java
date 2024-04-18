package com.challege.backend_jr.repositories;

import com.challege.backend_jr.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client,Long> {
    List<Client> findBycontractActive(boolean contractActive);
}
