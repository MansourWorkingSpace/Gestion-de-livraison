package com.example.GestionDeLivraison.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMe;

    @ManyToOne
    @JoinColumn(name = "id_user_envoi")
    private User userEnvoi;

    @ManyToOne
    @JoinColumn(name = "id_user_recu")
    private User userRecu;

    private String message;
    private LocalDateTime date;

    // Getters and Setters
}