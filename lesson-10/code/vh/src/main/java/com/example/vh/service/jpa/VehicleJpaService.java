package com.example.vh.service.jpa;


import com.example.vh.models.jpa.VehicleJpaEntity;
import com.example.vh.repository.jpa.VehicleJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleJpaService {

    @Autowired
    private VehicleJpaRepository repo;

    public VehicleJpaEntity create(VehicleJpaEntity e) {
        return repo.save(e);
    }

    public List<VehicleJpaEntity> getAll() {
        return repo.findAll();
    }

    public VehicleJpaEntity getById(Long id) {
        return repo.findById(id).orElse(null);
    }
}
