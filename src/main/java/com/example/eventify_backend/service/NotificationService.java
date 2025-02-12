package com.example.eventify_backend.service;

import com.example.eventify_backend.entity.Notification;
import com.example.eventify_backend.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    public void save(Notification notification) {
        notificationRepository.save(notification);
    }

    public void SendandSave(String numero , Notification notification) {
        // envoyer la notification au numero donner
    }
}
