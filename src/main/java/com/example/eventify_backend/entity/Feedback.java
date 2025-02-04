package com.example.eventify_backend.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int rating; // Vous pouvez ajouter une validation pour garantir que la note est entre 0 et 5

    @Column(columnDefinition = "TEXT")
    private String comment;

    @Column(nullable = false)
    private LocalDateTime Date;

    // Événement concerné
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    // Utilisateur qui donne son feedback
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
