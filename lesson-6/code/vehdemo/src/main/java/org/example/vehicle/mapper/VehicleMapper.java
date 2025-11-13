package org.example.vehicle.mapper;

import org.example.vehicle.dto.VehicleResponseDTO;
import org.example.vehicle.model.Vehicle;
import org.example.vehicle.dto.VehicleRequestDTO;

public class VehicleMapper {

    // VehicleDTOs --> Ebtity

    public static Vehicle toEnity(VehicleRequestDTO dto){
        Vehicle v = new Vehicle();
        v.setBrand(dto.getBrand());
        v.setModel(dto.getModel());
        return v;
    }

    public static VehicleResponseDTO toRespose(Vehicle v){
        VehicleResponseDTO dto = new VehicleResponseDTO();
        //dto.setId(v.getId());
        dto.setBrand(v.getBrand());
        dto.setModel(v.getModel());

        return dto;
    }
}
