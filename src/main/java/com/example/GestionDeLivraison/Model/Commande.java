package com.example.GestionDeLivraison.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "commande")
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer idCmd; // Identifiant unique de la commande

    @ManyToOne
    @JoinColumn(name = "id_clt", nullable = false)
    public Client client; // Client associé à la commande

    public String adresse; // Adresse de livraison
    public String codePostale; // Code postal (optionnel)

    @Enumerated(EnumType.STRING)
    public StatutCommande statut; // Statut de la commande (en_attente, livré, annulé)

    public LocalDateTime dateCmd; // Date de création de la commande
    public Boolean estpayee; // Indique si la commande est payée

    @ManyToOne
    @JoinColumn(name = "id_prod", nullable = false)
    public Produit produit; // Produit commandé

    public Integer quantity; // Quantité commandée
    public Double prixht; // Prix hors taxes
    public Double prixUnitaire; // Prix unitaire du produit
    public Double prixTotale; // Prix total (quantité * prix unitaire)
    public String tlf; // Numéro de téléphone du client

    @Lob
    public byte[] qrCode; // QR code associé à la commande

    @OneToOne(mappedBy = "commande")
    public DashboardL dashboardL; // Relation avec le dashboard

    // Constructeur par défaut (requis pour JPA)
    public Commande() {}

    // Constructeur avec paramètres
    public Commande(Client client, String adresse, String codePostale, StatutCommande statut,
                    LocalDateTime dateCmd, Boolean estpayee, Produit produit, Integer quantity,
                    Double prixht, Double prixUnitaire, Double prixTotale, String tlf, byte[] qrCode) {
        this.client = client;
        this.adresse = adresse;
        this.codePostale = codePostale;
        this.statut = statut;
        this.dateCmd = dateCmd;
        this.estpayee = estpayee;
        this.produit = produit;
        this.quantity = quantity;
        this.prixht = prixht;
        this.prixUnitaire = prixUnitaire;
        this.prixTotale = prixTotale;
        this.tlf = tlf;
        this.qrCode = qrCode;
    }
    // Getters and Setters
    public Integer getIdCmd() {
        return idCmd;
    }

    public void setIdCmd(Integer idCmd) {
        this.idCmd = idCmd;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCodePostale() {
        return codePostale;
    }

    public void setCodePostale(String codePostale) {
        this.codePostale = codePostale;
    }

    public StatutCommande getStatut() {
        return statut;
    }

    public void setStatut(StatutCommande statut) {
        this.statut = statut;
    }

    public LocalDateTime getDateCmd() {
        return dateCmd;
    }

    public void setDateCmd(LocalDateTime dateCmd) {
        this.dateCmd = dateCmd;
    }

    public Boolean getEstpayee() {
        return estpayee;
    }

    public void setEstpayee(Boolean estpayee) {
        this.estpayee = estpayee;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrixht() {
        return prixht;
    }

    public void setPrixht(Double prixht) {
        this.prixht = prixht;
    }

    public Double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(Double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public Double getPrixTotale() {
        return prixTotale;
    }

    public void setPrixTotale(Double prixTotale) {
        this.prixTotale = prixTotale;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public byte[] getQrCode() {
        return qrCode;
    }

    public void setQrCode(byte[] qrCode) {
        this.qrCode = qrCode;
    }

    public DashboardL getDashboardL() {
        return dashboardL;
    }

    public void setDashboardL(DashboardL dashboardL) {
        this.dashboardL = dashboardL;
    }
    public Commande(Integer idCmd, StatutCommande statut, LocalDateTime dateCmd, Double prixTotale,
                    String adresse, String codePostale, Client client, Produit produit,
                    DashboardL dashboardL) {
        this.idCmd = idCmd;
        this.statut = statut;
        this.dateCmd = dateCmd;
        this.prixTotale = prixTotale;
        this.adresse = adresse;
        this.codePostale = codePostale;
        this.client = client;
        this.produit = produit;
        this.dashboardL = dashboardL;
    }

}