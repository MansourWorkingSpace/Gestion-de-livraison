package com.example.GestionDeLivraison.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "client")
@PrimaryKeyJoinColumn(name = "id_user")
public class Client extends User {

    private String adresse;
    private String codePostale;
    private String zip;

    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<Commande> commandes;

    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<AvisProduit> avisProduits;

    // Getters and Setters for adresse, codePostale, and zip
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

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    // Getters and setters for List<Commande> and List<AvisProduit>
    public List<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(List<Commande> commandes) {
        this.commandes = commandes;
    }

    public List<AvisProduit> getAvisProduits() {
        return avisProduits;
    }

    public void setAvisProduits(List<AvisProduit> avisProduits) {
        this.avisProduits = avisProduits;
    }

    // Optional: toString method to log or debug the client object
    @Override
    public String toString() {
        return "Client{" +
                "adresse='" + adresse + '\'' +
                ", codePostale='" + codePostale + '\'' +
                ", zip='" + zip + '\'' +
                ", commandes=" + commandes +
                ", avisProduits=" + avisProduits +
                "} " + super.toString();
    }
}
