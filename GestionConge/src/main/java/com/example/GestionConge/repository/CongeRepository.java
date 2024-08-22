package com.example.GestionConge.repository;

import com.example.GestionConge.models.Conge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CongeRepository  extends JpaRepository<Conge, Long> {
    List<Conge> findByStatus(Conge.Status status);
}
