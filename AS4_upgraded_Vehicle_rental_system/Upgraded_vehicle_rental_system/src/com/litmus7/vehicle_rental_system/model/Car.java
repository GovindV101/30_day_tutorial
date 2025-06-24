package com.litmus7.vehicle_rental_system.model;


public class Car extends Vehicle {
    private int numberOfDoors;
    private boolean isAutomatic;

    public Car(String brand, String model, double rentalPricePerDay, int numberOfDoors, boolean isAutomatic) {
        super(brand, model, rentalPricePerDay);
        this.numberOfDoors = numberOfDoors;
        this.isAutomatic = isAutomatic;
    }

    @Override
    public void displayDetails() {
        System.out.println("Car: " + brand + " " + model +
            ", Price/Day: $" + rentalPricePerDay +
            ", Doors: " + numberOfDoors +
            ", Automatic: " + isAutomatic +
            ", Available: " + isAvailable);
    }
}
