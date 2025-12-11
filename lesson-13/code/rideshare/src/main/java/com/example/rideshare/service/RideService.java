package com.example.rideshare.service;

import com.example.rideshare.model.Ride;
import com.example.rideshare.repository.RideRepository;
import com.example.rideshare.exception.BadRequestException;
import com.example.rideshare.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RideService {

    private final RideRepository rideRepo;

    public RideService(RideRepository rideRepo) {
        this.rideRepo = rideRepo;
    }

    // Create a new ride request by user
    public Ride createRide(String userId,
                           String pickupLocation,
                           String dropLocation,
                           Double distanceKm,
                           Double fareAmount) {

        Ride ride = new Ride(userId, pickupLocation, dropLocation);
        ride.setDistanceKm(distanceKm != null ? distanceKm : 0.0);
        ride.setFareAmount(fareAmount != null ? fareAmount : 0.0);
        return rideRepo.save(ride);
    }

    // Get ride by id (helper)
    public Ride getRideOrThrow(String rideId) {
        return rideRepo.findById(rideId)
                .orElseThrow(() -> new NotFoundException("Ride not found: " + rideId));
    }

    // ACCEPT ride (transactional example)
    @Transactional
    public Ride acceptRide(String rideId, String driverId) {
        Ride ride = getRideOrThrow(rideId);

        if (!"REQUESTED".equals(ride.getStatus())) {
            throw new BadRequestException("Ride is not in REQUESTED state");
        }

        ride.setDriverId(driverId);
        ride.setStatus("ACCEPTED");

        return rideRepo.save(ride);
    }

    // COMPLETE ride (could also be transactional)
    @Transactional
    public Ride completeRide(String rideId, String actorUserId) {
        Ride ride = getRideOrThrow(rideId);

        if (!"ACCEPTED".equals(ride.getStatus())) {
            throw new BadRequestException("Ride is not in ACCEPTED state");
        }

        // (Optional) Verify that actorUserId is either userId or driverId
        ride.setStatus("COMPLETED");

        return rideRepo.save(ride);
    }

    // CANCEL ride
    @Transactional
    public Ride cancelRide(String rideId, String actorUserId) {
        Ride ride = getRideOrThrow(rideId);

        if ("COMPLETED".equals(ride.getStatus())) {
            throw new BadRequestException("Cannot cancel completed ride");
        }

        // (Optional) verify actorUserId is the user who booked
        ride.setStatus("CANCELLED");
        return rideRepo.save(ride);
    }
}
