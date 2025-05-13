package com.example.GestionDeLivraison.dto;

import java.util.List;

public class ClientDTO {

    private Integer idUser; // Identifiant de l'utilisateur
    private String nom; // Nom du client
    private String prenom; // Prénom du client
    private String email; // Email du client
    private String tlf; // Numéro de téléphone du client
    private String adresse; // Adresse du client
    private String codePostale; // Code postal du client
    private String statut; // Statut du client (par exemple "client", "admin", etc.)
    private List<CommandeDTO> commandes; // Liste des commandes associées au client
    private List<MessageDTO> messagesEnvoyes; // Liste des messages envoyés par le client

    public ClientDTO(Integer idUser, String nom, String prenom, String email, String tlf,
                     String adresse, String codePostale, String statut,
                     List<CommandeDTO> commandes, List<MessageDTO> messagesEnvoyes) {
        this.idUser = idUser;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.tlf = tlf;
        this.adresse = adresse;
        this.codePostale = codePostale;
        this.statut = statut;
        this.commandes = commandes;
        this.messagesEnvoyes = messagesEnvoyes;
    }

    public ClientDTO(Integer idUser, String nom, String prenom, String email, String tlf,
                     String adresse, String codePostale, String statut,
                     List<CommandeDTO> commandes) {
        this(idUser, nom, prenom, email, tlf, adresse, codePostale, statut, commandes, null);
    }

    public ClientDTO(Integer idUser, String nom, List<CommandeDTO> commandes, String prenom) {
        this(idUser, nom, prenom, null, null, null, null, "client", commandes, null);
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
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

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public List<CommandeDTO> getCommandes() {
        return commandes;
    }

    public void setCommandes(List<CommandeDTO> commandes) {
        this.commandes = commandes;
    }

    public List<MessageDTO> getMessagesEnvoyes() {
        return messagesEnvoyes;
    }

    public void setMessagesEnvoyes(List<MessageDTO> messagesEnvoyes) {
        this.messagesEnvoyes = messagesEnvoyes;
    }
}