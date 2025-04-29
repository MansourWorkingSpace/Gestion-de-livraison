package com.example.GestionDeLivraison.repository;

import com.example.GestionDeLivraison.Model.AvisLivreur;
import com.example.GestionDeLivraison.Model.Livreur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvisLivreurRepository extends JpaRepository<AvisLivreur, Long> {
    List<AvisLivreur> findByLivreur_IdUser(Long id);

    // Alternative if you need to find by Livreur object:
    List<AvisLivreur> findByLivreur(Livreur livreur);
}