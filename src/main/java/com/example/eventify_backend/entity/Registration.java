package com.example.eventify_backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "registrations")
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime registrationDate;

    @Column
    private String ticketQRCode;

    // Utilisateur inscrit
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    // Événement concerné
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    // Paiement associé (relation un-à-un)
    @OneToOne(mappedBy = "registration", cascade = CascadeType.ALL)
    private Payment payment;
}
