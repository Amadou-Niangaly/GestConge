package com.example.GestionConge.repository;

import com.example.GestionConge.models.Conge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CongeRepository  extends JpaRepository<Conge, Long> {
}
