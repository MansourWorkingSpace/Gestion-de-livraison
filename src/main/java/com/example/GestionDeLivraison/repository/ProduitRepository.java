package com.example.GestionDeLivraison.repository;

import com.example.GestionDeLivraison.Model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Integer> {

}
