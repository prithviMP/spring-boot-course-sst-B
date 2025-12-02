package com.example.vh.service;


import com.example.vh.models.Owner;
import com.example.vh.models.Vehicle;
import com.example.vh.repository.OwnerRepositry;
import com.example.vh.repository.VehicleMongoRepository;
import com.example.vh.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleMongoRepository vehicleMongoRepository;

    @Autowired
    private OwnerRepositry ownerRepositry;





//    public List<Vehicle> getAll() {
//        return vehicleRepository.findAll();
//    }
//
//    public Vehicle getById(String id) {
//        return  vehicleRepository.findById(id);
//    }
//
//    public Vehicle create(Vehicle vehicle) {
//        return vehicleRepository.save(vehicle);
//    }

    public List<Vehicle> getAll() {
        return vehicleMongoRepository.findAll();
    }

    public Vehicle getById(String id) {
        return  vehicleMongoRepository.findById(id).orElse( new Vehicle());
    }

    public Vehicle create(Vehicle vehicle) {
        return vehicleMongoRepository.save(vehicle);
    }

    // todo : write update and delete methods

    public Vehicle assignOwner(String vehicleId, String ownerId) {
        Vehicle vehicle = vehicleMongoRepository.findById(vehicleId).orElse( null);
        Owner owner = ownerRepositry.findById(ownerId).orElse(null);

        if(owner == null || vehicle == null) {
            return null;
        }

        vehicle.getOwners().add(owner);
        owner.getVehicleIds().add(vehicleId);

        vehicleMongoRepository.save(vehicle);
        ownerRepositry.save(owner);

        return vehicle;

    }


}
