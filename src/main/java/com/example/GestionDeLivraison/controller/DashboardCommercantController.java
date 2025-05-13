package com.example.GestionDeLivraison.controller;

import com.example.GestionDeLivraison.repository.CommandeRepository;
import com.example.GestionDeLivraison.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "http://localhost:4200")
public class DashboardCommercantController {

    private final DashboardService dashboardService;
    private final CommandeRepository commandeRepository;

    @Autowired
    public DashboardCommercantController(DashboardService dashboardService, CommandeRepository commandeRepository) {
        this.dashboardService = dashboardService;
        this.commandeRepository = commandeRepository;
    }

    @GetMapping("/recent-deliveries")
    public ResponseEntity<?> getRecentDeliveries(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            return ResponseEntity.ok(dashboardService.getRecentCommandes(page, size));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Erreur interne : " + e.getMessage()));
        }
    }

    @GetMapping("/commandes/recent")
    public ResponseEntity<?> getRecentCommandes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            return ResponseEntity.ok(dashboardService.getRecentCommandes(page, size));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Erreur interne : " + e.getMessage()));
        }
    }

    @GetMapping("/delivery-zones")
    public ResponseEntity<Map<String, Object>> getDeliveryZones() {
        try {
            return ResponseEntity.ok(dashboardService.getDeliveryZones());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Erreur interne : " + e.getMessage()));
        }
    }

    @GetMapping("/clients/top")
    public ResponseEntity<?> getTopClients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            return ResponseEntity.ok(dashboardService.getTopClients(page, size));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Erreur interne : " + e.getMessage()));
        }
    }

    @GetMapping("/commandes/stats")
    public ResponseEntity<Map<String, Object>> getDeliveryStats() {
        try {
            return ResponseEntity.ok(dashboardService.getDeliveryStats());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Erreur interne : " + e.getMessage()));
        }
    }

    @GetMapping("/stats/cards")
    public ResponseEntity<List<Map<String, Object>>> getStatsCards() {
        try {
            return ResponseEntity.ok(dashboardService.getStatsCards());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(List.of(Map.of("error", "Erreur lors du chargement des statistiques")));
        }
    }

}
