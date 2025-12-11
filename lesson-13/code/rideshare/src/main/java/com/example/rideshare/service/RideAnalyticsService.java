package com.example.rideshare.service;

import org.bson.Document;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class RideAnalyticsService {

    private final MongoTemplate mongoTemplate;

    public RideAnalyticsService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    // Driver stats: total rides, completed rides, cancelled rides, avg distance, total fare
    public Map<String, Object> getDriverStats(String driverId) {

        MatchOperation match = match(org.springframework.data.mongodb.core.query.Criteria
                .where("driverId").is(driverId));

        GroupOperation group = group("driverId")
                .count().as("totalRides")
                .sum(ConditionalOperators.when(org.springframework.data.mongodb.core.query.Criteria
                                .where("status").is("COMPLETED"))
                        .then(1).otherwise(0))
                .as("completedRides")
                .sum(ConditionalOperators.when(org.springframework.data.mongodb.core.query.Criteria
                                .where("status").is("CANCELLED"))
                        .then(1).otherwise(0))
                .as("cancelledRides")
                .avg("distanceKm").as("avgDistanceKm")
                .sum("fareAmount").as("totalFare");

        ProjectionOperation project = project("totalRides", "completedRides",
                "cancelledRides", "avgDistanceKm", "totalFare")
                .andExclude("_id");

        Aggregation agg = newAggregation(match, group, project);

        AggregationResults<Document> results =
                mongoTemplate.aggregate(agg, "rides", Document.class);

        Document doc = results.getUniqueMappedResult();
        if (doc == null) {
            return Map.of(
                    "totalRides", 0,
                    "completedRides", 0,
                    "cancelledRides", 0,
                    "avgDistanceKm", 0,
                    "totalFare", 0
            );
        }
        return doc;
    }

    // Rides per day (for charts, trends etc.)
    public List<Document> ridesPerDay() {

        GroupOperation group = group("createdDate")
                .count().as("totalRides");

        SortOperation sortOp = sort(Sort.by(Sort.Direction.ASC, "_id"));

        ProjectionOperation project = project("totalRides")
                .and("_id").as("date")
                .andExclude("_id");

        Aggregation agg = newAggregation(group, sortOp, project);

        AggregationResults<Document> results =
                mongoTemplate.aggregate(agg, "rides", Document.class);

        return results.getMappedResults();
    }

    // User spending summary: total rides & total fare
    public Map<String, Object> getUserSpending(String userId) {

        MatchOperation match = match(org.springframework.data.mongodb.core.query.Criteria
                .where("userId").is(userId)
                .and("status").is("COMPLETED"));

        GroupOperation group = group("userId")
                .count().as("totalCompletedRides")
                .sum("fareAmount").as("totalFare");

        ProjectionOperation project = project("totalCompletedRides", "totalFare")
                .andExclude("_id");

        Aggregation agg = newAggregation(match, group, project);

        AggregationResults<Document> results =
                mongoTemplate.aggregate(agg, "rides", Document.class);

        Document doc = results.getUniqueMappedResult();
        if (doc == null) {
            return Map.of(
                    "totalCompletedRides", 0,
                    "totalFare", 0
            );
        }
        return doc;
    }

    // Status summary: count rides grouped by status
    public List<Document> getStatusSummary() {

        GroupOperation group = group("status")
                .count().as("count");

        SortOperation sortOp = sort(Sort.by(Sort.Direction.ASC, "_id"));

        ProjectionOperation project = project("count")
                .and("_id").as("status")
                .andExclude("_id");

        Aggregation agg = newAggregation(group, sortOp, project);

        AggregationResults<Document> results =
                mongoTemplate.aggregate(agg, "rides", Document.class);

        return results.getMappedResults();
    }
}
