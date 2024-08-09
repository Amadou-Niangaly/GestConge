package com.example.GestionConge.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private Date date;
    private String status;
    @ManyToOne
    private Utilisateur utilisateur;
}
