package com.litmus7.vehicle_rental_system.exception;

public class VehicleNotRentedException extends Exception {
    public VehicleNotRentedException(String model) {
        super("Vehicle '" + model + "' is not currently rented.");
    }
}
