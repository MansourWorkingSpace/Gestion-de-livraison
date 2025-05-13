package com.example.GestionDeLivraison.dto;

import com.example.GestionDeLivraison.Model.Commercant;
import com.example.GestionDeLivraison.Model.Livreur;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.time.LocalDateTime;

public class AvisLivreurDTO {

    private Long id;
    private Commercant commercant;

    @JsonBackReference
    private Livreur livreur;

    private String message;
    private LocalDateTime date;

    public AvisLivreurDTO() {}

    public AvisLivreurDTO(com.example.GestionDeLivraison.Model.AvisLivreur avis) {
        this.id = avis.getId();
        this.commercant = avis.getCommercant();
        this.livreur = avis.getLivreur();
        this.message = avis.getMessage();
        this.date = avis.getDate();
    }


    public Long getId() {
        return id; // Retourne l'ID
    }

    public void setId(Long id) {
        this.id = id; // Définit l'ID
    }

    public Commercant getCommercant() {
        return commercant; // Retourne le commerçant
    }

    public void setCommercant(Commercant commercant) {
        this.commercant = commercant; // Définit le commerçant
    }

    public Livreur getLivreur() {
        return livreur; // Retourne le livreur
    }

    public void setLivreur(Livreur livreur) {
        this.livreur = livreur; // Définit le livreur
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
