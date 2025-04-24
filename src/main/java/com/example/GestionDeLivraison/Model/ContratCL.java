package com.example.GestionDeLivraison.Model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "contratCL")
public class ContratCL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCCL;

    @ManyToOne
    @JoinColumn(name = "id_c")
    private Commercant commercant;

    @ManyToOne
    @JoinColumn(name = "id_l")
    private Livreur livreur;

    private LocalDate dateDebut;
    private LocalDate dateFin;

    // Getters and Setters
}