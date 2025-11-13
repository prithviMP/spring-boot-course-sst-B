package org.example.vehicle.contoller.v2;


import org.example.vehicle.dto.VehicleRequestDTO;
import org.example.vehicle.dto.VehicleResponseDTO;
import org.example.vehicle.mapper.VehicleMapper;
import org.example.vehicle.service.VehicleService;
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
    public ResponseEntity<List<VehicleResponseDTO>> getAllVehicles()
    {
        List<VehicleResponseDTO> list = vehicleService.getAll().stream().map(VehicleMapper::toRespose).collect(Collectors.toList());

        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<VehicleResponseDTO> createVehicle(@RequestBody VehicleRequestDTO request)
    {
        var entity = VehicleMapper.toEnity(request);
        var created = vehicleService.create(entity);
        var response =  VehicleMapper.toRespose(created);

        return ResponseEntity.status(201).body(response);
    }
}
