package com.example.GestionDeLivraison.service_imp;

import com.example.GestionDeLivraison.Model.Commande;
import com.example.GestionDeLivraison.Model.StatutCommande;
import com.example.GestionDeLivraison.repository.CommandeRepository;
import com.example.GestionDeLivraison.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommandeServiceImpl implements CommandeService {

    private final CommandeRepository commandeRepository;

    @Autowired
    public CommandeServiceImpl(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }

    @Override
    public List<Commande> listAllCommandes() {
        return commandeRepository.findAll();
    }

    @Override
    public Optional<Commande> getCommandeById(int id) {
        Optional<Commande> optionalCommande = commandeRepository.findByIdCmd(id);
        if (optionalCommande.isPresent()) {
            return optionalCommande; // Retourne l'objet Commande s'il existe
        }
        throw new RuntimeException("Commande non trouvée avec l'ID : " + id); // Lance une exception si non trouvé
    }

    @Override
    public Commande updateStatutCommande(int id, StatutCommande nouveauStatut) {
        Optional<Commande> optionalCommande = commandeRepository.findByIdCmd(id);
        if (optionalCommande.isPresent()) {
            Commande commande = optionalCommande.get(); // Extraction de l'objet Commande
            commande.statut = nouveauStatut; // Modification directe du statut
            return commandeRepository.save(commande); // Sauvegarde et retour de la commande mise à jour
        }
        throw new RuntimeException("Commande non trouvée avec l'ID : " + id); // Lance une exception si non trouvé
    }
    public Commande marquerCommandePayee(int id) {
        Optional<Commande> optionalCommande = commandeRepository.findById(id);
        if (optionalCommande.isPresent()) {
            Commande commande = optionalCommande.get();

            // Vérifier si la commande n'est pas déjà payée (0 = non payée, 1 = payée)
            if (commande.estpayee == null || commande.estpayee == false) {
                commande.estpayee = true; // Marquer la commande comme payée
                return commandeRepository.save(commande); // Sauvegarder les modifications
            } else {
                throw new RuntimeException("La commande avec l'ID " + id + " est déjà payée.");
            }
        } else {
            throw new RuntimeException("Commande non trouvée avec l'ID : " + id);
        }
    }
}