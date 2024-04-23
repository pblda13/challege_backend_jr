package com.challege.backend_jr.repositories;

import com.challege.backend_jr.entity.TrainingForm;
import com.challege.backend_jr.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
