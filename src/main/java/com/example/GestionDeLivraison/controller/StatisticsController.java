package com.example.GestionDeLivraison.controller;

import com.example.GestionDeLivraison.service.AdminStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/statistics")
@CrossOrigin(origins = "*")
public class StatisticsController {

    private final AdminStatService adminStatService;

    @Autowired
    public StatisticsController(AdminStatService adminStatService) {
        this.adminStatService = adminStatService;
    }

    @GetMapping("/dashboard")
    public ResponseEntity<Map<String, Object>> getDashboardStats() {
        return ResponseEntity.ok(adminStatService.getAdminDashboardStats());
    }

    @GetMapping("/monthly/{year}")
    public ResponseEntity<Map<String, Object>> getMonthlyStatistics(@PathVariable int year) {
        return ResponseEntity.ok(adminStatService.getMonthlyStatistics(year));
    }

    @GetMapping("/cities")
    public ResponseEntity<Map<String, Object>> getCityDistribution() {
        Map<String, Object> stats = adminStatService.getAdminDashboardStats();
        return ResponseEntity.ok((Map<String, Object>) stats.get("cityDistribution"));
    }

    @GetMapping("/top-products")
    public ResponseEntity<Object> getTopProducts() {
        Map<String, Object> stats = adminStatService.getAdminDashboardStats();
        return ResponseEntity.ok(stats.get("topProducts"));
    }
} 