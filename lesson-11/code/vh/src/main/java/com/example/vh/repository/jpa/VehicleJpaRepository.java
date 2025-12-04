package com.example.vh.repository.jpa;


import com.example.vh.models.jpa.VehicleJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleJpaRepository extends JpaRepository<VehicleJpaEntity, Long> {
}

