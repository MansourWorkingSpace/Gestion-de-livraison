package com.example.GestionDeLivraison.service;

import com.example.GestionDeLivraison.Model.Produit;

import java.util.List;
import java.util.Optional;

public interface ProduitService {
    List<Produit> getAllProduits();
    Optional<Produit> getProduitById(Integer id);
}
