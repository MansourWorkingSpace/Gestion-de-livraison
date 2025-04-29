package com.example.GestionDeLivraison.Model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "livreur")
@PrimaryKeyJoinColumn(name = "id_user")
public class Livreur extends User {

    @Column(name = "tarif_livraison")
    private Double tarifLivraison;

    @Column(name = "tarif_retour")
    private Double tarifRetour;

    @OneToMany(mappedBy = "livreur", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContratCL> contratsCommercants;

    @OneToMany(mappedBy = "livreur", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvisLivreur> avisCommercants;

    @OneToMany(mappedBy = "livreur", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DashboardL> commandesLivrees;


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

    public List<ContratCL> getContratsCommercants() {
        return contratsCommercants;
    }

    public void setContratsCommercants(List<ContratCL> contratsCommercants) {
        this.contratsCommercants = contratsCommercants;
    }

    public List<AvisLivreur> getAvisCommercants() {
        return avisCommercants;
    }

    public void setAvisCommercants(List<AvisLivreur> avisCommercants) {
        this.avisCommercants = avisCommercants;
    }

    public List<DashboardL> getCommandesLivrees() {
        return commandesLivrees;
    }

    public void setCommandesLivrees(List<DashboardL> commandesLivrees) {
        this.commandesLivrees = commandesLivrees;
    }
}
