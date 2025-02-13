package com.example.eventify_backend.repository;

import com.example.eventify_backend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String name);
    UserEntity findById(long id);
    UserEntity findByNumber(String number);
}
