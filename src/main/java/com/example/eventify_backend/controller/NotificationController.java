package com.example.eventify_backend.controller;

import com.example.eventify_backend.entity.Notification;
import com.example.eventify_backend.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    private ResponseEntity<String> save(Notification notification) {
        notificationService.save(notification);
        return ResponseEntity.ok().body(notification.toString() + "saved ");
    }
}
