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
    // Getters
    public Integer getIdCCL() {
        return idCCL;
    }

    public Commercant getCommercant() {
        return commercant;
    }

    public Livreur getLivreur() {
        return livreur;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    // Setters
    public void setIdCCL(Integer idCCL) {
        this.idCCL = idCCL;
    }

    public void setCommercant(Commercant commercant) {
        this.commercant = commercant;
    }

    public void setLivreur(Livreur livreur) {
        this.livreur = livreur;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }
}