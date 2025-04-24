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
    public ResponseEntity<Map<String, String>> loginClient(@RequestBody Map<String, String> payload) {
        // Retrieve email and password from the request payload
        String email = payload.get("email");
        String motdepasse = payload.get("motdepasse");

        // Check if email and password are provided
        if (email == null || motdepasse == null) {
            return new ResponseEntity<>(Map.of("message", "Email and password are required"), HttpStatus.BAD_REQUEST);
        }

        // Retrieve the user from the database by email
        User user = userService.getUserByEmail(email);

        // If the user is not found, return 404
        if (user == null) {
            return new ResponseEntity<>(Map.of("message", "User not found"), HttpStatus.NOT_FOUND);
        }

        // Verify if the provided password matches the stored password
        if (!userService.verifyPassword(motdepasse, user.getMotdepasse())) {
            return new ResponseEntity<>(Map.of("message", "Invalid credentials"), HttpStatus.UNAUTHORIZED);
        }

        // Simple response with a success message (You can add JWT here if needed)
        return ResponseEntity.ok(Map.of("message", "Login successful"));
    }
}
