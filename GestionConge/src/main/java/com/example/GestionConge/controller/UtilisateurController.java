package com.example.GestionConge.controller;

import com.example.GestionConge.models.Utilisateur;
import com.example.GestionConge.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public Utilisateur addUtilisateur(@RequestBody Utilisateur utilisateur) {
        return utilisateurService.saveUtilisateur(utilisateur);
    }
    //mise a jour d'un utilisateur
    @PutMapping("/{id}")
    public ResponseEntity<Utilisateur> updateUtilisateur(@PathVariable Long id, @RequestBody Utilisateur utilisateur) {
        try {
            Utilisateur updatedUtilisateur = utilisateurService.updateUtilisateur(id, utilisateur);
            return ResponseEntity.ok(updatedUtilisateur);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
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
