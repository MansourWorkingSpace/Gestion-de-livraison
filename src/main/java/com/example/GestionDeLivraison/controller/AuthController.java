package com.example.GestionDeLivraison.controller;

import com.example.GestionDeLivraison.Model.*;
import com.example.GestionDeLivraison.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")

public class AuthController {

    @Autowired
    private UserService userService;

    // 🟢 Register Client
    @PostMapping("/registerClient")
    public ResponseEntity<String> registerClient(@RequestBody Client client) {
        if (userService.emailExists(client.getEmail())) {
            return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
        }

        client.setStatut(RoleUser.CLIENT);  // Set role for Client
        userService.saveUser(client);  // Save the client
        String jsonResponse = "{\"message\":\"Client registered successfully\"}";
        return ResponseEntity.ok(jsonResponse);    }

    // 🟢 Register Commercant
    @PostMapping("/registerCommercant")
    public ResponseEntity<String> registerCommercant(@RequestBody Commercant commercant) {
        if (userService.emailExists(commercant.getEmail())) {
            return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
        }

        commercant.setStatut(RoleUser.COMMERCANT);  // Set role for Commercant
        userService.saveUser(commercant);  // Save the commercant
        String jsonResponse = "{\"message\":\"Commercant registered successfully\"}";
        return ResponseEntity.ok(jsonResponse);    }

    // 🟢 Register Livreur
    @PostMapping("/registerLivreur")
    public ResponseEntity<String> registerLivreur(@RequestBody Livreur livreur) {
        if (userService.emailExists(livreur.getEmail())) {
            return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
        }

        livreur.setStatut(RoleUser.LIVREUR);  // Set role for Livreur
        userService.saveUser(livreur);  // Save the livreur
        String jsonResponse = "{\"message\":\"Livreur registered successfully\"}";
        return ResponseEntity.ok(jsonResponse);    }

    // 🔑 Login (Shared for all user types)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String motdepasse = credentials.get("motdepasse");

        if (email == null || motdepasse == null) {
            return new ResponseEntity<>(Map.of("message", "Email and password are required"), HttpStatus.BAD_REQUEST);
        }

        User user = userService.getUserByEmail(email);

        if (user == null || !userService.verifyPassword(motdepasse, user.getMotdepasse())) {
            return new ResponseEntity<>(Map.of("message", "Invalid credentials"), HttpStatus.UNAUTHORIZED);
        }

        // Build response
        Map<String, Object> userData = new HashMap<>();
        userData.put("idUser", user.getIdUser());
        userData.put("nom", user.getNom());
        userData.put("prenom", user.getPrenom());
        userData.put("email", user.getEmail());
        userData.put("statut", user.getStatutEnum().toString());

        // Add specific data based on user type
        if (user instanceof Client) {
            Client client = (Client) user;
            userData.put("adresse", client.getAdresse());
            userData.put("codePostale", client.getCodePostale());
            userData.put("zip", client.getZip());
        } else if (user instanceof Livreur) {
            Livreur livreur = (Livreur) user;
            userData.put("tarifLivraison", livreur.getTarifLivraison());
            userData.put("tarifRetour", livreur.getTarifRetour());
        } else if (user instanceof Commercant) {
            Commercant commercant = (Commercant) user;
            // Add commercant-specific data if needed
        }

        return ResponseEntity.ok(userData);
    }
}
