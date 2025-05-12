package com.example.GestionDeLivraison.service_imp;

import com.example.GestionDeLivraison.Model.Produit;
import com.example.GestionDeLivraison.repository.ProduitRepository;
import com.example.GestionDeLivraison.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitServiceImpl implements ProduitService {

    @Autowired
    private ProduitRepository produitRepository;

    @Override
    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    @Override
    public Optional<Produit> getProduitById(Integer id) {
        return produitRepository.findById(id);
    }
}
