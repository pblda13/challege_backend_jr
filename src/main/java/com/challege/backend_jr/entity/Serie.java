package com.challege.backend_jr.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tb_serie")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "training_form_id", nullable = false)
    @NotNull
    private TrainingForm trainingForm;

    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL)
    private List<Exercise> exercises;

    @Column(nullable = false)
    private int executionOrder;
}



