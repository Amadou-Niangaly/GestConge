package com.example.GestionConge.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Conge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    @Temporal(TemporalType.DATE)
    private Date dateFin;
    private String type;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    private Utilisateur utilisateur;
    @JsonIgnore
    @OneToOne
    private Demande demande;
   public enum  Status{
       APPROUVE,REJETE, EN_ATTENTE;
   }
   //
    public Conge() {
       this.status = Status.EN_ATTENTE;
    }

}
