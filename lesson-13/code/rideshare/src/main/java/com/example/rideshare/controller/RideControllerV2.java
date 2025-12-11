package com.example.rideshare.controller;

import com.example.rideshare.model.Ride;
import com.example.rideshare.service.RideQueryServiceV2;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * V2 Query Controller - For live coding demonstration
 * These endpoints use Criteria for complex queries
 */
@RestController
@RequestMapping("/api/v2/rides")
public class RideControllerV2 {

    private final RideQueryServiceV2 rideQueryService;

    public RideControllerV2(RideQueryServiceV2 rideQueryService) {
        this.rideQueryService = rideQueryService;
    }

    /**
     * API 1: Search rides by pickup OR drop location
     * TODO: Will be implemented live during lecture
     */
    @GetMapping("/search")
    public List<Ride> searchRides(@RequestParam String text) {
        return rideQueryService.searchRides(text);
    }

    /**
     * API 2: Filter rides by distance range
     * TODO: Will be implemented live during lecture
     */
    @GetMapping("/filter-distance")
    public List<Ride> filterByDistance(@RequestParam(required = false) Double min,
                                       @RequestParam(required = false) Double max) {
        return rideQueryService.filterByDistance(min, max);
    }

    /**
     * API 3: Filter rides between date range
     * TODO: Will be implemented live during lecture
     */
    @GetMapping("/filter-date-range")
    public List<Ride> filterByDateRange(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return rideQueryService.filterByDateRange(start, end);
    }

    /**
     * API 4: Sort rides by fare
     * TODO: Will be implemented live during lecture
     */
    @GetMapping("/sort")
    public List<Ride> sortByFare(@RequestParam(defaultValue = "desc") String order) {
        return rideQueryService.sortByFare(order);
    }

    /**
     * API 7: Driver active rides
     * TODO: Will be implemented live during lecture
     */
    @GetMapping("/driver/{driverId}/active-rides")
    public List<Ride> getDriverActiveRides(@PathVariable String driverId) {
        return rideQueryService.getDriverActiveRides(driverId);
    }

    /**
     * API 8: Filter rides by status + keyword search
     * TODO: Will be implemented live during lecture
     */
    @GetMapping("/filter-status")
    public List<Ride> filterByStatusAndSearch(@RequestParam(required = false) String status,
                                               @RequestParam(required = false) String search) {
        return rideQueryService.filterByStatusAndSearch(status, search);
    }

    /**
     * API 9: Advanced search with pagination
     * TODO: Will be implemented live during lecture
     */
    @GetMapping("/advanced-search")
    public List<Ride> advancedSearch(@RequestParam(required = false) String search,
                                     @RequestParam(required = false) String status,
                                     @RequestParam(required = false, defaultValue = "fareAmount") String sort,
                                     @RequestParam(required = false, defaultValue = "desc") String order,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size) {
        return rideQueryService.advancedSearch(search, status, sort, order, page, size);
    }
}

