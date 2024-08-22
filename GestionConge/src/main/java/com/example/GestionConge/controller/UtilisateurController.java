package com.example.GestionConge.controller;

import com.example.GestionConge.models.Utilisateur;
import com.example.GestionConge.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/utilisateurs")
public class UtilisateurController {
    @Autowired
    private UtilisateurService utilisateurService;
    //Endpoint pour la liste des utilisateurs
    @GetMapping("/list")
    public List<Utilisateur> AllUtilisateur() {
        return utilisateurService.getAllUtilisateur();
    }
    //Recuperer utilisateur par son id
    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> UtilisateurById(@PathVariable Long id) {
        Optional<Utilisateur> utilisateur = utilisateurService.getUtilisateurById(id);
        return utilisateur.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    //AJouter un Utilisateur
    @PostMapping("/ajout")
    public ResponseEntity<Utilisateur> createUtilisateur(
            @RequestPart("utilisateur") Utilisateur utilisateur,
            @RequestPart(value = "photo", required = false) MultipartFile photo) {
        try {
            Utilisateur savedUtilisateur = utilisateurService.saveUtilisateur(utilisateur, photo);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUtilisateur);
        } catch (IOException e) {
            // Gérer l'exception, par exemple en renvoyant une erreur HTTP 500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    //mise a jour d'un utilisateur
    @PutMapping("/{id}")
    public ResponseEntity<Utilisateur> updateUtilisateur(
            @PathVariable Long id,
            @RequestPart("utilisateur") Utilisateur utilisateur,
            @RequestPart(value = "photo", required = false) MultipartFile photo) {
        Utilisateur updatedUtilisateur = utilisateurService.updateUtilisateur(id, utilisateur, photo);
        return ResponseEntity.ok(updatedUtilisateur);
    }

    //Endpoint pour supprimer un utilisateur
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable Long id) {
        Optional<Utilisateur> utilisateur = utilisateurService.getUtilisateurById(id);
        if (utilisateur.isPresent()) {
            utilisateurService.deleteUtilisateur(id);
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }


}
