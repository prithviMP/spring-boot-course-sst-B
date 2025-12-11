package com.example.rideshare.service;

import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * V2 Analytics Service - Empty implementations for live coding demonstration
 * This service will be implemented live during the lecture
 */
@Service
public class RideAnalyticsServiceV2 {

    // Will be used during live coding implementation
    @SuppressWarnings("unused")
    private final MongoTemplate mongoTemplate;

    public RideAnalyticsServiceV2(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * API 10: Rides per day (for charts, trends etc.)
     * TODO: Implement using aggregation pipeline
     * Expected: GROUP by createdDate, COUNT, SORT, PROJECT
     */
    public List<Document> ridesPerDay() {
        // TODO: Implement this method
        // Hint: Use aggregation with group, sort, and project operations
        return new ArrayList<>();
    }

    /**
     * API 11: Driver summary - total rides, completed, cancelled, avg distance, total fare
     * TODO: Implement using aggregation pipeline
     * Expected: MATCH by driverId, GROUP with conditional counts, PROJECT
     */
    public Map<String, Object> getDriverStats(String driverId) {
        // TODO: Implement this method
        // Hint: Use MATCH → GROUP → PROJECT pattern
        // Hint: Use ConditionalOperators.when() for conditional counts
        return Map.of(
                "totalRides", 0,
                "completedRides", 0,
                "cancelledRides", 0,
                "avgDistanceKm", 0,
                "totalFare", 0
        );
    }

    /**
     * API 12: User spending summary - total completed rides & total fare
     * TODO: Implement using aggregation pipeline
     * Expected: MATCH by userId AND status=COMPLETED, GROUP, PROJECT
     */
    public Map<String, Object> getUserSpending(String userId) {
        // TODO: Implement this method
        // Hint: Use MATCH with AND condition, GROUP with count and sum, PROJECT
        return Map.of(
                "totalCompletedRides", 0,
                "totalFare", 0
        );
    }

    /**
     * API 13: Status summary - count rides grouped by status
     * TODO: Implement using aggregation pipeline
     * Expected: GROUP by status, COUNT, SORT, PROJECT
     */
    public List<Document> getStatusSummary() {
        // TODO: Implement this method
        // Hint: Use GROUP by status field, COUNT, SORT, PROJECT
        return new ArrayList<>();
    }
}

