package com.example.GestionConge.service;

import com.example.GestionConge.models.Utilisateur;
import com.example.GestionConge.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    //recuperer tous les user
    public List<Utilisateur> getAllUtilisateur() {
        return utilisateurRepository.findAll();
    }
    //enregistrer un user
    public Utilisateur saveUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }
    //recuperer un user
    public Optional<Utilisateur> getUtilisateurById(Long id) {
        return utilisateurRepository.findById(id);
    }
    //modifier un user
    public Utilisateur updateUtilisateur(Long id, Utilisateur utilisateur) {
        Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findById(id);
        if (utilisateurOptional.isPresent()) {
            Utilisateur utilisateurUpdate = utilisateurOptional.get();
            utilisateurUpdate.setNom(utilisateur.getNom());
            utilisateurUpdate.setPrenom(utilisateur.getPrenom());
            utilisateurUpdate.setEmail(utilisateur.getEmail());
            utilisateurUpdate.setPassword(utilisateur.getPassword());
            utilisateurUpdate.setDateDebut(utilisateur.getDateDebut());
            utilisateurUpdate.setDateNaissance(utilisateur.getDateNaissance());
            utilisateurUpdate.setConges(utilisateur.getConges());
            utilisateurUpdate.setDemandes(utilisateur.getDemandes());
            utilisateurUpdate.setDepartement(utilisateur.getDepartement());
            utilisateurUpdate.setNotifications(utilisateur.getNotifications());
            utilisateurUpdate.setType(utilisateur.getType());
            utilisateurUpdate.setPermissions(utilisateur.getPermissions());
            utilisateurUpdate.setRole(utilisateur.getRole());
            return utilisateurRepository.save(utilisateurUpdate);
        }
        else throw new RuntimeException("Utilisateur non trouvé par id " + id);
    }
}
