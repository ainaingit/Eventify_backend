package com.example.eventify_backend.service;

import com.example.eventify_backend.entity.Registration;
import com.example.eventify_backend.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationService {
    @Autowired
    private RegistrationRepository registrationRepository;

    public Registration save(Registration registration) {
        return registrationRepository.save(registration);
    }
    public List<Registration> findByUserId(Long userId) {
        return registrationRepository.findByUSerId(userId) ;
    }
}
