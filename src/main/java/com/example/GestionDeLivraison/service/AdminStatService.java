package com.example.GestionDeLivraison.service;

import java.util.Map;

public interface AdminStatService {
    Map<String, Object> getAdminDashboardStats();
    Map<String, Object> getMonthlyStatistics(int year);
}