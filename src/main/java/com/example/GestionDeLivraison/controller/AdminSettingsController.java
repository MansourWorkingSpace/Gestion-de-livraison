package com.example.GestionDeLivraison.controller;

import com.example.GestionDeLivraison.Model.Admin;
import com.example.GestionDeLivraison.Model.RoleUser;
import com.example.GestionDeLivraison.repository.AdminRepository;
import com.example.GestionDeLivraison.dto.AdminUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/settings")
public class AdminSettingsController {

    @Autowired
    private AdminRepository adminRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminDetails(@PathVariable Integer id) {
        return adminRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAdminDetails(@PathVariable Integer id, @RequestBody AdminUpdateDTO updateDTO) {
        return adminRepository.findById(id)
                .map(admin -> {
                    // Update basic user info
                    admin.setNom(updateDTO.getLastName());
                    admin.setPrenom(updateDTO.getFirstName());
                    admin.setEmail(updateDTO.getEmail());
                    admin.setTlf(updateDTO.getPhone());
                    admin.setAge(updateDTO.getAge());
                    admin.setMotdepasse(updateDTO.getPassword());
                    Admin updatedAdmin = adminRepository.save(admin);
                    return ResponseEntity.ok(updatedAdmin);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}