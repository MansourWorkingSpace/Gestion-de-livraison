package com.example.GestionDeLivraison.dto;

import java.time.LocalDateTime;

public class CommandeSummaryDTO {

    private Integer idCmd;
    private String clientNom;
    private String livreurNom;
    private String statut;
    private LocalDateTime dateCmd;
    private Double montant;

    public CommandeSummaryDTO(Integer idCmd, String clientNom, String livreurNom, String statut, LocalDateTime dateCmd, Double montant) {
        this.idCmd = idCmd;
        this.clientNom = clientNom;
        this.livreurNom = livreurNom;
        this.statut = statut;
        this.dateCmd = dateCmd;
        this.montant = montant;
    }

    public Integer getIdCmd() {
        return idCmd; // Retourne l'ID de la commande
    }

    public void setIdCmd(Integer idCmd) {
        this.idCmd = idCmd; // Définit l'ID de la commande
    }

    public String getClientNom() {
        return clientNom; // Retourne le nom du client
    }

    public void setClientNom(String clientNom) {
        this.clientNom = clientNom; // Définit le nom du client
    }

    public String getLivreurNom() {
        return livreurNom; // Retourne le nom du livreur
    }

    public void setLivreurNom(String livreurNom) {
        this.livreurNom = livreurNom; // Définit le nom du livreur
    }

    public String getStatut() {
        return statut; // Retourne le statut de la commande
    }

    public void setStatut(String statut) {
        this.statut = statut; // Définit le statut de la commande
    }

    public LocalDateTime getDateCmd() {
        return dateCmd; // Retourne la date de la commande
    }

    public void setDateCmd(LocalDateTime dateCmd) {
        this.dateCmd = dateCmd; // Définit la date de la commande
    }

    public Double getMontant() {
        return montant; // Retourne le montant total de la commande
    }

    public void setMontant(Double montant) {
        this.montant = montant; // Définit le montant total de la commande
    }
}
