package com.example.eventify_backend.repository;

import com.example.eventify_backend.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    public List<Registration> findByUserId(Long id);
}
