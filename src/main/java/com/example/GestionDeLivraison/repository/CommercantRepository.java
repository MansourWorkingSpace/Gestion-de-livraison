package com.example.GestionDeLivraison.repository;

import com.example.GestionDeLivraison.Model.Client;
import com.example.GestionDeLivraison.Model.Commercant;
import com.example.GestionDeLivraison.Model.RoleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommercantRepository extends JpaRepository<Commercant, Integer> {
    List<Commercant> findByNomContainingOrPrenomContainingOrEmailContaining(
            String nom, String prenom, String email);
}