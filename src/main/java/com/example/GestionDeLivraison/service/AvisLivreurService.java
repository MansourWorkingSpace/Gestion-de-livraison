package com.example.GestionDeLivraison.service;

import com.example.GestionDeLivraison.Model.AvisLivreur;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface AvisLivreurService {
    List<AvisLivreur> getAllAvis();
    Optional<AvisLivreur> getAvisById(Integer id);
}
