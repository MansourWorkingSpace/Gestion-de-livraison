package com.example.GestionDeLivraison.dto;

public class DashboardLDTO {

    private Integer livreurId;
    private Integer commandeId;
    private String id;

    public DashboardLDTO() {}

    public DashboardLDTO(com.example.GestionDeLivraison.Model.DashboardL dashboardL) {
        this.livreurId = dashboardL.getLivreur().getIdUser();
        this.commandeId = dashboardL.getCommande().getIdCmd();
        this.id = dashboardL.getId();  // ID combiné
    }

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
