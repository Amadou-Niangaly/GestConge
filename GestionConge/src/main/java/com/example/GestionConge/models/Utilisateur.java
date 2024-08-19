package com.example.GestionConge.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    @ManyToOne
    private Role role;
    @JsonIgnore
    @OneToMany(mappedBy = "utilisateur")
    private List<Conge> conges;
    @ManyToOne
    private Departement departement;
    @JsonIgnore
    @OneToMany(mappedBy = "utilisateur")
    private List<Demande> demandes;
    @JsonIgnore
    @OneToMany(mappedBy = "utilisateur")
    private List<Notification> notifications;
    @JsonIgnore
    @OneToMany(mappedBy = "utilisateur")
    private List<Permission> permissions;
}
