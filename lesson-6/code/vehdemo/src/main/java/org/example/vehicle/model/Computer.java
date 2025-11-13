package org.example.vehicle.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "computers")
public class Computer {

    @Id
    private String id;
    private String brand;
    private String processor;
    private int ramSize; // in GB
    private double price;

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getProcessor() { return processor; }
    public void setProcessor(String processor) { this.processor = processor; }

    public int getRamSize() { return ramSize; }
    public void setRamSize(int ramSize) { this.ramSize = ramSize; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
