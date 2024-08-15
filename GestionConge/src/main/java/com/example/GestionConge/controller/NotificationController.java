package com.example.GestionConge.controller;

import com.example.GestionConge.models.Notification;
import com.example.GestionConge.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;
    //Endpoint pour recuperer la liste des notifs
    @GetMapping("/list")
    public List<Notification> getAllNotifications() {
        return notificationService.getAllNotifications();
    }
    //Endpoint pour recuperer par id
    @GetMapping("/{id}")
    public ResponseEntity<Notification> getNotificationById(@PathVariable int id) {
        Optional<Notification> notification = notificationService.getNotificationById(id);
        return notification.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    //Ajouter une notif
    @PostMapping("/ajout")
    public Notification addNotification(@RequestBody Notification notification) {
        return notificationService.saveNotification(notification);
    }
    //mise a jour
    @PutMapping("/{id}")
    public ResponseEntity<Notification> updateNotification(@PathVariable Long id, @RequestBody Notification notification) {
        try{
            Notification updatedNotification = notificationService.updateNotification(id, notification);
            return ResponseEntity.ok(updatedNotification);
        }
        catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    //supprimer une notif
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable int id) {
        Optional<Notification> notification = notificationService.getNotificationById(id);
        if (notification.isPresent()) {
            notificationService.deleteNotification(id);
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
