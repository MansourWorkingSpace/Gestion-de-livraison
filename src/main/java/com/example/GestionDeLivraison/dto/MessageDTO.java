package com.example.GestionDeLivraison.dto;

import java.time.LocalDateTime;

public class MessageDTO {

    private Integer idMe;
    private Integer userEnvoiId;
    private Integer userRecuId;
    private String message;
    private LocalDateTime date;

    public MessageDTO() {}

    public MessageDTO(com.example.GestionDeLivraison.Model.Message message) {
        this.idMe = message.getIdMe();
        this.userEnvoiId = message.getUserEnvoi().getIdUser();
        this.userRecuId = message.getUserRecu().getIdUser();
        this.message = message.getMessage();
        this.date = message.getDate();
    }

    public Integer getIdMe() {
        return idMe;
    }

    public void setIdMe(Integer idMe) {
        this.idMe = idMe;
    }

    public Integer getUserEnvoiId() {
        return userEnvoiId;
    }

    public void setUserEnvoiId(Integer userEnvoiId) {
        this.userEnvoiId = userEnvoiId;
    }

    public Integer getUserRecuId() {
        return userRecuId;
    }

    public void setUserRecuId(Integer userRecuId) {
        this.userRecuId = userRecuId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}