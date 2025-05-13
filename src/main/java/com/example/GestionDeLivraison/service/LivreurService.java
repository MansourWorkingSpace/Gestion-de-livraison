package com.example.GestionDeLivraison.service;

import com.example.GestionDeLivraison.Model.Commercant;
import com.example.GestionDeLivraison.Model.Livreur;
import org.springframework.data.relational.core.sql.In;

import java.util.List;

public interface LivreurService {
    List<Livreur> getAllLivreurs();
    Livreur createLivreur(Livreur livreur);
    Livreur getLivreurById(Integer id);
    Livreur updateLivreur(Integer id, Livreur livreur);
    void deleteLivreur(Integer id);
    List<Livreur> searchLivreur(String query);
}
