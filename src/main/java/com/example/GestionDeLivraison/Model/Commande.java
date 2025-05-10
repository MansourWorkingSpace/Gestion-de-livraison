package com.example.GestionDeLivraison.Model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
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
}