package com.challege.backend_jr.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_trainingform")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainingForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    @NotNull
    private Client client;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    @NotNull
    private Teacher teacher;

    @OneToMany(mappedBy = "trainingform")
    private List<Exercise> exercises;

    private LocalDate dateCreation;

    private LocalDate expirationDate;
}

