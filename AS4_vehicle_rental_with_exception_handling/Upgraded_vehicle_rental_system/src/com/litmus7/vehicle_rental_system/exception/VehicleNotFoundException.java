package com.litmus7.vehicle_rental_system.exception;

public class VehicleNotFoundException extends Exception {
    public VehicleNotFoundException(String model) {
        super("Vehicle with model '" + model + "' not found.");
    }
}
