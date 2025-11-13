package org.example.vehicle.contoller.v1;


import org.example.vehicle.model.Vehicle;
import org.example.vehicle.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicles")
public class VehicleControllerV1 {

    @Autowired
    private VehicleService service;

    @GetMapping
    public List<Vehicle> list() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> get(@PathVariable String id) {
        Vehicle v = service.getById(id);
        return v != null ? ResponseEntity.ok(v) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Vehicle> create(@RequestBody Vehicle vehicle) {
        Vehicle created = service.create(vehicle);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> update(@PathVariable String id, @RequestBody Vehicle vehicle) {
        Vehicle updated = service.update(id, vehicle);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        boolean deleted = service.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
