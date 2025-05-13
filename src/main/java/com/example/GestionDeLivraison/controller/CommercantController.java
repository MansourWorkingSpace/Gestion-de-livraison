package com.example.GestionDeLivraison.controller;


import com.example.GestionDeLivraison.Model.Commercant;
import com.example.GestionDeLivraison.service.CommercantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commercants")
public class CommercantController {

    private final CommercantService commercantService;

    @Autowired
    public CommercantController(CommercantService commercantService) {
        this.commercantService = commercantService;
    }

    @GetMapping
    public ResponseEntity<List<Commercant>> getAllCommercants() {
        List<Commercant> commercants = commercantService.getAllCommercants();
        return ResponseEntity.ok(commercants);
    }

    @PostMapping
    public ResponseEntity<Commercant> createCommercant(@RequestBody Commercant commercant) {
        Commercant createdCommercant = commercantService.createCommercant(commercant);
        return ResponseEntity.ok(createdCommercant);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Commercant> getCommercantById(@PathVariable Integer id) {
        Commercant commercant = commercantService.getCommercantById(id);
        return ResponseEntity.ok(commercant);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Commercant> updateCommercant(@PathVariable Integer id, @RequestBody Commercant commercantDetails) {
        Commercant updatedCommercant = commercantService.updateCommercant(id, commercantDetails);
        return ResponseEntity.ok(updatedCommercant);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommercant(@PathVariable Integer id) {
        commercantService.deleteCommercant(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Commercant>> searchCommercants(@RequestParam String query) {
        List<Commercant> commercants = commercantService.searchCommercant(query);
        return ResponseEntity.ok(commercants);
    }
}