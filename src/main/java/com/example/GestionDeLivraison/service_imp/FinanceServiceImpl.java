package com.example.GestionDeLivraison.service_imp;

import com.example.GestionDeLivraison.Model.Commande;
import com.example.GestionDeLivraison.service.FinanceService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FinanceServiceImpl implements FinanceService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Map<String, Object> getFinancialSummary() {
        Double totalRevenue = entityManager.createQuery(
                "SELECT COALESCE(SUM(c.prixTotale), 0) FROM Commande c WHERE c.estpayee = true",
                Double.class
        ).getSingleResult();

        Double pendingPayments = entityManager.createQuery(
                "SELECT COALESCE(SUM(c.prixTotale), 0) FROM Commande c WHERE c.estpayee = false",
                Double.class
        ).getSingleResult();

        Long transactionCount = entityManager.createQuery(
                "SELECT COUNT(c) FROM Commande c",
                Long.class
        ).getSingleResult();

        List<Object[]> monthlyResults = entityManager.createQuery(
                "SELECT FUNCTION('MONTHNAME', c.dateCmd), SUM(c.prixTotale) " +
                        "FROM Commande c WHERE c.estpayee = true AND c.dateCmd IS NOT NULL " +
                        "GROUP BY FUNCTION('MONTHNAME', c.dateCmd), FUNCTION('MONTH', c.dateCmd) " +
                        "ORDER BY FUNCTION('MONTH', c.dateCmd)",
                Object[].class
        ).getResultList();

        List<Map<String, Object>> monthlyRevenue = monthlyResults.stream()
                .map(row -> {
                    Map<String, Object> revenueMap = new HashMap<>();
                    revenueMap.put("month", (String) row[0]);
                    revenueMap.put("revenue", ((Number) row[1]).doubleValue());
                    return revenueMap;
                })
                .collect(Collectors.toList());

        if (monthlyRevenue.isEmpty()) {
            Map<String, Object> defaultRevenue = new HashMap<>();
            defaultRevenue.put("month", "No Data");
            defaultRevenue.put("revenue", 0.0);
            monthlyRevenue.add(defaultRevenue);
        }

        List<Commande> recentCommandes = entityManager.createQuery(
                "SELECT c FROM Commande c LEFT JOIN FETCH c.client WHERE c.dateCmd IS NOT NULL " +
                        "ORDER BY c.dateCmd DESC",
                Commande.class
        ).setMaxResults(5).getResultList();

        List<Map<String, Object>> recentTransactions = recentCommandes.stream()
                .map(c -> {
                    Map<String, Object> transactionMap = new HashMap<>();
                    transactionMap.put("id", c.getIdCmd());
                    transactionMap.put("date", c.getDateCmd() != null ?
                            c.getDateCmd().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) : "N/A");
                    transactionMap.put("clientName", c.getClient() != null ?
                            c.getClient().getNom() + " " + c.getClient().getPrenom() : "Unknown");
                    transactionMap.put("type", "Order");
                    transactionMap.put("amount", c.getPrixTotale() != null ? c.getPrixTotale() : 0.0);
                    transactionMap.put("status", c.getEstpayee() ? "Completed" : "Pending");
                    transactionMap.put("paymentMethod", c.getTlf() != null ? "Card" : "Unknown");
                    transactionMap.put("reference", "REF" + c.getIdCmd());
                    return transactionMap;
                })
                .collect(Collectors.toList());

        System.out.println("Total Revenue: " + totalRevenue);
        System.out.println("Pending Payments: " + pendingPayments);
        System.out.println("Transaction Count: " + transactionCount);
        System.out.println("Monthly Revenue Results: " + monthlyResults.size());
        monthlyResults.forEach(row -> System.out.println("Month: " + row[0] + ", Revenue: " + row[1]));

        Map<String, Object> summary = new HashMap<>();
        summary.put("totalRevenue", totalRevenue);
        summary.put("pendingPayments", pendingPayments);
        summary.put("transactionCount", transactionCount.intValue());
        summary.put("monthlyRevenue", monthlyRevenue);
        summary.put("recentTransactions", recentTransactions);
        System.out.println("Summary: " + summary);

        return summary;
    }
}