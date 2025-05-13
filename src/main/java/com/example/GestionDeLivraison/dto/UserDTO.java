package com.example.GestionDeLivraison.dto;

import com.example.GestionDeLivraison.Model.RoleUser;
import java.util.List;

public class UserDTO {

    private Integer idUser;

    private String nom;

    private String prenom;

    private Integer age;

    private String tlf;

    private String email;

    private RoleUser statut;

    private String motdepasse;

    private String photodeprofil;

    private List<Integer> messagesEnvoyesIds;

    private List<Integer> messagesRecusIds;

    public UserDTO() {}

    public UserDTO(com.example.GestionDeLivraison.Model.User user) {
        this.idUser = user.getIdUser();
        this.nom = user.getNom();
        this.prenom = user.getPrenom();
        this.age = user.getAge();
        this.tlf = user.getTlf();
        this.email = user.getEmail();
        this.statut = user.getStatutEnum();
        this.motdepasse = user.getMotdepasse();
        this.photodeprofil = user.getPhotodeprofil();

        this.messagesEnvoyesIds = user.getMessagesEnvoyes().stream()
                .map(message -> message.getIdMe())  // Récupère l'identifiant du message envoyé
                .toList();

        this.messagesRecusIds = user.getMessagesRecus().stream()
                .map(message -> message.getIdMe())  // Récupère l'identifiant du message reçu
                .toList();
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RoleUser getStatut() {
        return statut;
    }

    public void setStatut(RoleUser statut) {
        this.statut = statut;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public String getPhotodeprofil() {
        return photodeprofil;
    }

    public void setPhotodeprofil(String photodeprofil) {
        this.photodeprofil = photodeprofil;
    }

    public List<Integer> getMessagesEnvoyesIds() {
        return messagesEnvoyesIds;
    }

    public void setMessagesEnvoyesIds(List<Integer> messagesEnvoyesIds) {
        this.messagesEnvoyesIds = messagesEnvoyesIds;
    }

    public List<Integer> getMessagesRecusIds() {
        return messagesRecusIds;
    }

    public void setMessagesRecusIds(List<Integer> messagesRecusIds) {
        this.messagesRecusIds = messagesRecusIds;
    }
}
