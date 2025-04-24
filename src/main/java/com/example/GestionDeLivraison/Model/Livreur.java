package com.example.GestionDeLivraison.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "livreur")
public class Livreur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLivreur")
    private Integer idLivreur;

    @Column(name = "tarif_livraison")
    private Double tarifLivraison;  // Utilisation de Double sans precision/scale

    @Column(name = "tarif_retour")
    private Double tarifRetour;     // Utilisation de Double sans precision/scale

    @OneToMany(mappedBy = "livreur", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContratCL> contratsCommercants;

    @OneToMany(mappedBy = "livreur", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvisLivreur> avisCommercants;

    @OneToMany(mappedBy = "livreur", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DashboardL> commandesLivrees;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "idUser")
    private User user;

    // Getters et Setters
    public Integer getIdLivreur() {
        return idLivreur;
    }

    public void setIdLivreur(Integer idLivreur) {
        this.idLivreur = idLivreur;
    }

    public Double getTarifLivraison() {
        return tarifLivraison;
    }

    public void setTarifLivraison(Double tarifLivraison) {
        this.tarifLivraison = tarifLivraison;
    }

    public Double getTarifRetour() {
        return tarifRetour;
    }

    public void setTarifRetour(Double tarifRetour) {
        this.tarifRetour = tarifRetour;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // ... autres getters/setters
}