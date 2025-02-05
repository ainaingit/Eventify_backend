package com.example.eventify_backend.service;

import com.example.eventify_backend.entity.Event;
import com.example.eventify_backend.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
}
