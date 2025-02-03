package com.example.eventify_backend.controller;

import com.example.eventify_backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // Point d'entrée pour l'enregistrement d'un utilisateur
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestParam("username") String username, @RequestParam("password") String password) {
        System.out.println("tonga anaty authController");
        try {
            userService.registerUser(username, password);
            return ResponseEntity.status(HttpStatus.CREATED).body("Utilisateur enregistré avec succès");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Point d'entrée pour la connexion (Spring Security gère cela)
    @PostMapping("/login")
    public ResponseEntity<String> login() {
        return ResponseEntity.ok("Utilisateur connecté");
    }
}
