package com.example.rideshare.controller;

import com.example.rideshare.model.Ride;
import com.example.rideshare.service.RideQueryService;
import com.example.rideshare.service.RideService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/driver")
public class DriverRideControllerV1 {

    private final RideService rideService;
    private final RideQueryService rideQueryService;

    public DriverRideControllerV1(RideService rideService, RideQueryService rideQueryService) {
        this.rideService = rideService;
        this.rideQueryService = rideQueryService;
    }

    // API 7: Driver's active rides (status = ACCEPTED)
    @GetMapping("/{driverId}/active-rides")
    public List<Ride> getDriverActiveRides(@PathVariable String driverId) {
        return rideQueryService.getDriverActiveRides(driverId);
    }

    // Driver accepts ride (transactional)
    @PostMapping("/rides/{rideId}/accept")
    public Ride acceptRide(@PathVariable String rideId,
                           @RequestParam String driverId) {
        return rideService.acceptRide(rideId, driverId);
    }

    // All pending rides (for driver to choose from)
    @GetMapping("/rides/requests")
    public List<Ride> pendingRides() {
        return rideQueryService.getPendingRidesOrdered();
    }

    // All rides for a given driver
    @GetMapping("/rides/driver/{driverId}")
    public List<Ride> driverRides(@PathVariable String driverId) {
        return rideQueryService.getRidesForDriver(driverId);
    }
}
