package com.example.eventify_backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @Column(nullable = false)
    private LocalDateTime Date;

    @Column(nullable = false)
    private boolean readStatus;

    // Utilisateur destinataire
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    // Événement lié (nullable si la notification n'est pas spécifique à un événement)
    @ManyToOne
    @JoinColumn(name = "event_id", nullable = true)
    private Event event;
}
