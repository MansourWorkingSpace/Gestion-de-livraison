package com.example.GestionDeLivraison.dto;

import java.util.List;

public class LivreurDTO {

    private Integer idUser;
    private String nom;
    private String prenom;
    private Double tarifLivraison;
    private Double tarifRetour;
    private List<Integer> contratsCommercantsIds;
    private List<Long> avisCommercantsIds;
    private List<String> commandesLivreesIds;

    // Constructeur par défaut
    public LivreurDTO() {
    }

    // Constructeur qui prend un objet Livreur et initialise le DTO
    public LivreurDTO(com.example.GestionDeLivraison.Model.Livreur livreur) {
        this.idUser = livreur.getIdUser();  // ID de l'utilisateur
        this.nom = livreur.getNom();        // Nom de l'utilisateur
        this.prenom = livreur.getPrenom();  // Prénom de l'utilisateur
        this.tarifLivraison = livreur.getTarifLivraison();  // Tarif de livraison
        this.tarifRetour = livreur.getTarifRetour();        // Tarif de retour

        // Conversion des listes en IDs uniquement
        this.contratsCommercantsIds = livreur.getContratsCommercants().stream()
                .map(contrat -> contrat.getIdCCL())
                .toList();

        this.avisCommercantsIds = livreur.getAvisCommercants().stream()
                .map(avis -> avis.getId())
                .toList();

        this.commandesLivreesIds = livreur.getCommandesLivrees().stream()
                .map(dashboardL -> dashboardL.getId())
                .toList();
    }

    // Getters et Setters
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

    public Double getTarifLivraison() {
        return tarifLivraison;
    }

    public void setTarifLivraison(Double tarifLivraison) {
        this.tarifLivraison = tarifLivraison;
    }

    public Double getTarifRetour() {
        return tarifRetour;
    }

    public void setTarifRetour(Double tarifRetour) {
        this.tarifRetour = tarifRetour;
    }

    public List<Integer> getContratsCommercantsIds() {
        return contratsCommercantsIds;
    }

    public void setContratsCommercantsIds(List<Integer> contratsCommercantsIds) {
        this.contratsCommercantsIds = contratsCommercantsIds;
    }

    public List<Long> getAvisCommercantsIds() {
        return avisCommercantsIds;
    }

    public void setAvisCommercantsIds(List<Long> avisCommercantsIds) {
        this.avisCommercantsIds = avisCommercantsIds;
    }

    public List<String> getCommandesLivreesIds() {
        return commandesLivreesIds;
    }

    public void setCommandesLivreesIds(List<String> commandesLivreesIds) {
        this.commandesLivreesIds = commandesLivreesIds;
    }
}
