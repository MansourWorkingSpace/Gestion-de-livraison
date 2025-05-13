package com.example.GestionDeLivraison.dto;

import java.time.LocalDate;

public class ContratCLDTO {

    private Integer idCCL;
    private Integer commercantId;
    private Integer livreurId;
    private LocalDate dateDebut;
    private LocalDate dateFin;

    public ContratCLDTO() {}

    public ContratCLDTO(com.example.GestionDeLivraison.Model.ContratCL contratCL) {
        this.idCCL = contratCL.getIdCCL();
        this.commercantId = contratCL.getCommercant().getIdUser();
        this.livreurId = contratCL.getLivreur().getIdUser();
        this.dateDebut = contratCL.getDateDebut();
        this.dateFin = contratCL.getDateFin();
    }

    public Integer getIdCCL() {
        return idCCL;
    }

    public void setIdCCL(Integer idCCL) {
        this.idCCL = idCCL;
    }

    public Integer getCommercantId() {
        return commercantId;
    }

    public void setCommercantId(Integer commercantId) {
        this.commercantId = commercantId;
    }

    public Integer getLivreurId() {
        return livreurId;
    }

    public void setLivreurId(Integer livreurId) {
        this.livreurId = livreurId;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }
}
