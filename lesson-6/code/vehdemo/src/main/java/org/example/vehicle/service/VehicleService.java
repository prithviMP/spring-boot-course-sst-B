package org.example.vehicle.service;


import org.example.vehicle.model.Vehicle;
import org.example.vehicle.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository repo;

    public List<Vehicle> getAll() {
        return repo.findAll();
    }

    public Vehicle getById(String id) {
        return repo.findById(id);
    }

    public Vehicle create(Vehicle vehicle) {
        return repo.save(vehicle);
    }

    public Vehicle update(String id, Vehicle vehicle) {
        Vehicle existing = repo.findById(id);
        if (existing == null) return null;
        existing.setBrand(vehicle.getBrand());
        existing.setModel(vehicle.getModel());
        // if v2: setYear...
        return repo.save(existing);
    }

    public boolean delete(String id) {
        return repo.deleteById(id);
    }
}
