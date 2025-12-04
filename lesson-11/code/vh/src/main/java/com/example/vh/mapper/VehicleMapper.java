package com.example.vh.mapper;

import com.example.vh.dto.VehicleRequestDTO;
import com.example.vh.dto.VehicleResponseDTO;
import com.example.vh.models.Vehicle;

public class VehicleMapper {

    // todo - VehicleDTO --> Entity(Model Object )

    public static Vehicle toEntity(VehicleRequestDTO dto){
        Vehicle v = new Vehicle();
        v.setBrand(dto.getBrand());
        v.setModel(dto.getModel());

        return v;
    }

    // todo - Entity --> VehicleDTO

    public static VehicleResponseDTO toDTO(Vehicle v){
        VehicleResponseDTO dto = new VehicleResponseDTO();
        dto.setId(v.getId());
        dto.setBrand(v.getBrand());
        dto.setModel(v.getModel());
        return dto;
    }
}
