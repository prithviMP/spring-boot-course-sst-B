package com.example.vh.repository;

import com.example.vh.models.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VehicleMongoRepository extends MongoRepository<Vehicle, String> {
}
