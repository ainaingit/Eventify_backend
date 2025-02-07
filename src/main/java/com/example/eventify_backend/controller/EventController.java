package com.example.eventify_backend.controller;

import com.example.eventify_backend.entity.Event;
import com.example.eventify_backend.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client/events") // Utilise "/events" après "/client"
public class EventController {

    @Autowired
    private EventService eventService;

    // Endpoint pour récupérer tous les événements
    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.findAll(); // Retourne la liste de tous les événements
    }

    // Endpoint pour créer un nouvel événement
    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        return eventService.create(event); // Crée un nouvel événement
    }

    // Endpoint pour récupérer un événement par son ID
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Event event = eventService.getEventById(id);

        if (event == null) {
            return ResponseEntity.notFound().build(); // Retourne 404 si l'événement n'existe pas
        }

        return ResponseEntity.ok(event); // Retourne l'événement avec un statut 200
    }
}
