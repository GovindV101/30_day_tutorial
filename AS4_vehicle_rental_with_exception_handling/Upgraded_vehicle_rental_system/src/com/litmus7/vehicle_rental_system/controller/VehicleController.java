package com.litmus7.vehicle_rental_system.controller;

import com.litmus7.vehicle_rental_system.model.*;
import com.litmus7.vehicle_rental_system.service.VehicleService;
import com.litmus7.vehicle_rental_system.exception.*;

import java.util.List;
import java.util.stream.Collectors;

public class VehicleController {

    private final VehicleService service;

    public VehicleController() throws VehicleServiceAccessException {
        this.service = new VehicleService();
        try {
            this.service.loadVehiclesFromFile("vehicles.txt");
        } catch (VehicleDataAccessException e) {
            throw new VehicleServiceAccessException("Service initialization failed: could not load data.", e);
        }
    }

    public Response<List<Vehicle>> getAllVehicles() {
        List<Vehicle> vehicles = service.getAllVehicles();
        return new Response<>(StatusCodes.OK, "All vehicles retrieved successfully.", vehicles);
    }

    public Response<List<Vehicle>> getAvailableVehicles() {
        List<Vehicle> available = service.getAllVehicles().stream()
                .filter(Vehicle::isAvailable)
                .collect(Collectors.toList());

        String message = available.isEmpty() ? "No available vehicles."
                : "Available vehicles retrieved successfully.";
        return new Response<>(StatusCodes.OK, message, available);
    }

    public Response<Boolean> rentVehicle(String model) {
        try {
            service.rentVehicle(model);
            return new Response<>(StatusCodes.OK, "Vehicle rented successfully.", true);
        } catch (VehicleAlreadyRentedException | VehicleNotFoundException e) {
            return new Response<>(StatusCodes.NOT_FOUND, e.getMessage(), false);
        }
    }

    public Response<Boolean> returnVehicle(String model) {
        try {
            service.returnVehicle(model);
            return new Response<>(StatusCodes.OK, "Vehicle returned successfully.", true);
        } catch (VehicleNotFoundException | VehicleNotRentedException e) {
            return new Response<>(StatusCodes.NOT_FOUND, e.getMessage(), false);
        }
    }

    public Response<List<Vehicle>> searchByBrandOrModel(String keyword) {
        List<Vehicle> results = service.searchByBrandOrModel(keyword);
        String message = results.isEmpty()
                ? "No vehicles found matching the search criteria."
                : "Search completed successfully.";
        return new Response<>(StatusCodes.OK, message, results);
    }

    public Response<Double> getTotalRentalPrice() {
        double total = service.calculateTotalRentalPrice();
        return new Response<>(StatusCodes.OK, "Total rental price calculated successfully.", total);
    }

    public Response<String> addVehicle(Vehicle vehicle) {
        try {
            service.addVehicle(vehicle);
            return new Response<>(StatusCodes.CREATED, "Vehicle added successfully.", vehicle.getModel());
        } catch (DuplicateVehicleException e) {
            return new Response<>(StatusCodes.CONFLICT, e.getMessage(), null);
        }
    }
}
