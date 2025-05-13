package com.example.GestionDeLivraison.service_imp;

import com.example.GestionDeLivraison.Model.Livreur;
import com.example.GestionDeLivraison.repository.LivreurRepository;
import com.example.GestionDeLivraison.service.LivreurService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class LivreurServiceImpl implements LivreurService {

    private final LivreurRepository livreurRepository;

    @Autowired
    public LivreurServiceImpl(LivreurRepository livreurRepository) {
        this.livreurRepository = livreurRepository;
    }

    @Override
    public List<Livreur> getAllLivreurs() {
        return livreurRepository.findAll();
    }

    @Override
    public Livreur createLivreur(Livreur livreur) {
        return livreurRepository.save(livreur);
    }

    @Override
    public Livreur getLivreurById(Integer id) {
        Optional<Livreur> livreur = livreurRepository.findById(id);
        return livreur.orElseThrow(() -> new RuntimeException("Livreur not found with id: " + id));
    }

    @Override
    public Livreur updateLivreur(Integer id, Livreur livreurDetails) {
        Livreur livreur = getLivreurById(id);

        // Update livreur fields as needed
        livreur.setNom(livreurDetails.getNom());
        livreur.setPrenom(livreurDetails.getPrenom());
        livreur.setAge(livreurDetails.getAge());
        livreur.setEmail(livreurDetails.getEmail());
        livreur.setTlf(livreurDetails.getTlf());
        livreur.setMotdepasse(livreurDetails.getMotdepasse());
        livreur.setPhotodeprofil(livreurDetails.getPhotodeprofil());
        livreur.setTarifLivraison(livreurDetails.getTarifLivraison());
        livreur.setTarifRetour(livreurDetails.getTarifRetour());

        return livreurRepository.save(livreur);
    }

    @Override
    public void deleteLivreur(Integer id) {
        Livreur livreur = getLivreurById(id);
        livreurRepository.delete(livreur);
    }

    @Override
    public List<Livreur> searchLivreur(String query) {
        return livreurRepository.findByNomContainingOrPrenomContainingOrEmailContaining(query, query, query);
    }

    @Override
    public List<Map<String, String>> getAllDeliveryAgents() {
        try {
            List<Livreur> livreurs = livreurRepository.findAll();
            return livreurs.stream()
                    .map(livreur -> Map.of(
                            "id", livreur.getIdUser().toString(),
                            "name", livreur.getNom() + " " + livreur.getPrenom()
                    ))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("Error retrieving livreurs: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}
