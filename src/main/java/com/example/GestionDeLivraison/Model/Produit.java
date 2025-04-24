package com.example.GestionDeLivraison.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "produit")
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProd;

    private String nomProd;
    private String categorie;
    private Double prix;
    private String description;
    private String image;
    private Boolean disponibilite;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "id_c")
    private Commercant commercant;

    @OneToMany(mappedBy = "produit")
    private List<Commande> commandes;

    @OneToMany(mappedBy = "produit")
    private List<AvisProduit> avis;

    // Getters and Setters
}