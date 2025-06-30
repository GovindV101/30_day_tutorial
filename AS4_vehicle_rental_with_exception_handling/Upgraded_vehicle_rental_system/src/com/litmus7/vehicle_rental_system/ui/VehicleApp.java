package com.litmus7.vehicle_rental_system.ui;

import com.litmus7.vehicle_rental_system.controller.VehicleController;
import com.litmus7.vehicle_rental_system.model.*;
import com.litmus7.vehicle_rental_system.exception.*;
import java.util.List;
import java.util.Scanner;

public class VehicleApp {
	private static VehicleController controller;
	private static final Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		try {
			controller = new VehicleController();
			System.out.println("Vehicles have been successfully loaded from file.");
		} catch (VehicleServiceAccessException e) {
			System.err.println("Initialization error: " + e.getMessage());
			e.printStackTrace();
			return;
		}

		int choice = -1;
		do {
			showMenu();
			try {
				choice = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a valid number.");
				continue;
			}

			switch (choice) {
			case 1 -> displayAllVehicles();
			case 2 -> addNewVehicle();
			case 3 -> displayAvailableVehicles();
			case 4 -> rentVehicle();
			case 5 -> returnVehicle();
			case 6 -> searchVehicle();
			case 7 -> calculateTotalRentalPrice();
			case 0 -> System.out.println("Program terminated. Thank you for using the Vehicle Rental System.");
			default -> System.out.println("Invalid option selected. Please enter a valid choice from the menu.");
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
		System.out.print("Enter your choice: ");
	}

	private static void displayAllVehicles() {
		System.out.println("\n--- Displaying All Vehicles ---");
		Response<List<Vehicle>> res = controller.getAllVehicles();
		if (res.getStatusCode() == StatusCodes.OK) {
			System.out.println(res.getMessage());
			res.getData().forEach(System.out::println);
		} else {
			System.out.println("Failed to retrieve vehicles.");
		}
	}

	private static void displayAvailableVehicles() {
		System.out.println("\n--- Displaying Available Vehicles ---");
		Response<List<Vehicle>> res = controller.getAvailableVehicles();
		if (res.getStatusCode() == StatusCodes.OK) {
			System.out.println(res.getMessage());
			res.getData().forEach(System.out::println);
		} else {
			System.out.println("Failed to retrieve available vehicles.");
		}
	}

	private static void addNewVehicle() {
		try {
			Vehicle vehicle = buildVehicleFromInput();
			Response<String> res = controller.addVehicle(vehicle);

			if (res.getStatusCode() == StatusCodes.CREATED) {
				System.out.println(res.getMessage());
			} else {
				System.out.println("Failed to add vehicle.");
			}
		} catch (InvalidVehicleTypeException e) {
			System.out.println(e.getMessage());
		}
	}

	private static Vehicle buildVehicleFromInput() throws InvalidVehicleTypeException, NumberFormatException {
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

			return new Car(brand, model, price, doors, isAuto);

		} else if (type.equalsIgnoreCase("Bike")) {
			System.out.print("Has gear? (true/false): ");
			boolean hasGear = Boolean.parseBoolean(sc.nextLine());

			System.out.print("Enter engine capacity (cc): ");
			int capacity = Integer.parseInt(sc.nextLine());

			return new Bike(brand, model, price, hasGear, capacity);
		}

		throw new InvalidVehicleTypeException(type);
	}

	private static void rentVehicle() {
		System.out.print("Enter model to rent: ");
		String rentModel = sc.nextLine();
		Response<Boolean> response = controller.rentVehicle(rentModel);

		if (response.getStatusCode() == StatusCodes.OK && Boolean.TRUE.equals(response.getData())) {
			System.out.println(response.getMessage());
		} else if (response.getStatusCode() == StatusCodes.NOT_FOUND) {
			System.out.println(response.getMessage());
		} else {
			System.out.println("Unexpected error occurred while renting vehicle.");
		}
	}

	private static void returnVehicle() {
		System.out.print("Enter model to return: ");
		String returnModel = sc.nextLine();
		Response<Boolean> response = controller.returnVehicle(returnModel);

		if (response.getStatusCode() == StatusCodes.OK && Boolean.TRUE.equals(response.getData())) {
			System.out.println(response.getMessage());
		} else if (response.getStatusCode() == StatusCodes.NOT_FOUND) {
			System.out.println(response.getMessage());
		} else {
			System.out.println("Unexpected error occurred while returning vehicle.");
		}
	}

	private static void searchVehicle() {
		System.out.print("Search vehicle by brand or model: ");
		String searchKey = sc.nextLine();
		Response<List<Vehicle>> res = controller.searchByBrandOrModel(searchKey);
		if (res.getStatusCode() == StatusCodes.OK) {
			System.out.println(res.getMessage());
			res.getData().forEach(System.out::println);
		} else {
			System.out.println("Search failed.");
		}
	}

	private static void calculateTotalRentalPrice() {
		Response<Double> res = controller.getTotalRentalPrice();
		if (res.getStatusCode() == StatusCodes.OK) {
			System.out.printf("%s: $%.2f%n", res.getMessage(), res.getData());
		} else {
			System.out.println("Could not calculate total rental price.");
		}
	}
}
