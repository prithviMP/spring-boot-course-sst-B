package com.example.rideshare.event;


public class RideCompletedEvent {

    private String rideId;
    private String userId;

    public RideCompletedEvent(String rideId, String userId) {
        this.rideId = rideId;
        this.userId = userId;
    }

    public String getRideId() {
        return rideId;
    }

    public void setRideId(String rideId) {
        this.rideId = rideId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
