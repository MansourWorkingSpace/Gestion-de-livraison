package com.example.GestionDeLivraison.service_imp;

import com.example.GestionDeLivraison.repository.AdminStatRepository;
import com.example.GestionDeLivraison.service.AdminStatService;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AdminStatServiceImpl implements AdminStatService {

    private final AdminStatRepository adminStatRepository;

    public AdminStatServiceImpl(AdminStatRepository adminStatRepository) {
        this.adminStatRepository = adminStatRepository;
    }

    @Override
    public Map<String, Object> getAdminDashboardStats() {
        Map<String, Object> stats = new HashMap<>();

        stats.put("totalClients", adminStatRepository.countTotalClients());
        stats.put("totalOrders", adminStatRepository.countTotalDeliveredOrders());
        stats.put("totalRevenue", adminStatRepository.calculateTotalRevenue());
        stats.put("cityDistribution", processCityData(adminStatRepository.getCityDistribution()));
        stats.put("topProducts", adminStatRepository.getTopProducts());

        return stats;
    }

    @Override
    public Map<String, Object> getMonthlyStatistics(int year) {
        return processMonthlyData(adminStatRepository.getMonthlyStats(year));
    }

    private Map<String, Object> processCityData(List<Map<String, Object>> rawData) {
        return rawData.stream()
                .collect(Collectors.toMap(
                        item -> (String) item.get("city"),
                        item -> item.get("count")
                ));
    }

    private Map<String, Object> processMonthlyData(List<Map<String, Object>> rawData) {
        Map<String, Object> monthlyStats = new HashMap<>();
        for (int i = 1; i <= 12; i++) {
            monthlyStats.put(String.valueOf(i), 0);
        }

        rawData.forEach(item -> {
            String month = String.valueOf(item.get("month"));
            monthlyStats.put(month, item.get("count"));
        });

        return monthlyStats;
    }
}
