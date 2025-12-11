package com.example.rideshare.controller;

import org.bson.Document;
import com.example.rideshare.service.RideAnalyticsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/analytics")
public class DriverAnalyticsControllerV1 {

    private final RideAnalyticsService analyticsService;

    public DriverAnalyticsControllerV1(RideAnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    // API 10: Rides per day (for charts / dashboards)
    @GetMapping("/rides-per-day")
    public List<Document> ridesPerDay() {
        return analyticsService.ridesPerDay();
    }

    // API 11: Driver summary (total rides, completed, cancelled, avg distance, total fare)
    @GetMapping("/driver/{driverId}/summary")
    public Map<String, Object> driverSummary(@PathVariable String driverId) {
        return analyticsService.getDriverStats(driverId);
    }

    // Alias for driver stats (backward compatibility)
    @GetMapping("/driver/{driverId}/stats")
    public Map<String, Object> driverStats(@PathVariable String driverId) {
        return analyticsService.getDriverStats(driverId);
    }

    // Core API: Driver earnings (same as summary, focusing on earnings)
    @GetMapping("/driver/{driverId}/earnings")
    public Map<String, Object> driverEarnings(@PathVariable String driverId) {
        Map<String, Object> stats = analyticsService.getDriverStats(driverId);
        // Return earnings-focused response
        return Map.of(
                "driverId", driverId,
                "totalFare", stats.getOrDefault("totalFare", 0),
                "totalRides", stats.getOrDefault("totalRides", 0),
                "completedRides", stats.getOrDefault("completedRides", 0),
                "avgDistanceKm", stats.getOrDefault("avgDistanceKm", 0)
        );
    }

    // API 12: User spending
    @GetMapping("/user/{userId}/spending")
    public Map<String, Object> userSpending(@PathVariable String userId) {
        return analyticsService.getUserSpending(userId);
    }

    // API 13: Status summary - count rides grouped by status
    @GetMapping("/status-summary")
    public List<Document> statusSummary() {
        return analyticsService.getStatusSummary();
    }
}
