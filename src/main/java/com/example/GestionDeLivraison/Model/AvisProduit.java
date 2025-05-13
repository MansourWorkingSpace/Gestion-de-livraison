package com.example.GestionDeLivraison.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/*@Entity
@Table(name = "avisproduit")
public class AvisProduit {
    @Id
    @ManyToOne
    @JoinColumn(name = "id_clt")
    private Client client;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_prod")
    private Produit produit;

    private String message;
    private LocalDateTime date;

    // Getters and Setters
}
 */

@Entity
@Table(name = "avisproduit")
public class AvisProduit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // <-- Single primary key

    private String message;
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "id_clt")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "id_prod")
    private Produit produit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }
}
