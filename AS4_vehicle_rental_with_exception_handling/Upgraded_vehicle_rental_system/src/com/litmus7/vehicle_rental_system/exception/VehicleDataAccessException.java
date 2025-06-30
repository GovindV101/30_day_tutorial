package com.litmus7.vehicle_rental_system.exception;

public class VehicleDataAccessException extends Exception {
    public VehicleDataAccessException(String message) {
        super(message);
    }

    public VehicleDataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
