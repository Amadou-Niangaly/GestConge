package com.example.GestionConge.repository;

import com.example.GestionConge.models.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    List<Permission> findByStatus(Permission.Status status);
}
