package com.example.GestionDeLivraison.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "avisproduit")
public class AvisProduit {
    @Id
    @ManyToOne
    @JoinColumn(name = "id_clt")
    private Client client;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_prod")
    private Produit produit;

    private String message;
    private LocalDateTime date;

    // Getters and Setters
}