package com.example.GestionDeLivraison.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "admin")
@PrimaryKeyJoinColumn(name = "id_user")
public class Admin extends User {
    @OneToMany(mappedBy = "admin")
    private List<ContratCA> contratsCommercants;

    // Getters and Setters
}