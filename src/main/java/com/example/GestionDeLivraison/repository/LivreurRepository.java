package com.example.GestionDeLivraison.repository;

import com.example.GestionDeLivraison.Model.Commercant;
import com.example.GestionDeLivraison.Model.Livreur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivreurRepository extends JpaRepository<Livreur, Integer> {
    List<Livreur> findByNomContainingOrPrenomContainingOrEmailContaining(
            String nom, String prenom, String email);

}