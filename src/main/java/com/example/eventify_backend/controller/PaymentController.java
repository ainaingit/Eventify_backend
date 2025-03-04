package com.example.eventify_backend.controller;

import com.example.eventify_backend.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public PaymentService getPaymentService(Long IdRegistration) {
        return paymentService;
    }

    
}
