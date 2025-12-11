package com.example.rideshare.controller;

import com.example.rideshare.model.Ride;
import com.example.rideshare.service.RideService;
import com.example.rideshare.service.RideQueryService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/rides")
public class RideControllerV1 {

    private final RideService rideService;
    private final RideQueryService rideQueryService;

    public RideControllerV1(RideService rideService, RideQueryService rideQueryService) {
        this.rideService = rideService;
        this.rideQueryService = rideQueryService;
    }

    // API 1: Search rides by pickup OR drop location
    @GetMapping("/search")
    public List<Ride> searchRides(@RequestParam String text) {
        return rideQueryService.searchRides(text);
    }

    // API 2: Filter rides by distance range
    @GetMapping("/filter-distance")
    public List<Ride> filterByDistance(@RequestParam(required = false) Double min,
                                       @RequestParam(required = false) Double max) {
        return rideQueryService.filterByDistance(min, max);
    }

    // API 3: Filter rides between date range
    @GetMapping("/filter-date-range")
    public List<Ride> filterByDateRange(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return rideQueryService.filterByDateRange(start, end);
    }

    // API 4: Sort rides by fare
    @GetMapping("/sort")
    public List<Ride> sortByFare(@RequestParam(defaultValue = "desc") String order) {
        return rideQueryService.sortByFare(order);
    }

    // API 5: Get rides for user
    @GetMapping("/user/{userId}")
    public List<Ride> getUserRides(@PathVariable String userId) {
        return rideQueryService.getRidesForUser(userId);
    }

    // API 6: Get rides for user by status
    @GetMapping("/user/{userId}/status/{status}")
    public List<Ride> getUserRidesByStatus(@PathVariable String userId,
                                           @PathVariable String status) {
        return rideQueryService.getRidesForUserByStatus(userId, status.toUpperCase());
    }

    // API 8: Filter rides by status + keyword search
    @GetMapping("/filter-status")
    public List<Ride> filterByStatusAndSearch(@RequestParam(required = false) String status,
                                               @RequestParam(required = false) String search) {
        return rideQueryService.filterByStatusAndSearch(status, search);
    }

    // API 9: Advanced search with pagination
    @GetMapping("/advanced-search")
    public List<Ride> advancedSearch(@RequestParam(required = false) String search,
                                     @RequestParam(required = false) String status,
                                     @RequestParam(required = false, defaultValue = "fareAmount") String sort,
                                     @RequestParam(required = false, defaultValue = "desc") String order,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size) {
        return rideQueryService.advancedSearch(search, status, sort, order, page, size);
    }

    // API 14: Get rides on specific date (using path variable)
    @GetMapping("/date/{date}")
    public List<Ride> ridesOnDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return rideQueryService.getRidesForDate(date);
    }

    // Create ride request (for demo: userId passed as request param)
    @PostMapping
    public Ride createRide(@RequestParam String userId,
                           @RequestParam String pickupLocation,
                           @RequestParam String dropLocation,
                           @RequestParam(required = false) Double distanceKm,
                           @RequestParam(required = false) Double fareAmount) {
        return rideService.createRide(userId, pickupLocation, dropLocation, distanceKm, fareAmount);
    }

    // Complete ride
    @PostMapping("/{rideId}/complete")
    public Ride completeRide(@PathVariable String rideId,
                             @RequestParam String actorUserId) {
        return rideService.completeRide(rideId, actorUserId);
    }

    // Cancel ride
    @PostMapping("/{rideId}/cancel")
    public Ride cancelRide(@PathVariable String rideId,
                           @RequestParam String actorUserId) {
        return rideService.cancelRide(rideId, actorUserId);
    }
}
