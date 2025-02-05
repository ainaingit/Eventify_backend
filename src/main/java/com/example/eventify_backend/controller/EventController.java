package com.example.eventify_backend.controller;

import com.example.eventify_backend.entity.Event;
import com.example.eventify_backend.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/client")
public class EventController {
    @Autowired
    private EventService eventService;

    @CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
    @GetMapping("/events")
    public List<Event> getAllEvents() {
        return eventService.findAll(); // Retourne la liste de tous les événements
    }

    @PostMapping("/events")
    public Event createEvent(@RequestBody Event event) {
        return eventService.create(event); // Crée un nouvel événement
    }

}
