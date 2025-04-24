package com.example.GestionDeLivraison.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "avislivreur")
public class AvisLivreur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "avis_id")  // Changé pour éviter le conflit
    private Long id;

    @ManyToOne
    @JoinColumn(name = "commercant_id")  // Renommé pour plus de clarté
    private Commercant commercant;

    @ManyToOne
    @JoinColumn(name = "livreur_id")  // Changé de 'id_l' pour éviter le conflit
    private Livreur livreur;

    private String message;
    private LocalDateTime date;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Commercant getCommercant() { return commercant; }
    public void setCommercant(Commercant commercant) { this.commercant = commercant; }
    public Livreur getLivreur() { return livreur; }
    public void setLivreur(Livreur livreur) { this.livreur = livreur; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }
}