package com.example.GestionDeLivraison.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int id_user_envoi,id_user_recu;
    private String message;
    private LocalDateTime date;

    public Message(int id_envoy, int id_recu, String message, LocalDateTime d){
        this.id_user_envoi=id_envoy;
        this.id_user_recu=id_recu;
        this.message=message;
        this.date=d;
    }
    public Message(){}
    public int getIdEnvoy(){
        return id_user_envoi;
    }
    public int getId_user_recu(){
        return id_user_recu;
    }
    public int getId(){
        return id;
    }
    public String getMessage(){
        return message;
    }
    public LocalDateTime getDate(){
        return date;
    }
    public void setId(int id){
        this.id=id;
    }
    public void setId_user_envoi(int id){
        this.id_user_envoi=id;
    }
    public void setMessage(String m){
        this.message=m;
    }
    public void setDate(LocalDateTime d){
        this.date=d;
    }
}