package com.example.GestionConge.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
public class Demande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateSoumission;
    @ManyToOne
    private Utilisateur utilisateur;
    @OneToOne(mappedBy = "demande")
    private Conge conge;
    @OneToOne(mappedBy = "demande")
    private Permission permission;
}
