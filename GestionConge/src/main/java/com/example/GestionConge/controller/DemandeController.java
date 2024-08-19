package com.example.GestionConge.controller;

import com.example.GestionConge.models.Demande;
import com.example.GestionConge.service.DemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/demandes")
public class DemandeController {
    @Autowired
    private DemandeService demandeService;
    //Recuperer la liste des demandes
    @GetMapping("/list")
    public List<Demande> getAllDemandes() {
        return demandeService.getAllDemandes();
    }
    //Recuperation par id
    @GetMapping("/{id}")
    public ResponseEntity<Demande> getDemandeById(@PathVariable Long id) {
        Optional<Demande> demande = demandeService.getDemandeById(id);
        return demande.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    //ajouter une demande
    @PostMapping("/ajout")
    public Demande addDemande(@PathVariable Long id, @RequestBody Demande demande) {
        return demandeService.saveDemande(demande);
    }
    //mise a jour
    @PutMapping("/{id}")
    public ResponseEntity<Demande> updateDemande(@PathVariable Long id, @RequestBody Demande demande) {
        Optional<Demande> demandeOptional = demandeService.getDemandeById(id);
        if (demandeOptional.isPresent()) {
            Demande demandeUpdated = demandeOptional.get();
        }
        return demandeOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    //suppression
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDemande(@PathVariable Long id) {
        Optional<Demande> demandeOptional = demandeService.getDemandeById(id);
        if (demandeOptional.isPresent()) {
            demandeService.deleteDemande(id);
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
