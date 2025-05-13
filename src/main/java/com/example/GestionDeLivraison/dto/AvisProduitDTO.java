package com.example.GestionDeLivraison.dto;

import com.example.GestionDeLivraison.Model.Client;
import com.example.GestionDeLivraison.Model.Produit;

import java.time.LocalDateTime;

public class AvisProduitDTO {

    private Client client; // Le client qui a laissé l'avis
    private Produit produit; // Le produit auquel l'avis se rapporte
    private String message; // Le message de l'avis
    private LocalDateTime date; // La date de l'avis

    // Constructeur vide (utilisé par Jackson pour la désérialisation)
    public AvisProduitDTO() {}

    // Constructeur depuis l'entité AvisProduit
    public AvisProduitDTO(com.example.GestionDeLivraison.Model.AvisProduit avisProduit) {
        this.client = avisProduit.getClient(); // Récupère le client associé à l'avis
        this.produit = avisProduit.getProduit(); // Récupère le produit associé à l'avis
        this.message = avisProduit.getMessage(); // Récupère le message de l'avis
        this.date = avisProduit.getDate(); // Récupère la date de l'avis
    }

    // Getters et Setters

    public Client getClient() {
        return client; // Retourne le client qui a laissé l'avis
    }

    public void setClient(Client client) {
        this.client = client; // Définit le client
    }

    public Produit getProduit() {
        return produit; // Retourne le produit associé à l'avis
    }

    public void setProduit(Produit produit) {
        this.produit = produit; // Définit le produit
    }

    public String getMessage() {
        return message; // Retourne le message de l'avis
    }

    public void setMessage(String message) {
        this.message = message; // Définit le message
    }

    public LocalDateTime getDate() {
        return date; // Retourne la date de l'avis
    }

    public void setDate(LocalDateTime date) {
        this.date = date; // Définit la date
    }
}
