package com.example.GestionDeLivraison.Model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "contratCA")
public class ContratCA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCCA;

    @ManyToOne
    @JoinColumn(name = "id_c")
    private Commercant commercant;

    @ManyToOne
    @JoinColumn(name = "id_a")
    private Admin admin;

    private LocalDate dateDebut;
    private LocalDate dateFin;

    // Getters and Setters
}