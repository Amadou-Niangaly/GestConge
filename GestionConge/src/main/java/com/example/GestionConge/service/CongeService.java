package com.example.GestionConge.service;

import com.example.GestionConge.models.Conge;
import com.example.GestionConge.repository.CongeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CongeService {
    @Autowired
    private CongeRepository congeRepository;
    @Autowired
    private NotificationService notificationService;
    //recuperer tous les conge
    public List<Conge> getAllConges() {
        return congeRepository.findAll();
    }
    //Enregistrer un nouveau congé
    public Conge saveConge(Conge conge) {
        return congeRepository.save(conge);
    }
    //recuper un congé
    public Optional<Conge> getCongeById(Long id) {
        return congeRepository.findById(id);
    }
    //modifier un congé
    public Conge updateConge(Long id, Conge conge) {
        Optional<Conge> congeOptional = congeRepository.findById(id);
        if (congeOptional.isPresent()) {
            Conge congeToUpdate = congeOptional.get();
            congeToUpdate.setStatus(conge.getStatus());
            congeToUpdate.setDateDebut(conge.getDateDebut());
            congeToUpdate.setDateFin(conge.getDateFin());
            congeToUpdate.setUtilisateur(conge.getUtilisateur());
            congeToUpdate.setDemande(conge.getDemande());
            return congeRepository.save(congeToUpdate);
        }else{
            throw new RuntimeException("conge non trouver par id " + id);
        }

    }
    //supprimer un conge
    public void deleteConge(Long id) {
        congeRepository.deleteById(id);
    }

    //approuver un congé
    public Conge approuverConge(Long id){
        Conge conge = congeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("conge non trouver"));
            conge.setStatus(Conge.Status.APPROUVE);
            congeRepository.save(conge);

            String message="votre demande de congé "+"du " +conge.getDateDebut()+" au "+conge.getDateFin()+" à été accepter";
            notificationService.sendNotification(conge.getUtilisateur().getId(),message);
            return conge;
    }
    //rejeter un congé
    public Conge rejeterConge(Long id){
        Conge conge =congeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("conge non trouver"));
        conge.setStatus(Conge.Status.REJETE);
         congeRepository.save(conge);

        String message="votre demande de congé "+"du " +conge.getDateDebut()+" au "+conge.getDateFin()+" à malheuresement été rejeter";
        notificationService.sendNotification(conge.getUtilisateur().getId(),message);
        return conge;
    }
}
