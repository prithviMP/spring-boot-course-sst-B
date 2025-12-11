package com.example.rideshare.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDate;

@Document(collection = "rides")
public class Ride {

    @Id
    private String id;

    // Who requested the ride
    private String userId;

    // Which driver accepted
    private String driverId;

    private String pickupLocation;
    private String dropLocation;

    // REQUESTED / ACCEPTED / COMPLETED / CANCELLED
    private String status;

    // For analytics
    private Double distanceKm;   // e.g. 12.5
    private Double fareAmount;   // e.g. 250.0

    private Instant createdAt;        // exact timestamp
    private LocalDate createdDate;    // only date part for grouping

    public Ride() {}

    public Ride(String userId, String pickupLocation, String dropLocation) {
        this.userId = userId;
        this.pickupLocation = pickupLocation;
        this.dropLocation = dropLocation;
        this.status = "REQUESTED";
        this.createdAt = Instant.now();
        this.createdDate = LocalDate.now();
        this.distanceKm = 0.0;
        this.fareAmount = 0.0;
    }

    // getters & setters

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getUserId() { return userId; }

    public void setUserId(String userId) { this.userId = userId; }

    public String getDriverId() { return driverId; }

    public void setDriverId(String driverId) { this.driverId = driverId; }

    public String getPickupLocation() { return pickupLocation; }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getDropLocation() { return dropLocation; }

    public void setDropLocation(String dropLocation) {
        this.dropLocation = dropLocation;
    }

    public String getStatus() { return status; }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getDistanceKm() { return distanceKm; }

    public void setDistanceKm(Double distanceKm) {
        this.distanceKm = distanceKm;
    }

    public Double getFareAmount() { return fareAmount; }

    public void setFareAmount(Double fareAmount) {
        this.fareAmount = fareAmount;
    }

    public Instant getCreatedAt() { return createdAt; }

    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    public LocalDate getCreatedDate() { return createdDate; }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
}
