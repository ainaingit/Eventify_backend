package com.example.eventify_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "event_images")
public class EventImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Par exemple, l'URL ou le chemin de l'image stockée
    @Column(nullable = false)
    private String imageUrl;

    // Lien vers l'événement concerné
    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    // Getters et setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
