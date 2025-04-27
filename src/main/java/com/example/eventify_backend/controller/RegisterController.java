package com.example.eventify_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
    public ResponseEntity<String> register(
            @RequestHeader(value = "Authorization", required = false) String authorization
    ) {
        // Vérifier si le token est présent dans l'en-tête
        if (authorization != null) {
            // Ici, on suppose que le token est au format "Bearer <token>"
            String token = authorization.startsWith("Bearer ") ? authorization.substring(7) : authorization;

            // Afficher le token en console (ou l'utiliser selon ton besoin)
            System.out.println("Token d'autorisation : " + token);
            
        } else {
            System.out.println("Aucun token trouvé");
        }

        return ResponseEntity.ok("vita");
    }
}
