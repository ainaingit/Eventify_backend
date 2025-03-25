package com.example.eventify_backend.controller;

import com.example.eventify_backend.entity.Event;
import com.example.eventify_backend.entity.EventImage;
import com.example.eventify_backend.entity.Registration;
import com.example.eventify_backend.entity.UserEntity;
import com.example.eventify_backend.repository.EventRepository;
import com.example.eventify_backend.repository.UserRepository;
import com.example.eventify_backend.security.JwtUtil;
import com.example.eventify_backend.service.EventService;
import com.example.eventify_backend.service.RegistrationService;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/client/events") // Utilise "/events" après "/client"
public class EventController {
    @Autowired
    JwtUtil jwtUtil ;
    @Autowired
    private EventService eventService;

    @Value("${file.upload-dir}") // Injection de la valeur du répertoire depuis application.properties
    private String uploadDir;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RegistrationService registrationService;

    // Endpoint pour récupérer tous les événements
    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.findAll(); // Retourne la liste de tous les événements
    }

    // Endpoint pour créer un nouvel événement
    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        if (event.getImages() != null) {
            for (EventImage image : event.getImages()) {
                image.setEvent(event);  // Lier chaque image à l'événement
            }
        }
        return eventService.create(event); // Crée un nouvel événement avec ses images
    }

    // 🎯 Endpoint pour créer un événement avec images
    @PostMapping("/events-with-image")
    public ResponseEntity<String> createEventWithImages(
            @RequestPart("event") Event event,
            @RequestPart("images") List<MultipartFile> images,
            @RequestHeader("Authorization") String token) {
        System.out.println("token " + token);
        System.out.println(jwtUtil.extractEmail(jwtUtil.removeFirstSevenChars(token)) + "ito le izy ");

        // Format String pour le numero vias le token dechiffrer
        String numero_via_token = jwtUtil.extractEmail(jwtUtil.removeFirstSevenChars(token)) ;

        try {
            // 🔎 Vérifier que l'événement est valide
            if (event == null) {
                return ResponseEntity.badRequest().body("L'événement est invalide.");
            }

            // Vérification et création du dossier d'upload
            Path uploadPath = Paths.get(uploadDir); // Utilisation du chemin dynamique
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);

            }

            // 📂 Créer une liste d'images (mais ne les sauvegarde pas encore)
            List<EventImage> eventImages = new ArrayList<>();

            // 🚀 Traiter et sauvegarder chaque image associée à l'événement

            for (MultipartFile file : images) {
                if (!file.isEmpty()) {
                    String filename = saveAndFormatImage(file, uploadPath); // Sauvegarder et reformater l'image
                    EventImage eventImage = new EventImage();
                    eventImage.setImageUrl(uploadDir + filename);  // Assigner l'URL de l'image
                    eventImage.setEvent(event); // Associer l'image à l'événement
                    eventImages.add(eventImage);
                    System.out.println("Image sauvegardée : " + filename);
                }
            }

            // Associer les images à l'événement
            event.setImages(eventImages);

            // Afficher l'objet event après avoir set les images
            System.out.println("Événement après set des images : " + event);
            // get user id by his tokeny numéro
            event.setOrganizer((UserEntity) userRepository.findByNumber(numero_via_token));

            // afficher l event
            event.showEvent();
            // Sauvegarder l'événement dans la base de données
             eventService.create(event);

            return ResponseEntity.ok("Événement créé avec succès, images associées.");
        } catch (Exception e) {
            // En cas d'erreur, retourner une erreur 500
            return ResponseEntity.status(500).body("Erreur lors du traitement des images.");
        }
    }

    // Fonction pour sauvegarder et reformater les images
    private String saveAndFormatImage(MultipartFile file, Path uploadPath) throws IOException {
        // 📝 Reformater le nom de l'image (ajouter un timestamp pour garantir l'unicité)
        String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(filename);

        // 🖼️ Sauvegarder l'image sur le disque
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return filename; // Retourne le nom du fichier enregistré
    }

    // Endpoint pour récupérer un événement par son ID
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Event event = eventService.getEventById(id);

        if (event == null) {
            return ResponseEntity.notFound().build(); // Retourne 404 si l'événement n'existe pas
        }

        return ResponseEntity.ok(event); // Retourne l'événement avec un statut 200
    }
    @PostMapping("/")
    public void dropEvent(@RequestBody Event event) {
        eventService.delete(event.getId());
    }

    @GetMapping("/{id}/participants")
    public List<Registration> allParticipants(@PathVariable Long id) {
        return registrationService.findByEventId(id);
    }
}
