package com.example.GestionConge.repository;

import com.example.GestionConge.models.Demande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandeRepository  extends JpaRepository<Demande, Long> {
}
