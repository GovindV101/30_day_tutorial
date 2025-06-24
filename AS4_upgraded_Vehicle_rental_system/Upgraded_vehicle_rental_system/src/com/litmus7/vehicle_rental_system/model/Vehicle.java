package com.litmus7.vehicle_rental_system.model;

public abstract class Vehicle {
    protected String brand;
    protected String model;
    protected double rentalPricePerDay;
    protected boolean isAvailable = true;

    public Vehicle() {}

    public Vehicle(String brand, String model, double rentalPricePerDay) {
        this.brand = brand;
        this.model = model;
        this.rentalPricePerDay = rentalPricePerDay;
    }

    public abstract void displayDetails();

    public String getBrand() {
    	return brand;
    	}
    public String getModel() { 
    	return model; 
    	}
    public double getRentalPricePerDay() { 
    	return rentalPricePerDay; 
    	}
    public boolean isAvailable() { 
    	return isAvailable; 
    	}

    public void rent() { 
    	isAvailable = false; 
    	}
    public void returned() { 
    	isAvailable = true; 
    	}
}
