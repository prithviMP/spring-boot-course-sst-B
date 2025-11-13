package org.example.vehicle.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "vehicles")
public class Vehicle {
    @Id
    private String id;
    private String brand;
    private String model;
    // v2 will add: private Integer year; (or color etc.)

    public Vehicle() {}
    public Vehicle(String brand, String model) {
        this.brand = brand; this.model = model;
    }

    // getters & setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
}
