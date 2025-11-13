package org.example.vehicle.dto;

import jakarta.validation.constraints.NotBlank;

public class VehicleRequestDTO {


    @NotBlank( message = "Brand should not be empty")
    private String brand;

    @NotBlank(message = "model is required")
    private String model;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
