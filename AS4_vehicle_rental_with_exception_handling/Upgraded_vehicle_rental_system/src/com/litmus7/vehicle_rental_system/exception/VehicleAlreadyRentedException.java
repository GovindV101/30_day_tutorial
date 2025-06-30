package com.litmus7.vehicle_rental_system.exception;

public class VehicleAlreadyRentedException extends Exception {
    public VehicleAlreadyRentedException(String model) {
        super("Vehicle '" + model + "' is already rented.");
    }
}
