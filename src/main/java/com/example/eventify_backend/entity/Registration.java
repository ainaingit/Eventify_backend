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

    //Ajout de orphanRemoval = true pour supprimer un paiement lorsqu’une Registration est supprimée.
    @OneToOne(mappedBy = "registration", cascade = CascadeType.ALL, orphanRemoval = true)
    private Payment payment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getTicketQRCode() {
        return ticketQRCode;
    }

    public void setTicketQRCode(String ticketQRCode) {
        this.ticketQRCode = ticketQRCode;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Registration() {
    }
    public Registration(Long id, LocalDateTime registrationDate, String ticketQRCode, UserEntity user, Event event) {}

    public Registration(LocalDateTime registrationDate, String ticketQRCode, UserEntity user, Event event, Payment payment) {
        this.setRegistrationDate(registrationDate);
        this.setTicketQRCode(ticketQRCode);
        this.setUser(user);
        this.setEvent(event);
        this.setPayment(payment);
    }

    public Registration(LocalDateTime registrationDate, String ticketQRCode, UserEntity user, Event event) {
        this.setRegistrationDate(registrationDate);
        this.setTicketQRCode(ticketQRCode);
        this.setUser(user);
        this.setEvent(event);
    }

}
