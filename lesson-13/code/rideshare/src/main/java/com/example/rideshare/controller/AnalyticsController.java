package com.example.rideshare.controller;

import com.example.rideshare.service.RideAnalyticsService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    private final RideAnalyticsService analyticsService;

    public AnalyticsController(RideAnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    // Core API 6: Driver Earnings
    @GetMapping("/driver/{driver}/earnings")
    public Map<String, Object> driverEarnings(@PathVariable String driver) {
        Map<String, Object> stats = analyticsService.getDriverStats(driver);
        // Return earnings-focused response
        return Map.of(
                "driverId", driver,
                "totalFare", stats.getOrDefault("totalFare", 0),
                "totalRides", stats.getOrDefault("totalRides", 0),
                "completedRides", stats.getOrDefault("completedRides", 0),
                "avgDistanceKm", stats.getOrDefault("avgDistanceKm", 0)
        );
    }
}

