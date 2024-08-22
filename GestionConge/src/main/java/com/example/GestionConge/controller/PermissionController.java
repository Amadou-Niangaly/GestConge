package com.example.GestionConge.controller;

import com.example.GestionConge.models.Permission;
import com.example.GestionConge.repository.PermissionRepository;
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
    @Autowired
    private PermissionRepository permissionRepository;

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
    //Endpoint pour approuver une permission
    @PutMapping("/{id}/approuver")
    public ResponseEntity<Permission> approuverPermission(@PathVariable Long id) {
        Permission approuverPermission = permissionService.approuverPermission(id);
        return ResponseEntity.ok(approuverPermission);
    }
    @PutMapping("/{id}/rejeter")
    public ResponseEntity<Permission> rejeterPermission(@PathVariable Long id) {
        Permission rejeterPermission=permissionService.refuserPermission(id);
        return ResponseEntity.ok(rejeterPermission);
    }
    //Endpoint pour tous les permission approuver
    @GetMapping("/list/approved")
    public List<Permission> getApprouverPermissions() {
        return permissionRepository.findByStatus(Permission.Status.APPROUVE);
    }
}
