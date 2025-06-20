package com.litmus7.vehicle_rental.dto;

import java.util.Scanner;

/**
 * The {@code Vehicle} class represents a generic vehicle in a vehicle rental system.
 * It includes details like brand, model, and rental price per day.
 * It also provides methods to input and display vehicle details.
 * @author Govind V Namboothiri
 */
public class Vehicle {
    
    /** The brand of the vehicle */
    protected String brand;
    
    /** The model of the vehicle */
    protected String model;
    
    /** The rental price of the vehicle per day */
    double rentalPricePerDay;

    /**
     * Default constructor that initializes the vehicle
     * with default values ("Unknown" and 0.0).
     */
    public Vehicle() {
        brand = "Unknown";
        model = "Unknown";
        rentalPricePerDay = 0.0;
    }

    /**
     * Parameterized constructor to initialize a vehicle
     * with specified brand, model, and rental price.
     *
     * @param brand The brand of the vehicle
     * @param model The model of the vehicle
     * @param rentalPricePerDay The rental price per day
     */
    public Vehicle(String brand, String model, double rentalPricePerDay) {
        this.brand = brand;
        this.model = model;
        this.rentalPricePerDay = rentalPricePerDay;
    }

    /**
     * Takes user input from a {@code Scanner} to populate vehicle details.
     *
     * @param scanner The {@code Scanner} object used to read user input
     */
    public void inputDetails(Scanner scanner) {
        System.out.print("Enter vehicle brand: ");
        brand = scanner.nextLine();
        System.out.print("Enter vehicle model: ");
        model = scanner.nextLine();
        System.out.print("Enter rental price per day: ");
        rentalPricePerDay = Double.parseDouble(scanner.nextLine());
    }

    /**
     * Displays the vehicle's details to the console.
     */
    public void displayDetails() {
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Rental Price/Day: $" + rentalPricePerDay);
    }
}
