package com.example.GestionDeLivraison.service;

import com.example.GestionDeLivraison.Model.Livreur;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface LivreurService {
    List<Livreur> getAllLivreurs();
    Livreur createLivreur(Livreur livreur);
    Livreur getLivreurById(Integer id);
    Livreur updateLivreur(Integer id, Livreur livreur);
    void deleteLivreur(Integer id);
    List<Livreur> searchLivreur(String query);

    List<Map<String, String>> getAllDeliveryAgents();
}
