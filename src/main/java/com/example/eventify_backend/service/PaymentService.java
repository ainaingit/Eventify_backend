package com.example.eventify_backend.service;

import com.example.eventify_backend.entity.Payment;
import com.example.eventify_backend.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public void save(Payment payment) {

        try {
            paymentRepository.save(payment);
            System.out.println("payment saved") ;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
