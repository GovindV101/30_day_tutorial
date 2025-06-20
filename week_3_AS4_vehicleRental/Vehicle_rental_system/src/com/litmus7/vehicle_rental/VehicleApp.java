package com.litmus7.vehicle_rental;
import java.util.Scanner;

import com.litmus7.vehicle_rental.dto.*;

/**
 * The {@code VehicleApp} class is the entry point of the vehicle rental application.
 * It demonstrates the creation and usage of {@link Car} and {@link Bike} objects
 * using both parameterized and default constructors.
 * 
 * The application also shows how to use the {@code inputDetails} method to
 * gather information interactively from the user via the console.
 * 
 * This class includes a {@code main} method that tests the functionality of the
 * {@link Vehicle}, {@link Car}, and {@link Bike} classes.
 * @author Govind V Namboothiri
 */
public class VehicleApp {

    /**
     * The main method serves as the entry point of the application.
     * It demonstrates:
     * Creating {@code Car} and {@code Bike} objects using both constructors
     * Displaying details of each object
     * Taking input from the user for one {@code Car} and one {@code Bike} object
     * 
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== Car (Parameterized Constructor) ===");
        Car car1 = new Car("Toyota", "Innova", 90.0, 5, true);
        car1.displayDetails();

        System.out.println("\n=== Bike (Parameterized Constructor) ===");
        Bike bike1 = new Bike("Bajaj", "Pulsar", 45.0, true, 150);
        bike1.displayDetails();
        
        System.out.println("\n=== Car (Interactive Input using Default Constructor) ===");
        Car car2 = new Car();
        car2.inputDetails(scanner); 
        car2.displayDetails();

        System.out.println("\n=== Bike (Interactive Input using Default Constructor) ===");
        Bike bike2 = new Bike();
        bike2.inputDetails(scanner);
        bike2.displayDetails();

        scanner.close();
    }
}
