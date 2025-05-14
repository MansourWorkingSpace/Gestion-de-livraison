package com.example.GestionDeLivraison.dto;

public class DashboardLDTO {
    private Integer livreurId;  // ID du livreur
    private Integer commandeId; // ID de la commande

    public DashboardLDTO() {}

    public DashboardLDTO(com.example.GestionDeLivraison.Model.DashboardL dashboardL) {
        this.livreurId = dashboardL.getLivreur().getIdUser();
        this.commandeId = dashboardL.getCommande().getIdCmd();
    }

    // Getters et Setters
    public Integer getLivreurId() {
        return livreurId;
    }

    public void setLivreurId(Integer livreurId) {
        this.livreurId = livreurId;
    }

    public Integer getCommandeId() {
        return commandeId;
    }

    public void setCommandeId(Integer commandeId) {
        this.commandeId = commandeId;
    }
}