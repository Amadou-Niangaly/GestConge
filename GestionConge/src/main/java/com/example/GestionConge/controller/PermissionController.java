package com.example.GestionConge.controller;

import com.example.GestionConge.models.Permission;
import com.example.GestionConge.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/permissions")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;
    //Endpoint pour recuperer la liste
    @GetMapping("/list")
    public List<Permission> getAllPermissions() {
        return permissionService.getAllPermissions();
    }
    //recuperer par id
    @GetMapping("/{id}")
    public ResponseEntity<Permission> getPermissionById(@PathVariable Long id) {
        Optional<Permission> permission = permissionService.getPermissionById(id);
        return permission.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    //Ajouter une permission
    @PostMapping("/ajout")
    public Permission addPermission(@RequestBody Permission permission) {
        return permissionService.savePermission(permission);
    }
    //modifier une permission
    @PutMapping("/{id}")
    public ResponseEntity<Permission> updatePermission(@PathVariable Long id, @RequestBody Permission permission) {
        try{
            Permission updatedPermission = permissionService.updatePermission(id, permission);
            return ResponseEntity.ok(updatedPermission);
        }
        catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    //supprimer une permission
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePermission(@PathVariable Long id) {
        Optional<Permission> permission = permissionService.getPermissionById(id);
        if (permission.isPresent()) {
            permissionService.deletePermission(id);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
