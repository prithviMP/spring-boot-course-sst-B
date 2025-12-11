package com.example.rideshare.controller;

import org.bson.Document;
import com.example.rideshare.service.RideAnalyticsServiceV2;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * V2 Analytics Controller - For live coding demonstration
 * These endpoints will be implemented live during the lecture
 */
@RestController
@RequestMapping("/api/v2/analytics")
public class DriverAnalyticsControllerV2 {

    private final RideAnalyticsServiceV2 analyticsService;

    public DriverAnalyticsControllerV2(RideAnalyticsServiceV2 analyticsService) {
        this.analyticsService = analyticsService;
    }

    /**
     * API 10: Rides per day (for charts / dashboards)
     * TODO: Will be implemented live during lecture
     */
    @GetMapping("/rides-per-day")
    public List<Document> ridesPerDay() {
        return analyticsService.ridesPerDay();
    }

    /**
     * API 11: Driver summary (total rides, completed, cancelled, avg distance, total fare)
     * TODO: Will be implemented live during lecture
     */
    @GetMapping("/driver/{driverId}/summary")
    public Map<String, Object> driverSummary(@PathVariable String driverId) {
        return analyticsService.getDriverStats(driverId);
    }

    /**
     * Alias for driver stats (backward compatibility)
     * TODO: Will be implemented live during lecture
     */
    @GetMapping("/driver/{driverId}/stats")
    public Map<String, Object> driverStats(@PathVariable String driverId) {
        return analyticsService.getDriverStats(driverId);
    }

    /**
     * API 12: User spending
     * TODO: Will be implemented live during lecture
     */
    @GetMapping("/user/{userId}/spending")
    public Map<String, Object> userSpending(@PathVariable String userId) {
        return analyticsService.getUserSpending(userId);
    }

    /**
     * API 13: Status summary - count rides grouped by status
     * TODO: Will be implemented live during lecture
     */
    @GetMapping("/status-summary")
    public List<Document> statusSummary() {
        return analyticsService.getStatusSummary();
    }
}

