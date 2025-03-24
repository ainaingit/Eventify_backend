package com.example.eventify_backend.service;

import com.example.eventify_backend.entity.Event;
import com.example.eventify_backend.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public List<Event> findAll() {
        return eventRepository.findAll();
    }
    public Event create(Event event) {
        return eventRepository.save(event);
    }
    // Méthode pour récupérer un événement par son ID
    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElse(null); // Retourne null si l'événement n'existe pas
    }

    public String delete(Long id) {
        try {
            if (!eventRepository.existsById(id)) {
                return "Event with ID " + id + " does not exist.";
            }
            eventRepository.deleteById(id);
            return "Event deleted successfully with ID: " + id;
        } catch (Exception e) {
            return "Error deleting event with ID " + id + ": " + e.getMessage();
        }
    }

}
