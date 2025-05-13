package com.example.GestionDeLivraison.controller;

import com.example.GestionDeLivraison.service.FinanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class FinanceController {

    private final FinanceService financeService;

    public FinanceController(FinanceService financeService) {
        this.financeService = financeService;
    }

    @GetMapping("/finance/summary")
    public ResponseEntity<Map<String, Object>> getFinancialSummary() {
        try {
            return ResponseEntity.ok(financeService.getFinancialSummary());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Erreur interne : " + e.getMessage()));
        }
    }
}
