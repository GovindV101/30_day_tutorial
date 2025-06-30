//package com.litmus7.vehicle_rental_system.service;
//
//import java.io.*;
//import java.util.*;
//import com.litmus7.vehicle_rental_system.model.*;
//
//public class VehicleService {
//	private List<Vehicle> vehicles = new ArrayList<>();
//	private static final int SUCCESS_CODE = 200;
//	private static final int FAILED_CODE = 404;
//	private static final int SEMI_FAILURE_CODE = 200;
//
//	public void loadVehiclesFromFile(String filename) {
//		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
//			String line;
//			while ((line = br.readLine()) != null) {
//				String[] parts = line.split(",");
//				if (parts[0].equalsIgnoreCase("Car")) {
//					vehicles.add(new Car(parts[1], parts[2], Double.parseDouble(parts[3]), Integer.parseInt(parts[4]),
//							Boolean.parseBoolean(parts[5])));
//				} else if (parts[0].equalsIgnoreCase("Bike")) {
//					vehicles.add(new Bike(parts[1], parts[2], Double.parseDouble(parts[3]),
//							Boolean.parseBoolean(parts[4]), Integer.parseInt(parts[5])));
//				}
//			}
//		} catch (IOException e) {
//			System.out.println("Error loading vehicles: " + e.getMessage());
//		}
//	}
//
//	public void addVehicle(Vehicle v) {
//		vehicles.add(v);
//	}
//
//	public void displayAllVehicles() {
//		for (Vehicle v : vehicles) {
//			System.out.println(v);
//			System.out.println("--------------------------------");
//		}
//	}
//
//	public void displayAvailableVehicles() {
//		for (Vehicle v : vehicles) {
//			if (v.isAvailable()) {
//				System.out.println(v);
//				System.out.println("--------------------------------");
//			}
//		}
//	}
//
//	public void searchByBrandOrModel(String keyword) {
//		for (Vehicle v : vehicles) {
//			if (v.getBrand().equalsIgnoreCase(keyword) || v.getModel().equalsIgnoreCase(keyword)) {
//				System.out.println(v);
//				System.out.println("--------------------------------");
//			}
//		}
//	}
//
//	public double calculateTotalRentalPrice() {
//		double total = 0;
//		for (Vehicle v : vehicles) {
//			total += v.getRentalPricePerDay();
//		}
//		return total;
//	}
//
//	public Response<Boolean> rentVehicle(String model) {
//		for (Vehicle v : vehicles) {
//			if (v.getModel().equalsIgnoreCase(model)) {
//				if (v.isAvailable()) {
//					v.rent();
//					return new Response<>(SUCCESS_CODE, "Vehicle rented successfully.", true);
//				} else {
//					return new Response<>(SEMI_FAILURE_CODE, "Vehicle is already rented.", false);
//				}
//			}
//		}
//		return new Response<>(FAILED_CODE, "Vehicle not found.", false);
//	}
//
//	public boolean returnVehicle(String model) {
//		for (Vehicle v : vehicles) {
//			if (v.getModel().equalsIgnoreCase(model) && !v.isAvailable()) {
//				v.returned();
//				return true;
//			}
//		}
//		return false;
//	}
//}

package com.litmus7.vehicle_rental_system.service;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import com.litmus7.vehicle_rental_system.exception.*;
import com.litmus7.vehicle_rental_system.model.*;

public class VehicleService {
	private final List<Vehicle> vehicles = new ArrayList<>();

	public void loadVehiclesFromFile(String filename) throws VehicleDataAccessException {
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(",");
				if (parts[0].equalsIgnoreCase("Car")) {
					vehicles.add(new Car(parts[1], parts[2], Double.parseDouble(parts[3]), Integer.parseInt(parts[4]),
							Boolean.parseBoolean(parts[5])));
				} else if (parts[0].equalsIgnoreCase("Bike")) {
					vehicles.add(new Bike(parts[1], parts[2], Double.parseDouble(parts[3]),
							Boolean.parseBoolean(parts[4]), Integer.parseInt(parts[5])));
				}
			}
		} catch (IOException | NumberFormatException e) {
			throw new VehicleDataAccessException("Failed to load vehicle data from file.", e);
		}
	}

	public void addVehicle(Vehicle vehicle) throws DuplicateVehicleException {
		boolean exists = vehicles.stream().anyMatch(v -> v.getModel().equalsIgnoreCase(vehicle.getModel()));

		if (exists) {
			throw new DuplicateVehicleException(vehicle.getModel());
		}

		vehicles.add(vehicle);
	}

	public List<Vehicle> getAllVehicles() {
		return new ArrayList<>(vehicles);
	}

	public List<Vehicle> getAvailableVehicles() {
		return vehicles.stream().filter(Vehicle::isAvailable).collect(Collectors.toList());
	}

	public List<Vehicle> searchByBrandOrModel(String keyword) {
		return vehicles.stream()
				.filter(v -> v.getBrand().equalsIgnoreCase(keyword) || v.getModel().equalsIgnoreCase(keyword))
				.collect(Collectors.toList());
	}

	public double calculateTotalRentalPrice() {
		return vehicles.stream().mapToDouble(Vehicle::getRentalPricePerDay).sum();
	}

	public Vehicle findVehicleByModel(String model) throws VehicleNotFoundException {
		return vehicles.stream().filter(v -> v.getModel().equalsIgnoreCase(model)).findFirst()
				.orElseThrow(() -> new VehicleNotFoundException(model));
	}

	public void rentVehicle(String model) throws VehicleNotFoundException, VehicleAlreadyRentedException {
		Vehicle v = findVehicleByModel(model);
		if (!v.isAvailable()) {
			throw new VehicleAlreadyRentedException(model);
		}
		v.rent();
	}

	public void returnVehicle(String model) throws VehicleNotFoundException, VehicleNotRentedException {
		Vehicle v = findVehicleByModel(model);
		if (v.isAvailable()) {
			throw new VehicleNotRentedException(model);
		}
		v.returned();
	}

}
