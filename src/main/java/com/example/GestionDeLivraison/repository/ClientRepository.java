package com.example.GestionDeLivraison.repository;

import com.example.GestionDeLivraison.Model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    List<Client> findByNomContainingOrPrenomContainingOrEmailContainingOrAdresseContaining(
            String nom, String prenom, String email, String adresse);
}
