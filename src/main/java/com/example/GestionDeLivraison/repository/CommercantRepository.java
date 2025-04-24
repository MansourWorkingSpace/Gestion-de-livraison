package com.example.GestionDeLivraison.repository;

import com.example.GestionDeLivraison.Model.Commercant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommercantRepository extends JpaRepository<Commercant, Integer> {

}