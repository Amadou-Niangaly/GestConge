package com.example.GestionConge.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String raison;
    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    @Temporal(TemporalType.DATE)
    private Date dateFin;
    private Status status;
    @ManyToOne
    private Utilisateur utilisateur;
    @JsonIgnore
    @OneToOne
    private  Demande demande;
    public enum Status{
        APPROUVE,REJETE,EN_ATTENTE
    }
}
