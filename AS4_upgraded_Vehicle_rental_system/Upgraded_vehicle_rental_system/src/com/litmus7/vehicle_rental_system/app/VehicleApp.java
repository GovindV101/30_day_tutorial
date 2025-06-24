package com.litmus7.vehicle_rental_system.app;

import com.litmus7.vehicle_rental_system.model.*;
import com.litmus7.vehicle_rental_system.service.*;

import java.util.Scanner;

public class VehicleApp {
    private static final VehicleService service = new VehicleService();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        service.loadVehiclesFromFile("vehicles.txt");
        System.out.println("Vehicles loaded from file.");

        int choice;
        do {
            showMenu();
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> displayAllVehicles();
                case 2 -> addNewVehicle();
                case 3 -> displayAvailableVehicles();
                case 4 -> rentVehicle();
                case 5 -> returnVehicle();
                case 6 -> searchVehicle();
                case 7 -> calculateTotalRentalPrice();
                case 0 -> System.out.println("Exiting program. Goodbye!");
                default -> System.out.println("Invalid option. Please try again.");
            }

        } while (choice != 0);
    }

    private static void showMenu() {
        System.out.println("\n====== Vehicle Rental Menu ======");
        System.out.println("1. Display All Vehicles");
        System.out.println("2. Add a New Vehicle");
        System.out.println("3. Display Available Vehicles");
        System.out.println("4. Rent a Vehicle");
        System.out.println("5. Return a Vehicle");
        System.out.println("6. Search by Brand/Model");
        System.out.println("7. Calculate Total Rental Price");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    private static void displayAllVehicles() {
        System.out.println("\n--- All Vehicles ---");
        service.displayAllVehicles();
    }

    private static void displayAvailableVehicles() {
        System.out.println("\n--- Available Vehicles ---");
        service.displayAvailableVehicles();
    }

    private static void addNewVehicle() {
        System.out.println("\n--- Add a New Vehicle ---");
        System.out.print("Enter type (Car/Bike): ");
        String type = sc.nextLine().trim();

        System.out.print("Enter brand: ");
        String brand = sc.nextLine();

        System.out.print("Enter model: ");
        String model = sc.nextLine();

        System.out.print("Enter rental price per day: ");
        double price = Double.parseDouble(sc.nextLine());

        if (type.equalsIgnoreCase("Car")) {
            System.out.print("Enter number of doors: ");
            int doors = Integer.parseInt(sc.nextLine());

            System.out.print("Is it automatic? (true/false): ");
            boolean isAuto = Boolean.parseBoolean(sc.nextLine());

            service.addVehicle(new Car(brand, model, price, doors, isAuto));

        } else if (type.equalsIgnoreCase("Bike")) {
            System.out.print("Has gear? (true/false): ");
            boolean hasGear = Boolean.parseBoolean(sc.nextLine());

            System.out.print("Enter engine capacity (cc): ");
            int capacity = Integer.parseInt(sc.nextLine());

            service.addVehicle(new Bike(brand, model, price, hasGear, capacity));
        } else {
            System.out.println("Invalid vehicle type.");
        }
    }

    private static void rentVehicle() {
        System.out.print("Enter model to rent: ");
        String rentModel = sc.nextLine();
        if (service.rentVehicle(rentModel)) {
            System.out.println(rentModel + " rented successfully.");
        } else {
            System.out.println("Vehicle not available or already rented.");
            rentVehicle();
        }
    }

    private static void returnVehicle() {
        System.out.print("Enter model to return: ");
        String returnModel = sc.nextLine();
        if (service.returnVehicle(returnModel)) {
            System.out.println(returnModel + " returned successfully.");
        } else {
            System.out.println("Vehicle not found or not rented.");
            returnVehicle();
        }
    }

    private static void searchVehicle() {
        System.out.print("Search vehicle by brand or model: ");
        String searchKey = sc.nextLine();
        service.searchByBrandOrModel(searchKey);
    }

    private static void calculateTotalRentalPrice() {
        double total = service.calculateTotalRentalPrice();
        System.out.println("Total Rental Price of All Vehicles: $" + total);
    }
}

