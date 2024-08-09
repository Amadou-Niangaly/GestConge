package com.example.GestionConge.service;

import com.example.GestionConge.models.Departement;
import com.example.GestionConge.repository.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartementService {
    @Autowired
    private DepartementRepository departementRepository;
    //recuperer tous les departement
    public List<Departement> getAllDepartement() {
        return departementRepository.findAll();
    }
    //Enregistrer un nouveau departement
    public Departement saveDepartement(Departement departement) {
        return departementRepository.save(departement);
    }
    //recuperer un department par id
    public Optional<Departement> getDepartementById(Long id) {
        return departementRepository.findById(id);
    }
    //modifier un departement
    public Departement updateDepartement(Long id, Departement departement) {
        Optional<Departement> optionalDepartement = departementRepository.findById(id);
        if (optionalDepartement.isPresent()) {
            Departement updatedDepartement = optionalDepartement.get();
            updatedDepartement.setNom(departement.getNom());
            updatedDepartement.setUtilisateurs(departement.getUtilisateurs());
            return departementRepository.save(updatedDepartement);
        }
        else throw new RuntimeException("departement non recuperer par id");
    }
    //supprimer un departement
    public void deleteDepartement(Long id) {
        departementRepository.deleteById(id);
    }
}
