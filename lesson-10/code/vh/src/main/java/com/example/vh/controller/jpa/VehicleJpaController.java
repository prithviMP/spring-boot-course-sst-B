package com.example.vh.controller.jpa;

import com.example.vh.models.jpa.VehicleJpaEntity;
import com.example.vh.service.jpa.VehicleJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jpa/vehicles")
public class VehicleJpaController {

    @Autowired
    private VehicleJpaService service;

    @PostMapping
    public VehicleJpaEntity create(@RequestBody VehicleJpaEntity e) {
        return service.create(e);
    }

    @GetMapping
    public List<VehicleJpaEntity> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public VehicleJpaEntity getById(@PathVariable Long id) {
        return service.getById(id);
    }
}

