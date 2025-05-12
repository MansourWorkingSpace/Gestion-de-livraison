package com.example.GestionDeLivraison.service_imp;

import com.example.GestionDeLivraison.Model.AvisProduit;
import com.example.GestionDeLivraison.repository.AvisProduitRepository;
import com.example.GestionDeLivraison.service.AvisProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvisProduitServiceImpl implements AvisProduitService {

    private final AvisProduitRepository avisProduitRepository;

    @Autowired
    public AvisProduitServiceImpl(AvisProduitRepository avisProduitRepository) {
        this.avisProduitRepository = avisProduitRepository;
    }

    @Override
    public List<AvisProduit> getAllAvis() {
        return avisProduitRepository.findAll();
    }

    @Override
    public Optional<AvisProduit> getAvisById(Integer id) {
        return avisProduitRepository.findById(id);
    }
}
