package com.example.GestionDeLivraison.controller;

import com.example.GestionDeLivraison.Model.AvisProduit;
import com.example.GestionDeLivraison.service.AvisProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avis")
public class AvisProduitController {

    private final AvisProduitService avisProduitService;

    @Autowired
    public AvisProduitController(AvisProduitService avisProduitService) {
        this.avisProduitService = avisProduitService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<AvisProduit>> getAllAvis() {
        return ResponseEntity.ok(avisProduitService.getAllAvis());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvisProduit> getAvisById(@PathVariable Integer id) {
        return avisProduitService.getAvisById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
