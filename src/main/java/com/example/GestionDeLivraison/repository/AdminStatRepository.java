package com.example.GestionDeLivraison.repository;

import com.example.GestionDeLivraison.Model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public interface AdminStatRepository extends JpaRepository<Commande, Integer> {

    // Nombre total de clients
    @Query("SELECT COUNT(u) FROM User u WHERE u.statut = 'client'")
    Long countTotalClients();

    // Nombre total de commandes livrées
    @Query("SELECT COUNT(c) FROM Commande c WHERE c.statut = 'done'")
    Long countTotalDeliveredOrders();

    // Revenu total
    @Query("SELECT SUM(c.prixTotale) FROM Commande c WHERE c.statut = 'done'")
    Double calculateTotalRevenue();

    // Statistiques mensuelles
    @Query("SELECT FUNCTION('MONTH', c.dateCmd) as month, COUNT(c) as count " +
            "FROM Commande c WHERE YEAR(c.dateCmd) = :year GROUP BY FUNCTION('MONTH', c.dateCmd)")
    List<Map<String, Object>> getMonthlyStats(int year);

    // Répartition par ville
    @Query("SELECT c.client.codePostale as city, COUNT(c) as count FROM Commande c GROUP BY c.client.codePostale")
    List<Map<String, Object>> getCityDistribution();

    // Top produits
    @Query("SELECT p.nomProd as name, COUNT(c) as count FROM Commande c JOIN c.produit p GROUP BY p.nomProd ORDER BY COUNT(c) DESC")
    List<Map<String, Object>> getTopProducts();
}