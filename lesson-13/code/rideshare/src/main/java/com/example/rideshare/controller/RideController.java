package com.example.rideshare.controller;

import com.example.rideshare.dto.CreateRideRequest;
import com.example.rideshare.model.Ride;
import com.example.rideshare.service.RideService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RideController {

    private final RideService rideService;

    public RideController(RideService rideService) {
        this.rideService = rideService;
    }

    // Core API 3: Create Ride
    @PostMapping("/rides")
    public Ride createRide(@RequestBody CreateRideRequest request) {
        return rideService.createRide(
                request.getUserId(),
                request.getPickupLocation(),
                request.getDropLocation(),
                request.getDistanceKm(),
                request.getFareAmount()
        );
    }

    // Core API 4: Accept Ride
    @PostMapping("/rides/accept/{id}")
    public Ride acceptRide(@PathVariable String id,
                          @RequestParam String driverId) {
        return rideService.acceptRide(id, driverId);
    }

    // Core API 5: Complete Ride
    @PostMapping("/rides/complete/{id}")
    public Ride completeRide(@PathVariable String id,
                            @RequestParam String actorUserId) {
        return rideService.completeRide(id, actorUserId);
    }
}

