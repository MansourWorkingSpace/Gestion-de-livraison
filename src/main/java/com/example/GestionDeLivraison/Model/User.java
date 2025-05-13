package com.example.GestionDeLivraison.Model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;

    private String nom;
    private String prenom;
    private Integer age;
    private String tlf;

    @Column(unique = true)
    private String email;

    @Column(name = "statut")
    private String statut;

    private String motdepasse;
    private String photodeprofil;

    @OneToMany(mappedBy = "userEnvoi")
    private List<Message> messagesEnvoyes;

    @OneToMany(mappedBy = "userRecu")
    private List<Message> messagesRecus;

    // Méthodes setters
    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStatut(RoleUser statutEnum) {
        this.statut = statutEnum.name(); // pour stocker sous forme de texte
    }


    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public void setPhotodeprofil(String photodeprofil) {
        this.photodeprofil = photodeprofil;
    }

    public void setMessagesEnvoyes(List<Message> messagesEnvoyes) {
        this.messagesEnvoyes = messagesEnvoyes;
    }

    public void setMessagesRecus(List<Message> messagesRecus) {
        this.messagesRecus = messagesRecus;
    }

    // Méthodes getters
    public Integer getIdUser() {
        return idUser;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Integer getAge() {
        return age;
    }

    public String getTlf() {
        return tlf;
    }

    public String getEmail() {
        return email;
    }

    public RoleUser getStatutEnum() {
        return RoleUser.valueOf(statut);
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public String getPhotodeprofil() {
        return photodeprofil;
    }

    public List<Message> getMessagesEnvoyes() {
        return messagesEnvoyes;
    }

    public List<Message> getMessagesRecus() {
        return messagesRecus;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getStatutEnum().getAuthorities(); // appelle l’enum enrichi
    }


    @Override
    public String getPassword() {
        return motdepasse;
    }

    @Override
    public String getUsername() {
        return prenom;
    }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true;}

}

