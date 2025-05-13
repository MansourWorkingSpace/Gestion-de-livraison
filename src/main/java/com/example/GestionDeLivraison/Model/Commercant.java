package com.example.GestionDeLivraison.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "commercant")
@PrimaryKeyJoinColumn(name = "id_user")
public class Commercant extends User {
    @OneToMany(mappedBy = "commercant")
    @JsonIgnore
    private List<Produit> produits;

    @OneToMany(mappedBy = "commercant")
    @JsonIgnore
    private List<ContratCL> contratsLivreurs;

    @OneToMany(mappedBy = "commercant")
    @JsonIgnore
    private List<ContratCA> contratsAdmins;

    @OneToMany(mappedBy = "commercant")
    @JsonIgnore
    private List<AvisLivreur> avisLivreurs;

    // Getters and Setters
    public Commercant() {}

    public List<ContratCL> getContratsLivreurs() {
        return contratsLivreurs;
    }

    public void setContratsLivreurs(List<ContratCL> contratsLivreurs) {
        this.contratsLivreurs = contratsLivreurs;
    }

    public List<AvisLivreur> getAvisLivreurs() {
        return avisLivreurs;
    }

    public void setAvisLivreurs(List<AvisLivreur> avisLivreurs) {
        this.avisLivreurs = avisLivreurs;
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }
}