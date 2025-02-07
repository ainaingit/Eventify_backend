package com.example.eventify_backend.service;

import com.example.eventify_backend.entity.EventCategory;
import com.example.eventify_backend.repository.EventCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@RequestMapping("/api/event_categorie")
public class EventCategoryService {
    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    public List<EventCategory> findAll() {
        return eventCategoryRepository.findAll();
    }
}
