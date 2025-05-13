package com.example.GestionDeLivraison.dto;

import com.example.GestionDeLivraison.Model.Commande;
import com.example.GestionDeLivraison.service.AvisService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;

import java.time.LocalDateTime;

public class CommandeDTO {


    // Attributs de la commande
    @NotNull(message = "Client ID est requis")
    private Integer idCmd; // Identifiant de la commande
    private Integer clientId; // Identifiant du client
    private String clientNom; // Nom du client
    @NotBlank(message = "Adresse est requise")
    private String adresse; // Adresse de livraison
    @NotBlank(message = "Code postal est requis")
    private String codePostale; // Code postal de l'adresse
    private String statut; // Statut de la commande
    private LocalDateTime dateCmd; // Date de la commande
    private Boolean estpayee; // Statut de paiement (true ou false)
    @NotNull(message = "Produit ID est requis")
    private Integer produitId; // Identifiant du produit
    private String produitNom; // Nom du produit
    @NotNull(message = "Quantité est requise")
    @Min(value = 1, message = "La quantité doit être supérieure à 0")
    private Integer quantity; // Quantité de produits commandés
    private Double prixTotale; // Prix total de la commande

    // Constructeur à partir de l'entité Commande
    public CommandeDTO(Commande commande) {
        // Initialisation des attributs à partir de l'entité Commande
        this.idCmd = commande.getIdCmd();
        this.clientId = commande.getClient() != null ? commande.getClient().getIdUser() : null;
        this.clientNom = commande.getClient() != null ? commande.getClient().getNom() + " " + commande.getClient().getPrenom() : null;
        this.adresse = commande.getAdresse();
        this.codePostale = commande.getCodePostale();
        this.statut = commande.getStatut() != null ? commande.getStatut().name() : null;
        this.dateCmd = commande.getDateCmd();
        this.estpayee = commande.getEstpayee();
        this.produitId = commande.getProduit() != null ? commande.getProduit().getIdProd() : null;
        this.produitNom = commande.getProduit() != null ? commande.getProduit().getNomProd() : null;
        this.quantity = commande.getQuantity();
        this.prixTotale = commande.getPrixTotale();
    }

    // Getters et Setters pour chaque attribut
    public Integer getIdCmd() {
        return idCmd; // Retourne l'ID de la commande
    }

    public void setIdCmd(Integer idCmd) {
        this.idCmd = idCmd; // Définit l'ID de la commande
    }

    public Integer getClientId() {
        return clientId; // Retourne l'ID du client
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId; // Définit l'ID du client
    }

    public String getClientNom() {
        return clientNom; // Retourne le nom du client
    }

    public void setClientNom(String clientNom) {
        this.clientNom = clientNom; // Définit le nom du client
    }

    public String getAdresse() {
        return adresse; // Retourne l'adresse de livraison
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse; // Définit l'adresse de livraison
    }

    public String getCodePostale() {
        return codePostale; // Retourne le code postal de l'adresse
    }

    public void setCodePostale(String codePostale) {
        this.codePostale = codePostale; // Définit le code postal de l'adresse
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

    public Boolean getEstpayee() {
        return estpayee; // Retourne si la commande est payée ou non
    }

    public void setEstpayee(Boolean estpayee) {
        this.estpayee = estpayee; // Définit le statut de paiement
    }

    public Integer getProduitId() {
        return produitId; // Retourne l'ID du produit
    }

    public void setProduitId(Integer produitId) {
        this.produitId = produitId; // Définit l'ID du produit
    }

    public String getProduitNom() {
        return produitNom; // Retourne le nom du produit
    }

    public void setProduitNom(String produitNom) {
        this.produitNom = produitNom; // Définit le nom du produit
    }

    public Integer getQuantity() {
        return quantity; // Retourne la quantité de produits commandés
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity; // Définit la quantité de produits commandés
    }

    public Double getPrixTotale() {
        return prixTotale; // Retourne le prix total de la commande
    }

    public void setPrixTotale(Double prixTotale) {
        this.prixTotale = prixTotale; // Définit le prix total de la commande
    }
}
