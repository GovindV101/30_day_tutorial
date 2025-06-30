package com.litmus7.vehicle_rental_system.model;

public class Bike extends Vehicle {
	private boolean hasGear;
	private int engineCapacity;

	public Bike(String brand, String model, double rentalPricePerDay, boolean hasGear, int engineCapacity) {
		super(brand, model, rentalPricePerDay);
		this.hasGear = hasGear;
		this.engineCapacity = engineCapacity;
	}

//	@Override
//	public void displayDetails() {
//		System.out.println("Bike: " + brand + " " + model + ", Price/Day: $" + rentalPricePerDay + ", Gear: " + hasGear
//				+ ", Engine: " + engineCapacity + "cc" + ", Available: " + isAvailable);
//	}
	@Override
	public String toString() {
	    return "Bike - " + super.toString() + ", Gear: " + hasGear + ", Engine: " + engineCapacity + "cc";
	}
}
