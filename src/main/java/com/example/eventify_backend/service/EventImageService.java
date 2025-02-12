package com.example.eventify_backend.service;

import com.example.eventify_backend.entity.EventImage;
import com.example.eventify_backend.repository.EventImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventImageService {
    @Autowired
    private EventImagesRepository eventImagesRepository;

    public List<EventImage> findByEventId(Long eventId) throws Exception {
        List<EventImage> declared_list = new ArrayList<>();
        if (eventImagesRepository.findByEventId(eventId)!= null){
            declared_list = eventImagesRepository.findByEventId(eventId);
        }
        // dans le sens ou la reservation n as pas d image associer
        return null ;
    }
}
