package com.example.eventify_backend.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDateTime paymentDate;

    @Column
    private String method; // Par exemple "Stripe" ou "PayPal"

    @Column
    private String status; // Par exemple "COMPLETED" ou "PENDING"

    // Association à une inscription
    @OneToOne
    @JoinColumn(name = "registration_id")
    private Registration registration;
}
