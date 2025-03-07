package com.example.eventify_backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

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

    // Définition d'une relation OneToOne entre Registration et Payment
    @OneToOne(mappedBy = "registration", cascade = CascadeType.ALL, orphanRemoval = true)
// mappedBy = "registration" : Indique que la relation est bidirectionnelle et que la classe Payment possède la référence "registration".
// Cela permet à JPA de comprendre que la classe Payment a un attribut "registration" et que cette relation est déjà mappée dans cette classe.

// cascade = CascadeType.ALL : Cela signifie que toutes les opérations de persistance (save, update, delete, etc.) effectuées sur l'entité "Registration"
// seront également appliquées sur l'entité "Payment". Par exemple, si la "Registration" est supprimée, la "Payment" associée sera aussi supprimée automatiquement.

// orphanRemoval = true : Lorsque orphanRemoval est activé, si la "Registration" est supprimée et qu'il n'y a plus de référence à la "Payment" dans
// l'entité "Registration", alors l'entité "Payment" sera aussi supprimée de la base de données pour éviter les "orphanes" (enfants sans parent).

    @JoinColumn(name = "payment_id", nullable = true)
// @JoinColumn : Spécifie la colonne de jointure dans la table "registrations" qui sera utilisée pour lier cette entité à une autre entité (ici, Payment).
// Le nom de la colonne dans la table de la base de données est défini par "payment_id".
// nullable = true : Cette option indique que la colonne "payment_id" peut être nulle, ce qui permet à une inscription d'exister sans paiement associé.
// Cela permet de gérer les cas où un utilisateur peut s'inscrire à un événement sans payer immédiatement. Le paiement peut être ajouté plus tard.

    private Payment payment;
// Déclaration de la relation avec l'entité Payment. Cela indique qu'un objet "Registration" a une association avec un objet "Payment".



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

    public Registration(LocalDateTime registrationDate, UserEntity user, Event event, Payment payment) {
        this.registrationDate = registrationDate;
        this.user = user;
        this.event = event;
        this.payment = payment;
    }

    // Méthode pour générer une clé aléatoire (par exemple, UUID)
    private String generateQRCode() {
        // Générer une clé aléatoire basée sur un UUID
        UUID uuid = UUID.randomUUID();
        return uuid.toString();  // La clé générée sous forme de chaîne (vous pouvez la personnaliser si nécessaire)
    }
}
