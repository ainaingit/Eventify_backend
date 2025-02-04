package com.example.eventify_backend.controller;

import com.example.eventify_backend.entity.Event;
import com.example.eventify_backend.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/client")
public class EventController {
    @Autowired
    private EventService eventService;

    @GetMapping("/allevents")
    public List<Event> getAllEvents() {
        return eventService.findAll();
    }
}
