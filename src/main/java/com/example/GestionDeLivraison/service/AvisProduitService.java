package com.example.GestionDeLivraison.service;

import com.example.GestionDeLivraison.Model.AvisProduit;

import java.util.List;
import java.util.Optional;

public interface AvisProduitService {
    List<AvisProduit> getAllAvis();
    Optional<AvisProduit> getAvisById(Integer id);
}
