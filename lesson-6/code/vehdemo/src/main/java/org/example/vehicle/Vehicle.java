package org.example.vehicle;

public class Vehicle {

    public void drive(){

        Engine engine = new Engine();
        Wheel wheel = new Wheel();

        engine.start();
        wheel.rotate();

        System.out.println("Vehicle is moving ...");

    }
}
