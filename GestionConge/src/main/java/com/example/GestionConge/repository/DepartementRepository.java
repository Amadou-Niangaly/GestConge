package com.example.GestionConge.repository;

import com.example.GestionConge.models.Departement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartementRepository extends JpaRepository<Departement, Long> {
}
