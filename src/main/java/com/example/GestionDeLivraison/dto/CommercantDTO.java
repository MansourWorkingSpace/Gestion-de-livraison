package com.example.GestionDeLivraison.dto;

import java.util.List;

public class CommercantDTO {


    private Integer idUser;
    private String nom;
    private String prenom;
    private String email;
    private String tlf;


    private List<Long> produitsIds;
    private List<Long> contratsLivreursIds;
    private List<Long> contratsAdminsIds;
    private List<Long> avisLivreursIds;

    public CommercantDTO() {}

    public CommercantDTO(com.example.GestionDeLivraison.Model.Commercant commercant) {
        this.idUser = commercant.getIdUser(); // Initialisation de l'ID de l'utilisateur
        this.nom = commercant.getNom(); // Initialisation du nom du commerçant
        this.prenom = commercant.getPrenom(); // Initialisation du prénom du commerçant
        this.email = commercant.getEmail(); // Initialisation de l'email
        this.tlf = commercant.getTlf(); // Initialisation du numéro de téléphone

        this.produitsIds = commercant.getProduits().stream().map(p -> p.getIdProd().longValue()).toList();
        this.contratsLivreursIds = commercant.getContratsLivreurs().stream().map(c -> c.getIdCCL().longValue()).toList();
        this.contratsAdminsIds = commercant.getContratsLivreurs().stream().map(c -> c.getIdCCL().longValue()).toList();
        this.avisLivreursIds = commercant.getAvisLivreurs().stream().map(a -> a.getId()).toList();
    }

    public Integer getIdUser() { return idUser; }
    public void setIdUser(Integer idUser) { this.idUser = idUser; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTlf() { return tlf; }
    public void setTlf(String tlf) { this.tlf = tlf; }

    public List<Long> getProduitsIds() { return produitsIds; }
    public void setProduitsIds(List<Long> produitsIds) { this.produitsIds = produitsIds; }

    public List<Long> getContratsLivreursIds() { return contratsLivreursIds; }
    public void setContratsLivreursIds(List<Long> contratsLivreursIds) { this.contratsLivreursIds = contratsLivreursIds; }

    public List<Long> getContratsAdminsIds() { return contratsAdminsIds; }
    public void setContratsAdminsIds(List<Long> contratsAdminsIds) { this.contratsAdminsIds = contratsAdminsIds; }

    public List<Long> getAvisLivreursIds() { return avisLivreursIds; }
    public void setAvisLivreursIds(List<Long> avisLivreursIds) { this.avisLivreursIds = avisLivreursIds; }
}
