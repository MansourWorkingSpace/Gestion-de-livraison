package com.example.GestionDeLivraison.service_imp;

import com.example.GestionDeLivraison.Model.AvisLivreur;
import com.example.GestionDeLivraison.repository.AvisLivreurRepository;
import com.example.GestionDeLivraison.service.AvisLivreurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvisLivreurServiceImpl implements AvisLivreurService {
    private final AvisLivreurRepository avisLivreurRepository;

    @Autowired
    public AvisLivreurServiceImpl(AvisLivreurRepository avisLivreurRepository) {
        this.avisLivreurRepository = avisLivreurRepository;
    }

    @Override
    public List<AvisLivreur> getAllAvis() {
        return avisLivreurRepository.findAll();
    }

    @Override
    public Optional<AvisLivreur> getAvisById(Integer id) {
        return avisLivreurRepository.findById((long)id);
    }
}
