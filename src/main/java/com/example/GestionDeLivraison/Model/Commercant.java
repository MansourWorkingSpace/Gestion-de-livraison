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
    private List<ContratCL> contratsLivreurs;

    @OneToMany(mappedBy = "commercant")
    private List<ContratCA> contratsAdmins;

    @OneToMany(mappedBy = "commercant")
    private List<AvisLivreur> avisLivreurs;

    // Getters and Setters
}