package com.example.GestionDeLivraison.service;

import com.example.GestionDeLivraison.dto.CommandeSummaryDTO; // Add this import
import java.util.List;
import java.util.Map;

public interface DashboardService {
    List<CommandeSummaryDTO> getRecentCommandes(int page, int size); // Changed return type
    List<?> getTopClients(int page, int size);
    Object updateCommandeStatus(Integer id, String statut);
    Map<String, Object> getDeliveryZones();
    List<Map<String, Object>> getStatsCards();
    Map<String, Object> getDeliveryStats();
    Map<String, Object> getMonthlyStats();
}