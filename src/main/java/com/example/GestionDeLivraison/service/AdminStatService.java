package com.example.GestionDeLivraison.service;

import java.util.List;
import java.util.Map;

public interface AdminStatService {
    Long getTotalClients();
    Long getTotalDeliveredOrders();
    Double getTotalRevenue();
    List<Map<String, Object>> getMonthlyStats(int year);
    List<Map<String, Object>> getCityDistribution();
    List<Map<String, Object>> getTopProducts();
}