package com.challege.backend_jr.entity;

import com.challege.backend_jr.enums.RoleEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "tb_clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client extends User {

    @NotBlank
    @Size(min = 5, max = 255)
    @Column(nullable = false)
    private String registration;

    @Column(nullable = false)
    private boolean contractActive;
}

