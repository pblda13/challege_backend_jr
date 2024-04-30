package com.challege.backend_jr.repositories;

import com.challege.backend_jr.entity.TrainingForm;
import com.challege.backend_jr.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {
    ;
    User findByUsername(String username);
}
