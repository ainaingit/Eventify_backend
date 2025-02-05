package com.example.eventify_backend.service;

import com.example.eventify_backend.entity.UserEntity;
import com.example.eventify_backend.repository.UserRepository;
import com.example.eventify_backend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;  // Injecter l'utilitaire JWT

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public boolean isValidPhoneNumber(String number) {
        // Vérifier que le numéro n'est pas nul
        if (number == null) {
            return false;
        }

        // Vérifier que le numéro comporte exactement 10 chiffres
        if (number.length() != 10) {
            return false;
        }

        // Vérifier que le numéro commence par "034" ou "038"
        if (!number.startsWith("034") && !number.startsWith("038")) {
            return false;
        }

        return true;
    }


    // Méthode pour enregistrer un utilisateur
    public UserEntity registerUser(String username, String password, String numero) {

        // Vérifie si le numéro est valide
        if (!isValidPhoneNumber(numero)) {
            throw new IllegalArgumentException("Numero invalide");  // Lancer l'exception ici
        }

        System.out.println("tonga ato anatin'ny service registerUser");

        // Vérifie si l'utilisateur existe déjà
        if (userRepository.findByUsername(username).isPresent()) {
            System.out.println("L'utilisateur existe déjà");
            throw new IllegalArgumentException("Utilisateur déjà existant");
        }

        // Encrypter le mot de passe
        String encodedPassword = passwordEncoder.encode(password);
        UserEntity newUser = new UserEntity();
        newUser.setUsername(username);
        newUser.setPassword(encodedPassword);
        newUser.setRole("USER");
        newUser.setNumber(numero);

        // Sauver l'utilisateur dans la base de données
        return userRepository.save(newUser);
    }


    // Méthode pour récupérer un utilisateur par son nom d'utilisateur
    public UserEntity getUserByNumber(String numero) {
        return userRepository.findByUsername(numero)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé"));
    }

    // Méthode pour authentifier un utilisateur et générer un JWT
    public String authenticateUser(String username, String rawPassword) {
        UserEntity userEntity = getUserByNumber(username);
        if (!passwordEncoder.matches(rawPassword, userEntity.getPassword())) {
            throw new IllegalArgumentException("Mot de passe incorrect");
        }
        // Si la vérification réussit, génère et retourne le token JWT
        return jwtUtil.generateToken(userEntity.getNumber());
    }
}
