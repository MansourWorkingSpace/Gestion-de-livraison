package com.example.GestionDeLivraison.controller;

import com.example.GestionDeLivraison.Model.AvisLivreur;
import com.example.GestionDeLivraison.service.AvisLivreurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avislivreur")
public class AvisLivreurController {

    private final AvisLivreurService avisLivreurService;

    @Autowired
    public AvisLivreurController(AvisLivreurService avisLivreurService) {
        this.avisLivreurService = avisLivreurService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<AvisLivreur>> getAllAvis() {
        return ResponseEntity.ok(avisLivreurService.getAllAvis());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvisLivreur> getAvisById(@PathVariable Integer id) {
        return avisLivreurService.getAvisById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
