package com.example.GestionDeLivraison.service;

import com.example.GestionDeLivraison.Model.Commercant;

import java.util.List;

public interface CommercantService {
    List<Commercant> getAllCommercants();
    Commercant createCommercant(Commercant commercant);
    Commercant getCommercantById(Integer id);
    Commercant updateCommercant(Integer id, Commercant commercant);
    void deleteCommercant(Integer id);
    List<Commercant> searchCommercant(String query);
}
