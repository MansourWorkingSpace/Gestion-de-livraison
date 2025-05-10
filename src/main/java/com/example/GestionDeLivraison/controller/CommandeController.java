package com.example.GestionDeLivraison.controller;

import com.example.GestionDeLivraison.Model.Commande;
import com.example.GestionDeLivraison.Model.StatutCommande;
import com.example.GestionDeLivraison.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/commandes")
@CrossOrigin(origins = "*")
public class CommandeController {

    @Autowired
    private CommandeService commandeService;

    // Liste toutes les commandes
    @GetMapping("/all")
    public ResponseEntity<List<Commande>> getAllCommandes() {
        List<Commande> commandes = commandeService.listAllCommandes();
        return ResponseEntity.ok(commandes);
    }

    // Récupère une commande par ID
    @GetMapping("/{id}")
    public ResponseEntity<Commande> getCommandeById(@PathVariable int id) {
        Optional<Commande> optionalCommande = commandeService.getCommandeById(id);
        if (optionalCommande.isPresent()) {
            return ResponseEntity.ok(optionalCommande.get());
        }
        return ResponseEntity.notFound().build();
    }

    // Met à jour le statut d'une commande
    @PutMapping("/{id}/statut/{nouveauStatut}")
    public ResponseEntity<Commande> updateStatutCommande(
            @PathVariable int id,
            @PathVariable String nouveauStatut
    ) {
        try {
            StatutCommande statut = StatutCommande.valueOf(nouveauStatut);
            Commande updatedCommande = commandeService.updateStatutCommande(id, statut);
            return ResponseEntity.ok(updatedCommande);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    // Marquer une commande comme payée
    @PutMapping("/{id}/payer")
    public ResponseEntity<Commande> marquerCommandePayee(@PathVariable int id) {
        try {
            Commande commandePayee = commandeService.marquerCommandePayee(id);
            return ResponseEntity.ok(commandePayee);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
