package com.example.GestionConge.controller;

import com.example.GestionConge.models.Conge;
import com.example.GestionConge.service.CongeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/conges")
public class CongeController {
    @Autowired
    private CongeService congeService;
    //Endpoint pour recuperer tout les congé
    @GetMapping("/list")
    public List<Conge> getAllConges() {
      return congeService.getAllConges();
    }
    //Endpoint pour recuperer un congé par id
    @GetMapping("/{id}")
    public ResponseEntity<Conge> getConge(@PathVariable Long id) {
        Optional<Conge> conge=congeService.getCongeById(id);
        return conge.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }
    //Endpoint pour creer
    @PostMapping("/ajout")
    public Conge addConge(@RequestBody Conge conge) {
        return congeService.saveConge(conge);
    }
    //Endpoint pour la mise a jour
    @PutMapping("/{id}")
    public ResponseEntity<Conge> updateConge(@PathVariable Long id, @RequestBody Conge conge) {
        try{
            Conge updatedConge=congeService.updateConge(id, conge);
            return ResponseEntity.ok(updatedConge);
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    //Endpoint pour supprimer
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConge(@PathVariable Long id) {
        Optional<Conge> conge=congeService.getCongeById(id);
        if(conge.isPresent()) {
            congeService.deleteConge(id);
            return ResponseEntity.noContent().build();
        }
       else {
           return ResponseEntity.notFound().build();
        }
    }
}
