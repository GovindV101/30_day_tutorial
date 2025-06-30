package com.litmus7.vehicle_rental_system.exception;

public class DuplicateVehicleException extends Exception {
    public DuplicateVehicleException(String model) {
        super("Vehicle with model '" + model + "' already exists.");
    }
}
