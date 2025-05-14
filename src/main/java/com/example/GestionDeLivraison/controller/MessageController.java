package com.example.GestionDeLivraison.controller;


import com.example.GestionDeLivraison.Model.Message;
import com.example.GestionDeLivraison.service_imp.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    @Autowired
    private MessageServiceImpl messageService;

    @PostMapping("/envoyer")
    public ResponseEntity<String> envoyerMessage(@RequestBody Message message) {
        String response = messageService.Envoyer(message);
        if (response.equals("Message envoyer")) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/entre")
    public ResponseEntity<List<Message>> getMessagesEntreUtilisateurs(
            @RequestParam int id1,
            @RequestParam int id2) {
        try {
            List<Message> messages = messageService.RecupererMessages(id1, id2);
            return ResponseEntity.ok(messages);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
