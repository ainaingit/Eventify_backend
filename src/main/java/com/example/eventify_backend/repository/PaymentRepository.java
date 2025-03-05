package com.example.eventify_backend.repository;

import com.example.eventify_backend.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    public Payment getPaymentById(Long Id);
}
