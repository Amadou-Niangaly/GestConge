package com.example.GestionConge.service;

import com.example.GestionConge.models.Demande;
import com.example.GestionConge.repository.DemandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DemandeService {
    @Autowired
    private DemandeRepository demandeRepository;
    //recuperer tous les demandes
    public List<Demande> getAllDemandes() {
        return demandeRepository.findAll();
    }
    //Enregistrer une demande
    public Demande saveDemande(Demande demande) {
        return demandeRepository.save(demande);
    }
    //recuperer une demande par id
    public Optional<Demande> getDemandeById(Long id) {
        return demandeRepository.findById(id);
    }
    //modifier une demande
    public Demande updateDemande(Long id, Demande demande) {
        Optional<Demande> optionalDemande = demandeRepository.findById(id);
        if (optionalDemande.isPresent()) {
            Demande updatedDemande = optionalDemande.get();
            updatedDemande.setDateSoumission(demande.getDateSoumission());
            updatedDemande.setUtilisateur(demande.getUtilisateur());
            updatedDemande.setConge(demande.getConge());
            updatedDemande.setPermission(demande.getPermission());
            return demandeRepository.save(updatedDemande);
        }
        else{
            throw  new RuntimeException("Demande non recuperer par id");
        }
    }
    //supprimer une demande
    public void deleteDemande(Long id) {
        demandeRepository.deleteById(id);
    }
}
