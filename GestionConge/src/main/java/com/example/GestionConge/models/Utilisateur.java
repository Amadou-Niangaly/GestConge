package com.example.GestionConge.models;

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
    private String type;
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    private Date dateDebut;
    @ManyToOne
    private Role role;
    @OneToMany(mappedBy = "utilisateur")
    private List<Conge> conges;
    @ManyToOne
    private Departement departement;
    @OneToMany(mappedBy = "utilisateur")
    private List<Demande> demandes;
    @OneToMany(mappedBy = "utilisateur")
    private List<Notification> notifications;
    @OneToMany(mappedBy = "utilisateur")
    private List<Permission> permissions;
}
