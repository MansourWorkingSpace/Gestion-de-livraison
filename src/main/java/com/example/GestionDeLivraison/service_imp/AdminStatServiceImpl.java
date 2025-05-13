package com.example.GestionDeLivraison.service_imp;

import com.example.GestionDeLivraison.repository.AdminStatRepository;
import com.example.GestionDeLivraison.service.AdminStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AdminStatServiceImpl implements AdminStatService {

    private final AdminStatRepository adminStatRepository;

    @Autowired
    public AdminStatServiceImpl(AdminStatRepository adminStatRepository) {
        this.adminStatRepository = adminStatRepository;
    }

    @Override
    public Map<String, Object> getAdminDashboardStats() {
        Map<String, Object> stats = new HashMap<>();

        // Basic statistics
        stats.put("totalClients", adminStatRepository.countTotalClients());
        stats.put("totalOrders", adminStatRepository.countTotalDeliveredOrders());
        stats.put("totalRevenue", adminStatRepository.calculateTotalRevenue());

        // City distribution
        List<Map<String, Object>> cityDistribution = adminStatRepository.getCityDistribution();
        Map<String, Object> cityStats = cityDistribution.stream()
                .collect(Collectors.toMap(
                        item -> (String) item.get("city"),
                        item -> item.get("count")
                ));
        stats.put("cityDistribution", cityStats);

        // Top products
        stats.put("topProducts", adminStatRepository.getTopProducts());

        return stats;
    }

    @Override
    public Map<String, Object> getMonthlyStatistics(int year) {
        List<Map<String, Object>> monthlyData = adminStatRepository.getMonthlyStats(year);
        Map<String, Object> monthlyStats = new HashMap<>();

        // Initialize all months with 0
        for (int i = 1; i <= 12; i++) {
            monthlyStats.put(String.valueOf(i), 0L);
        }

        // Fill in actual data
        monthlyData.forEach(item -> {
            String month = String.valueOf(item.get("month"));
            monthlyStats.put(month, item.get("count"));
        });

        return monthlyStats;
    }
}
