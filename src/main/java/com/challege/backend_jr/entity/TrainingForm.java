package com.challege.backend_jr.entity;

import jakarta.persistence.*;
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
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @OneToMany(mappedBy = "trainingform", cascade = CascadeType.ALL)
    private List<Serie> series;

    private LocalDate dateCreation;

    private LocalDate expirationDate;
}
