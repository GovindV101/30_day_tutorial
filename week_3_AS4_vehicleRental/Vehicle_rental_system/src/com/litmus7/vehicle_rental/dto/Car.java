package com.litmus7.vehicle_rental.dto;
import java.util.Scanner;

/**
 * The {@code Car} class represents a car in the vehicle rental system.
 * It extends the {@link Vehicle} class and adds specific properties
 * such as the number of doors and whether the car is automatic.
 * 
 * This class overrides methods from {@code Vehicle} to include car-specific input and output.
 * 
 * @author Govind V Namboothiri
 */
public class Car extends Vehicle {

    /** Number of doors in the car */
    private int numberOfDoors;

    /** Indicates if the car has an automatic transmission */
    private boolean isAutomatic;

    /**
     * Default constructor initializing a car with default values.
     * - 4 doors
     * - Automatic transmission
     */
    public Car() {
        super();
        this.numberOfDoors = 4;
        this.isAutomatic = true;
    }

    /**
     * Parameterized constructor to initialize all properties of the car.
     * 
     * @param brand The brand of the car
     * @param model The model of the car
     * @param rentalPricePerDay Rental cost per day
     * @param numberOfDoors Number of doors
     * @param isAutomatic {@code true} if the car is automatic, {@code false} otherwise
     */
    public Car(String brand, String model, double rentalPricePerDay, int numberOfDoors, boolean isAutomatic) {
        super(brand, model, rentalPricePerDay);
        this.numberOfDoors = numberOfDoors;
        this.isAutomatic = isAutomatic;
    }

    /**
     * Reads car details from user input using a {@code Scanner}.
     * Also calls the parent class method to input common vehicle details.
     * 
     * @param scanner The {@code Scanner} object for reading input
     */
    @Override
    public void inputDetails(Scanner scanner) {
        super.inputDetails(scanner);
        System.out.print("Enter number of doors: ");
        numberOfDoors = Integer.parseInt(scanner.nextLine());
        System.out.print("Is it automatic (true/false)? ");
        isAutomatic = Boolean.parseBoolean(scanner.nextLine());
    }

    /**
     * Displays car-specific details in addition to the common vehicle details.
     */
    @Override
    public void displayDetails() {
        System.out.println("\n\n---Displaying Car Details---");
        super.displayDetails();
        System.out.println("Number of Doors: " + numberOfDoors);
        System.out.println("Automatic: " + (isAutomatic ? "Yes" : "No"));
    }
}

