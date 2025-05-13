package com.example.GestionDeLivraison.service_imp;


import com.example.GestionDeLivraison.Model.Commercant;
import com.example.GestionDeLivraison.repository.CommercantRepository;
import com.example.GestionDeLivraison.service.CommercantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommercantServiceImpl implements CommercantService {

    private final CommercantRepository commercantRepository;

    @Autowired
    public CommercantServiceImpl(CommercantRepository commercantRepository) {
        this.commercantRepository = commercantRepository;
    }

    @Override
    public List<Commercant> getAllCommercants() {
        return commercantRepository.findAll();
    }

    @Override
    public Commercant createCommercant(Commercant commercant) {
        return commercantRepository.save(commercant);
    }

    @Override
    public Commercant getCommercantById(Integer id) {
        Optional<Commercant> commercant = commercantRepository.findById(id);
        return commercant.orElseThrow(() -> new RuntimeException("Commercant not found with id: " + id));
    }

    @Override
    public Commercant updateCommercant(Integer id, Commercant commercantDetails) {
        Commercant commercant = getCommercantById(id);

        commercant.setNom(commercantDetails.getNom());
        commercant.setPrenom(commercantDetails.getPrenom());
        commercant.setAge(commercantDetails.getAge());
        commercant.setEmail(commercantDetails.getEmail());
        commercant.setTlf(commercantDetails.getTlf());
        commercant.setMotdepasse(commercantDetails.getMotdepasse());
        commercant.setPhotodeprofil(commercantDetails.getPhotodeprofil());

        return commercantRepository.save(commercant);
    }

    @Override
    public void deleteCommercant(Integer id) {
        Commercant commercant = getCommercantById(id);
        commercantRepository.delete(commercant);
    }

    @Override
    public List<Commercant> searchCommercant(String query) {
        return commercantRepository.findByNomContainingOrPrenomContainingOrEmailContaining(query, query, query);
    }
}