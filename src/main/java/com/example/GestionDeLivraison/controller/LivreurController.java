package com.example.GestionDeLivraison.controller;

import com.example.GestionDeLivraison.Model.Livreur;
import com.example.GestionDeLivraison.service.LivreurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livreurs")
public class LivreurController {

    private final LivreurService livreurService;

    @Autowired
    public LivreurController(LivreurService livreurService) {
        this.livreurService = livreurService;
    }

    @GetMapping
    public ResponseEntity<List<Livreur>> getAllLivreurs() {
        List<Livreur> livreurs = livreurService.getAllLivreurs();
        return new ResponseEntity<>(livreurs, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Livreur> createLivreur(@RequestBody Livreur livreur) {
        Livreur createdLivreur = livreurService.createLivreur(livreur);
        return new ResponseEntity<>(createdLivreur, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livreur> getLivreurById(@PathVariable Integer id) {
        Livreur livreur = livreurService.getLivreurById(id);
        return new ResponseEntity<>(livreur, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livreur> updateLivreur(
            @PathVariable Integer id,
            @RequestBody Livreur livreurDetails) {
        Livreur updatedLivreur = livreurService.updateLivreur(id, livreurDetails);
        return new ResponseEntity<>(updatedLivreur, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLivreur(@PathVariable Integer id) {
        livreurService.deleteLivreur(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Livreur>> searchLivreurs(@RequestParam String query) {
        List<Livreur> livreurs = livreurService.searchLivreur(query);
        return new ResponseEntity<>(livreurs, HttpStatus.OK);
    }
}