package com.litmus7.vehicle_rental.dto;

import java.util.Scanner;

/**
 * The {@code Bike} class represents a bike in the vehicle rental system.
 * It extends the {@link Vehicle} class and includes bike-specific attributes
 * such as whether it has gear and its engine capacity in cc.
 * 
 * This class overrides the {@code inputDetails} and {@code displayDetails} methods
 * to handle bike-specific information.
 * @author Govind V Namboothiri
 */
public class Bike extends Vehicle {

    /** Indicates if the bike has a gear system */
    private boolean hasGear;

    /** The engine capacity of the bike in cubic centimeters (cc) */
    private int engineCapacity;

    /**
     * Default constructor initializing a bike with:
     * - No gear system
     * - 100cc engine capacity
     */
    public Bike() {
        super();
        this.hasGear = false;
        this.engineCapacity = 100;
    }

    /**
     * Parameterized constructor to initialize all properties of the bike.
     * 
     * @param brand The brand of the bike
     * @param model The model of the bike
     * @param rentalPricePerDay The rental price per day
     * @param hasGear {@code true} if the bike has gears, {@code false} otherwise
     * @param engineCapacity Engine capacity in cc
     */
    public Bike(String brand, String model, double rentalPricePerDay, boolean hasGear, int engineCapacity) {
        super(brand, model, rentalPricePerDay);
        this.hasGear = hasGear;
        this.engineCapacity = engineCapacity;
    }

    /**
     * Reads bike details from user input using a {@code Scanner}.
     * Also calls the parent class method to input general vehicle details.
     * 
     * @param scanner The {@code Scanner} object for reading input
     */
    @Override
    public void inputDetails(Scanner scanner) {
        super.inputDetails(scanner);
        System.out.print("Does the bike have gears (true/false)? ");
        hasGear = Boolean.parseBoolean(scanner.nextLine());
        System.out.print("Enter engine capacity (in cc): ");
        engineCapacity = Integer.parseInt(scanner.nextLine());
    }

    /**
     * Displays bike-specific details in addition to the general vehicle details.
     */
    @Override
    public void displayDetails() {
        System.out.println("\n\n---Displaying Bike Details---");
        super.displayDetails();
        System.out.println("Has Gear: " + (hasGear ? "Yes" : "No"));
        System.out.println("Engine Capacity: " + engineCapacity + "cc");
    }
}
