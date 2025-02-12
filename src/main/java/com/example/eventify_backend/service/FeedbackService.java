package com.example.eventify_backend.service;

import com.example.eventify_backend.entity.Feedback;
import com.example.eventify_backend.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;

    public void save(Feedback feedback) {
        feedbackRepository.save(feedback);
    }
    public List<Feedback> findByEventId(Long eventId) {
       return  feedbackRepository.findByEventId(eventId) ;
    }
}
