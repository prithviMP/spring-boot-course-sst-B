package com.example.vh.controller.v2;


import com.example.vh.dto.VehicleRequestDTO;
import com.example.vh.dto.VehicleResponseDTO;
import com.example.vh.mapper.VehicleMapper;
import com.example.vh.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v2/vehicles")
public class VehicleControllerV2 {

    @Autowired
    private VehicleService vehicleService;


    @GetMapping
    public ResponseEntity<List<VehicleResponseDTO>> getAllVehicles() {

        List<VehicleResponseDTO> list = vehicleService.getAll().stream().map(VehicleMapper::toDTO).collect(Collectors.toList());

        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<VehicleResponseDTO> createVehicle(@RequestBody VehicleRequestDTO vehicleRequestDTO) {
        var entity = VehicleMapper.toEntity(vehicleRequestDTO); // Dto and converting it to ehicle entity
        var created = vehicleService.create(entity); // entity to vehicleService
        var response = VehicleMapper.toDTO(created);

        return ResponseEntity.ok(response);



    }
}
