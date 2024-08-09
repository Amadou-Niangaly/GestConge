package com.example.GestionConge.service;

import com.example.GestionConge.models.Notification;
import com.example.GestionConge.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;
    //recuperer tous les notifications
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }
    //Enregistrer une notifications
    public Notification saveNotification(Notification notification) {
        return notificationRepository.save(notification);
    }
    //recuperer une notifications par l'id
    public Optional<Notification> getNotificationById(long id) {
        return notificationRepository.findById(id);
    }
    //modifier une notifications
    public Notification updateNotification(Long id, Notification notification) {
        Optional<Notification> notificationOptional = notificationRepository.findById(id);
        if (notificationOptional.isPresent()) {
            Notification notificationToUpdate = notificationOptional.get();
            notificationToUpdate.setDate(new Date(notification.getDate().getTime()));
            notificationToUpdate.setMessage(notification.getMessage());
            notificationToUpdate.setStatus(notification.getStatus());
            notificationToUpdate.setUtilisateur(notification.getUtilisateur());
            return notificationRepository.save(notificationToUpdate);
        }
        else throw  new RuntimeException("La notification n'est pas recuperer par id");
    }
    //supprimer une notification
    public void deleteNotification(long id) {
        notificationRepository.deleteById(id);
    }

}
