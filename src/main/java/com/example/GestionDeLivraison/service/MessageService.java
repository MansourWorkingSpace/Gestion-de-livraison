package com.example.GestionDeLivraison.service;


import com.example.GestionDeLivraison.Model.Message;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MessageService {
    String Envoyer(Message m); //envoyer une message de x vers y (le parametre m contient le user_recu et user_envoi)
    ArrayList<Message> RecupererMessages(int id1, int id2) throws SQLException; // recuperer tous les messages ordonné entre 2

}
