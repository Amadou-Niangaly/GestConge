package com.example.GestionConge.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Demande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private Date dateSoumission;
    @ManyToOne
    private Utilisateur utilisateur;
}
