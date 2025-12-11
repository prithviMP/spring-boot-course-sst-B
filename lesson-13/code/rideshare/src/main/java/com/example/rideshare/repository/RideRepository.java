package com.example.rideshare.repository;

import com.example.rideshare.model.Ride;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface RideRepository extends MongoRepository<Ride, String> {

    // All rides for a user
    List<Ride> findByUserId(String userId);

    // All rides for a user with status filter
    List<Ride> findByUserIdAndStatus(String userId, String status);

    // All rides accepted by a driver
    List<Ride> findByDriverId(String driverId);

    // All rides with a given status ordered by creation date/time
    List<Ride> findByStatusOrderByCreatedAtAsc(String status);

    // All rides on a given date
    List<Ride> findByCreatedDate(LocalDate date);

    // Custom search: pickup or drop location contains text (case-insensitive)
    @Query("{ '$or': [ " +
            " { 'pickupLocation': { $regex: ?0, $options: 'i' } }, " +
            " { 'dropLocation':   { $regex: ?0, $options: 'i' } } " +
            "] }")
    List<Ride> searchRidesByLocation(String text);
}
