package com.example.GestionDeLivraison.service;

import com.example.GestionDeLivraison.Model.Commande;
import com.example.GestionDeLivraison.dto.CommandeDTO;

import java.util.Map;

public interface DeliveryService {
    Commande updateCommandeStatus(Integer id, String statut);

    Commande assignLivreur(Integer idCmd, Integer idLivreur);

    Map<String, Object> getFilteredOrders(int page, int size, String status, String searchTerm, String date, String agent, String sortBy, String sortDirection);
}