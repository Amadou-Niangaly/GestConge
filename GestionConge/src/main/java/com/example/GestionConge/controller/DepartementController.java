package com.example.GestionConge.controller;

import com.example.GestionConge.models.Departement;
import com.example.GestionConge.service.DepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departement")
public class DepartementController {
    @Autowired
    private DepartementService departementService;
    //recuper la liste des departement
    @GetMapping("/list")
    public List<Departement> getAllDepartement(){
        return departementService.getAllDepartement();
    }
    //recuperer par id
    @GetMapping("/{id}")
    public ResponseEntity<Departement> getDepartementById(@PathVariable Long id){
        Optional<Departement> departement = departementService.getDepartementById(id);
        return departement.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    //ajouter  un departement
    @PostMapping("/ajout")
    public Departement addDepartement(@RequestBody Departement departement){
        return departementService.saveDepartement(departement);
    }
    //modifier un departement
    @PutMapping("/{id}")
    public ResponseEntity<Departement> updateDepartement(@PathVariable Long id, @RequestBody Departement departement){
        try{
            Departement updateDepartement = departementService.updateDepartement(id, departement);
            return ResponseEntity.ok(updateDepartement);
        }
        catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    //supprimer un departement
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartement(@PathVariable Long id){
        Optional<Departement> departement = departementService.getDepartementById(id);
        if(departement.isPresent()){
            departementService.deleteDepartement(id);
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
