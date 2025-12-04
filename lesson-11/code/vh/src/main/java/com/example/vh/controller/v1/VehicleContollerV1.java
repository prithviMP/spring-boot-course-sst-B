package com.example.vh.controller.v1;


import com.example.vh.models.Vehicle;
import com.example.vh.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicles")
public class VehicleContollerV1 {

    @Autowired
    public VehicleService vehicleService;


    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return  vehicleService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable String id) {
        Vehicle v = vehicleService.getById(id);
        return ResponseEntity.ok(v);
    }

    @PostMapping
    public ResponseEntity<Vehicle> create(@RequestBody Vehicle vehicle) {
        Vehicle v = vehicleService.create(vehicle);
        return ResponseEntity.ok(v);
    }

    //http://localhost:8082/api/v1/vehicles/<vehicleID>/owners/<OwnerID>

    @PostMapping("{vehicleId}/owners/{ownerId}")
    public Vehicle assignVehicle(@PathVariable String vehicleId, @PathVariable String ownerId) {
        return vehicleService.assignOwner(vehicleId,ownerId);
    }



}
