package com.example.GestionConge.service;

import com.example.GestionConge.models.Utilisateur;
import com.example.GestionConge.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public Utilisateur saveUtilisateur(Utilisateur utilisateur, MultipartFile photo) throws IOException {
        // Mise à jour des champs
        try {
            if (photo != null && !photo.isEmpty()) {
                utilisateur.setPhoto(photo.getBytes());
            }
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de la conversion de la photo", e);
        }
        return utilisateurRepository.save(utilisateur);
    }
    //recuperer un user
    public Optional<Utilisateur> getUtilisateurById(Long id) {
        return utilisateurRepository.findById(id);
    }
    //modifier un user
    public Utilisateur updateUtilisateur(Long id, Utilisateur utilisateur,MultipartFile photo) {
        Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findById(id);
        if (utilisateurOptional.isPresent()) {
            Utilisateur utilisateurUpdate = utilisateurOptional.get();
            // Mise à jour des champs
            try {
                if (photo != null && !photo.isEmpty()) {
                    utilisateur.setPhoto(photo.getBytes());
                }
            } catch (IOException e) {
                throw new RuntimeException("Erreur lors de la conversion de la photo", e);
            }
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

            utilisateurUpdate.setPermissions(utilisateur.getPermissions());
            utilisateurUpdate.setRole(utilisateur.getRole());
            return utilisateurRepository.save(utilisateurUpdate);
        }
        else throw new RuntimeException("Utilisateur non trouvé par id " + id);
    }
    //supprimer un utilisateur
    public void deleteUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }
}
