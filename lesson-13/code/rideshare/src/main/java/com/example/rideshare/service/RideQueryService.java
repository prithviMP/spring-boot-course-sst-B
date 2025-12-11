package com.example.rideshare.service;

import com.example.rideshare.model.Ride;
import com.example.rideshare.repository.RideRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class RideQueryService {

    private final RideRepository rideRepo;
    private final MongoTemplate mongoTemplate;

    public RideQueryService(RideRepository rideRepo, MongoTemplate mongoTemplate) {
        this.rideRepo = rideRepo;
        this.mongoTemplate = mongoTemplate;
    }

    public List<Ride> getRidesForUser(String userId) {
        return rideRepo.findByUserId(userId);
    }

    public List<Ride> getRidesForUserByStatus(String userId, String status) {
        return rideRepo.findByUserIdAndStatus(userId, status);
    }

    public List<Ride> getRidesForDriver(String driverId) {
        return rideRepo.findByDriverId(driverId);
    }

    public List<Ride> getPendingRidesOrdered() {
        return rideRepo.findByStatusOrderByCreatedAtAsc("REQUESTED");
    }

    public List<Ride> getRidesForDate(LocalDate date) {
        return rideRepo.findByCreatedDate(date);
    }

    public List<Ride> searchRides(String text) {
        return rideRepo.searchRidesByLocation(text);
    }

    // Filter rides by distance range (min-max)
    public List<Ride> filterByDistance(Double min, Double max) {
        Criteria criteria = Criteria.where("distanceKm")
                .gte(min != null ? min : 0)
                .lte(max != null ? max : Double.MAX_VALUE);
        
        Query query = new Query(criteria);
        return mongoTemplate.find(query, Ride.class);
    }

    // Filter rides between date range
    public List<Ride> filterByDateRange(LocalDate start, LocalDate end) {
        Criteria criteria = Criteria.where("createdDate")
                .gte(start)
                .lte(end);
        
        Query query = new Query(criteria);
        return mongoTemplate.find(query, Ride.class);
    }

    // Sort rides by fare amount
    public List<Ride> sortByFare(String order) {
        Sort sort = Sort.by(order != null && order.equalsIgnoreCase("asc") 
                ? Sort.Direction.ASC 
                : Sort.Direction.DESC, "fareAmount");
        
        Query query = new Query().with(sort);
        return mongoTemplate.find(query, Ride.class);
    }

    // Filter rides by status and keyword search (AND + OR combo)
    public List<Ride> filterByStatusAndSearch(String status, String search) {
        Criteria criteria = new Criteria();
        
        // Build AND conditions
        if (status != null && !status.isEmpty() && search != null && !search.isEmpty()) {
            // Both status and search provided
            Pattern pattern = Pattern.compile(search, Pattern.CASE_INSENSITIVE);
            criteria = Criteria.where("status").is(status.toUpperCase())
                    .andOperator(new Criteria().orOperator(
                            Criteria.where("pickupLocation").regex(pattern),
                            Criteria.where("dropLocation").regex(pattern)
                    ));
        } else if (status != null && !status.isEmpty()) {
            // Only status provided
            criteria = Criteria.where("status").is(status.toUpperCase());
        } else if (search != null && !search.isEmpty()) {
            // Only search provided
            Pattern pattern = Pattern.compile(search, Pattern.CASE_INSENSITIVE);
            criteria = new Criteria().orOperator(
                    Criteria.where("pickupLocation").regex(pattern),
                    Criteria.where("dropLocation").regex(pattern)
            );
        }
        
        Query query = new Query(criteria);
        return mongoTemplate.find(query, Ride.class);
    }

    // Advanced search with multiple criteria + pagination
    public List<Ride> advancedSearch(String search, String status, String sortField, 
                                     String order, int page, int size) {
        Criteria criteria = new Criteria();
        
        // Build criteria based on provided parameters
        if (status != null && !status.isEmpty() && search != null && !search.isEmpty()) {
            // Both status and search provided
            Pattern pattern = Pattern.compile(search, Pattern.CASE_INSENSITIVE);
            criteria = Criteria.where("status").is(status.toUpperCase())
                    .andOperator(new Criteria().orOperator(
                            Criteria.where("pickupLocation").regex(pattern),
                            Criteria.where("dropLocation").regex(pattern)
                    ));
        } else if (status != null && !status.isEmpty()) {
            // Only status provided
            criteria = Criteria.where("status").is(status.toUpperCase());
        } else if (search != null && !search.isEmpty()) {
            // Only search provided
            Pattern pattern = Pattern.compile(search, Pattern.CASE_INSENSITIVE);
            criteria = new Criteria().orOperator(
                    Criteria.where("pickupLocation").regex(pattern),
                    Criteria.where("dropLocation").regex(pattern)
            );
        }
        
        Query query = new Query(criteria);
        
        // Sorting
        if (sortField != null && !sortField.isEmpty()) {
            Sort sort = Sort.by(order != null && order.equalsIgnoreCase("asc") 
                    ? Sort.Direction.ASC 
                    : Sort.Direction.DESC, sortField);
            query.with(sort);
        }
        
        // Pagination
        Pageable pageable = PageRequest.of(page, size);
        query.with(pageable);
        
        return mongoTemplate.find(query, Ride.class);
    }

    // Get active rides for a driver (status = ACCEPTED)
    public List<Ride> getDriverActiveRides(String driverId) {
        Criteria criteria = Criteria.where("driverId").is(driverId)
                .and("status").is("ACCEPTED");
        
        Query query = new Query(criteria);
        return mongoTemplate.find(query, Ride.class);
    }
}
