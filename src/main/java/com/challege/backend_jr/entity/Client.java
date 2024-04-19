package com.challege.backend_jr.entity;

import com.challege.backend_jr.enums.RoleEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_client")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    @NotBlank
    private String username;

    @NotBlank
    @Min(5)
    @Max(5)
    @Column(nullable = false)
    private String password;

    @NotBlank
    @Column(nullable = false)
    private boolean contractActive;

    @NotBlank
    @Column(nullable = false)
    private RoleEnum role;


}
