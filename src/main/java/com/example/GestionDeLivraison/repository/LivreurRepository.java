package com.example.GestionDeLivraison.repository;

import com.example.GestionDeLivraison.Model.Livreur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivreurRepository extends JpaRepository<Livreur, Integer> {

}