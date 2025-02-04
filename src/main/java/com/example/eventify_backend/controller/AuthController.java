package com.example.eventify_backend.controller;

import com.example.eventify_backend.entity.UserEntity;
import com.example.eventify_backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // Point d'entrée pour l'enregistrement d'un utilisateur
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");
        String numero = request.get("numero");

        System.out.println("tonga anaty authController");
        try {
            userService.registerUser(username, password,numero);
            return ResponseEntity.status(HttpStatus.CREATED).body("Utilisateur enregistré avec succès");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Endpoint pour le login, qui génère un token JWT
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        try {
            String username = loginData.get("username");
            String password = loginData.get("password");
            String token = userService.authenticateUser(username, password);
            return ResponseEntity.ok(Map.of("token", token));
        } catch (IllegalArgumentException e) {
            System.out.println("erreur login");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}

