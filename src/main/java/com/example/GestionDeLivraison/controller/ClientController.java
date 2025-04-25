package com.example.GestionDeLivraison.controller;

import com.example.GestionDeLivraison.Model.Client;
import com.example.GestionDeLivraison.Model.User;
import com.example.GestionDeLivraison.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ClientController {

    @Autowired
    private UserService userService; // Injecting UserService

    @PostMapping("/api/auth/registerClient")
    public ResponseEntity<String> registerClient(@RequestBody Client client) {
        // Check if email already exists
        if (userService.emailExists(client.getEmail())) {
            return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
        }

        // Save client with inherited user fields
        userService.saveUser(client);  // save client which is a subclass of User

        return ResponseEntity.ok("Client registered successfully");
    }

    @PostMapping("/api/auth/loginClient")
    public ResponseEntity<?> loginClient(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String motdepasse = credentials.get("motdepasse");

        if (email == null || motdepasse == null) {
            return new ResponseEntity<>(Map.of("message", "Email and password are required"), HttpStatus.BAD_REQUEST);
        }

        User user = userService.getUserByEmail(email);

        // Check if user exists and is a Client
        if (user == null || !(user instanceof Client client)) {
            return new ResponseEntity<>(Map.of("message", "Client not found"), HttpStatus.NOT_FOUND);
        }

        // Verify password
        if (!userService.verifyPassword(motdepasse, user.getMotdepasse())) {
            return new ResponseEntity<>(Map.of("message", "Invalid credentials"), HttpStatus.UNAUTHORIZED);
        }

        // Build the response JSON manually without exposing password or relations
        Map<String, Object> clientData = new HashMap<>();
        clientData.put("idUser", client.getIdUser());
        clientData.put("nom", client.getNom());
        clientData.put("prenom", client.getPrenom());
        clientData.put("age", client.getAge());
        clientData.put("tlf", client.getTlf());
        clientData.put("email", client.getEmail());
        clientData.put("statut", client.getStatut());
        clientData.put("photodeprofil", client.getPhotodeprofil());
        clientData.put("adresse", client.getAdresse());
        clientData.put("codePostale", client.getCodePostale());
        clientData.put("zip", client.getZip());

        return ResponseEntity.ok(clientData);
    }

}
