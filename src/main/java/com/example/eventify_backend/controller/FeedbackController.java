package com.example.eventify_backend.controller;

import com.example.eventify_backend.entity.Feedback;
import com.example.eventify_backend.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller("/feedback")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    public ResponseEntity<String> saveFeedback(Feedback feedback) {
        feedbackService.save(feedback);
       return ResponseEntity.ok().body(" donnee enregistrer " + feedback.toString());
    }
}
