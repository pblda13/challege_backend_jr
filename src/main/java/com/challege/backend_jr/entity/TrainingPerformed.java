package com.challege.backend_jr.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "tb_trainingperformed")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainingPerformed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "serie_id", nullable = false)
    @NotNull
    private Serie serie;

    @Column(nullable = false)
    @NotNull
    private LocalDate dateRealization;
}

