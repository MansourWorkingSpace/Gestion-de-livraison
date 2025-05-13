package com.example.GestionDeLivraison.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "dashboardL")
public class DashboardL {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_l")
    private Livreur livreur;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_cmd")
    private Commande commande;

    // Getters and Setters
    public Livreur getLivreur() {
        return livreur;
    }

    public void setLivreur(Livreur livreur) {
        this.livreur = livreur;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public String getId() {
        return livreur.getIdUser() + "-" + commande.getIdCmd();
    }

}
