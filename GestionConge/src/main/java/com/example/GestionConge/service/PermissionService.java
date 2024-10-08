package com.example.GestionConge.service;

import com.example.GestionConge.models.Permission;
import com.example.GestionConge.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.DelegatingServerHttpResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private NotificationService notificationService;

    //recuperer tous les permission
    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }
    //Enregistrer une permission
    public Permission savePermission(Permission permission) {
        return permissionRepository.save(permission);
    }
    //recuperer une permission par id
    public Optional<Permission> getPermissionById(Long id) {
        return permissionRepository.findById(id);
    }
    //modifier une permission
    public Permission updatePermission(Long id, Permission permission) {
        Optional<Permission> permissionOptional = permissionRepository.findById(id);
        if (permissionOptional.isPresent()) {
            Permission permissionToUpdate = permissionOptional.get();
            permissionToUpdate.setDateDebut(permission.getDateDebut());
            permissionToUpdate.setDateFin(permission.getDateFin());
            permissionToUpdate.setStatus(permission.getStatus());
            permissionToUpdate.setRaison(permission.getRaison());
            permissionToUpdate.setUtilisateur(permission.getUtilisateur());
            permissionToUpdate.setDemande(permission.getDemande());
           return permissionRepository.save(permissionToUpdate);
        }else{
            throw  new RuntimeException("La permission id " + id + " n'existe pas");
        }
    }
    //supprimer une permission
    public void deletePermission(Long id) {
        permissionRepository.deleteById(id);
    }

    //approuver une permission
    public Permission approuverPermission(Long id) {
        Permission permission = permissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La permission id " + id + " n'existe pas"));
        permission.setStatus(Permission.Status.APPROUVE);
         permissionRepository.save(permission);
         String message ="votre demande de permission à été approuver";
         notificationService.sendNotification(permission.getUtilisateur().getId(),message);
         return permission;
    }
    //rejeter une permission
    public Permission refuserPermission(Long id) {
        Permission permission=permissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La permission id " + id + " n'existe pas"));
        permission.setStatus(Permission.Status.REJETE);
         permissionRepository.save(permission);
         String message ="votre demande de permission n'as pas été approuver";
         notificationService.sendNotification(permission.getUtilisateur().getId(),message);
         return permission;
    }
}
