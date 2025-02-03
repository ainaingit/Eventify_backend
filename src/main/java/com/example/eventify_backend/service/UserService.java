package com.example.eventify_backend.service;

import com.example.eventify_backend.entity.UserEntity;
import com.example.eventify_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Méthode pour enregistrer un utilisateur
    public UserEntity registerUser(String username, String password) {
        // Vérifier si l'utilisateur existe déjà
        System.out.println("tonga ato aantinle service registerUser");
        if (userRepository.findByUsername(username).isPresent()) {
            System.out.println("user ef amisy ");
            throw new IllegalArgumentException("Utilisateur déjà existant");
        }

        // Crypter le mot de passe
        String encodedPassword = passwordEncoder.encode(password);

        // Créer un nouvel utilisateur
        UserEntity newUser = new UserEntity();
        newUser.setUsername(username);
        newUser.setPassword(encodedPassword);
        newUser.setRole("USER"); // Attribuer un rôle, ici "USER"

        // Sauvegarder l'utilisateur dans la base de données
        return userRepository.save(newUser);
    }

    // Méthode pour récupérer un utilisateur par son nom d'utilisateur
    public UserEntity getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé"));
    }
}
