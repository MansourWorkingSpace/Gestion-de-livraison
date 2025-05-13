package com.example.GestionDeLivraison.controller;

import com.example.GestionDeLivraison.Model.Commande;
import com.example.GestionDeLivraison.dto.CommandeDTO;
import com.example.GestionDeLivraison.service.AvisService;
import com.example.GestionDeLivraison.service.DeliveryService;
import com.example.GestionDeLivraison.service.CommandeService;
import com.example.GestionDeLivraison.mapper.CommandeMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class DeliveryController {

    private final DeliveryService deliveryService;
    private final AvisService avisService;
    private final CommandeService commandeService;
    private final CommandeMapper commandeMapper;

    public DeliveryController(
            DeliveryService deliveryService,
            AvisService avisService,
            CommandeService commandeService,
            CommandeMapper commandeMapper
    ) {
        this.deliveryService = deliveryService;
        this.avisService = avisService;
        this.commandeService = commandeService;
        this.commandeMapper = commandeMapper;
    }

    @PostMapping("/commandes/{idCmd}/assign-livreur/{idLivreur}")
    public ResponseEntity<?> assignLivreurToCommande(
            @PathVariable Integer idCmd,
            @PathVariable Integer idLivreur) {
        try {
            return ResponseEntity.ok(deliveryService.assignLivreur(idCmd, idLivreur));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/commandes/{id}/statut/{statut}")
    public ResponseEntity<?> updateStatutCommande(
            @PathVariable Integer id,
            @PathVariable String statut) {
        try {
            Commande updatedCommande = deliveryService.updateCommandeStatus(id, statut);
            return ResponseEntity.ok(commandeMapper.toDTO(updatedCommande));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Erreur inattendue : " + e.getMessage()));
        }
    }

    @GetMapping("/orders")
    public ResponseEntity<Map<String, Object>> getOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "all") String status,
            @RequestParam(defaultValue = "") String searchTerm,
            @RequestParam(defaultValue = "") String date,
            @RequestParam(defaultValue = "all") String agent,
            @RequestParam(defaultValue = "dateCmd") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDirection) {
        return ResponseEntity.ok(deliveryService.getFilteredOrders(page, size, status, searchTerm, date, agent, sortBy, sortDirection));
    }



}
