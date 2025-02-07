package com.example.eventify_backend.controller;

import com.example.eventify_backend.entity.EventCategory;
import com.example.eventify_backend.service.EventCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/event_categorie")
public class EventCategoryController {
    @Autowired
    private EventCategoryService eventCategoryService;

    @GetMapping
    public List<EventCategory> findAll() {
        return eventCategoryService.findAll();
    }
}
