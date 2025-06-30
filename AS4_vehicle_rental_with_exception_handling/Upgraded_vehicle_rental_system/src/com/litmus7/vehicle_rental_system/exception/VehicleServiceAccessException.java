package com.litmus7.vehicle_rental_system.exception;

public class VehicleServiceAccessException extends Exception {
    public VehicleServiceAccessException(String message) {
        super(message);
    }

    public VehicleServiceAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
