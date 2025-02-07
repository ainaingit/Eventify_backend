package com.example.eventify_backend.controller;

import com.example.eventify_backend.entity.UserEntity;
import com.example.eventify_backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
    public ResponseEntity<Map<String, String>> register(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");
        String numero = request.get("numero");

        try {
            // Appel du service pour enregistrer l'utilisateur
            userService.registerUser(username, password, numero);

            // Si l'enregistrement réussit, répondre avec succès
            Map<String, String> response = new HashMap<>();
            response.put("message", "Utilisateur enregistré avec succès");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            // Capturer l'exception et renvoyer un message d'erreur
            System.out.println(e.getMessage());
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
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
    // Endpoint de déconnexion
    @GetMapping("/logout")
    public ResponseEntity<String> logout() {
        // Ici, on ne supprime pas réellement le token côté serveur, car le token est géré par le client.
        // On envoie juste une réponse pour informer le client qu'il peut supprimer le token localement.
        return ResponseEntity.ok("Déconnexion réussie. Supprimez le token localement.");
    }
}

