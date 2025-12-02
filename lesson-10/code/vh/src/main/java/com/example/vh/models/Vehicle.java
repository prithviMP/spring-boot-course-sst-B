package com.example.vh.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document( collection = "vehicles")
public class Vehicle {

    @Id
    private String id;
    private String brand;
    private String model;

    private Registration registration;

    private List<Owner> owners = new ArrayList<>();

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

    public List<Owner> getOwners() {
        return owners;
    }

    public void setOwners(List<Owner> owners) {
        this.owners = owners;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    private String manufactureYear;
    private String color;
    private  String iamShyDontExposeME;

    public String getIamShyDontExposeME() {
        return iamShyDontExposeME;
    }

    public void setIamShyDontExposeME(String iamShyDontExposeME) {
        this.iamShyDontExposeME = iamShyDontExposeME;
    }

    public Vehicle() {}

    public String getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(String manufactureYear) {
        this.manufactureYear = manufactureYear;
    }

    public Vehicle(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    public String getId(){ return id;}
    public void setId(String id) {this.id = id;}
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
