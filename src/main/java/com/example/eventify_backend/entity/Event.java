package com.example.eventify_backend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column
    private String location;

    // Nombre maximum de participants autorisés (null = pas de limite)
    @Column(name = "max_participants", nullable = true)
    private Integer maxParticipants;

    // Relation avec la catégorie de l'événement
    @ManyToOne
    @JoinColumn(name = "category_id")
    private EventCategory category;

    // Organisateur de l'événement
    @ManyToOne
    @JoinColumn(name = "organizer_id")
    private UserEntity organizer;

    // Liste des inscriptions à cet événement
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Registration> registrations;

    // Liste des feedbacks pour l'événement
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Feedback> feedbacks;

    // Notifications liées à cet événement (optionnel)
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notification> notifications;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    @Size(max = 5)  // Bean validation : max 5 images
    @JsonManagedReference // Indique que cette relation gère la sérialisation des EventImage
    private List<EventImage> images;


    // Getters et setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getters et setters pour les autres champs...

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(Integer maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public EventCategory getCategory() {
        return category;
    }

    public void setCategory(EventCategory category) {
        this.category = category;
    }

    public UserEntity getOrganizer() {
        return organizer;
    }

    public void setOrganizer(UserEntity organizer) {
        this.organizer = organizer;
    }

    public List<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public List<EventImage> getImages() {
        return images;
    }

    public void setImages(List<EventImage> images) {
        this.images = images;
    }

    /**
     * Méthode utilitaire pour vérifier le nombre actuel d'images.
     *
     * @return le nombre d'images actuellement associées à l'événement.
     */
    @Transient
    public int getCurrentImageCount() {
        return images != null ? images.size() : 0;
    }

    /**
     * Vérifie si l'événement possède déjà le maximum d'images autorisées.
     *
     * @return true si 5 images sont déjà associées, false sinon.
     */
    @Transient
    public boolean hasMaxImages() {
        return images != null && images.size() >= 5;
    }
}
