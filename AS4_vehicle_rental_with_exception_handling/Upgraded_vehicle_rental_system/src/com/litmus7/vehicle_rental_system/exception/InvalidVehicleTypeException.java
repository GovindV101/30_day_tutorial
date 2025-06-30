package com.litmus7.vehicle_rental_system.exception;

public class InvalidVehicleTypeException extends Exception {
    public InvalidVehicleTypeException(String type) {
        super("Invalid vehicle type: " + type + ". Only 'Car' or 'Bike' are accepted.");
    }
}
