package com.litmus7.vehicle_rental_system.service;
import java.io.*;
import java.util.*;
import com.litmus7.vehicle_rental_system.model.*;

public class VehicleService {
    private List<Vehicle> vehicles = new ArrayList<>();

    public void loadVehiclesFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equalsIgnoreCase("Car")) {
                    vehicles.add(new Car(parts[1], parts[2],
                        Double.parseDouble(parts[3]),
                        Integer.parseInt(parts[4]),
                        Boolean.parseBoolean(parts[5])));
                } else if (parts[0].equalsIgnoreCase("Bike")) {
                    vehicles.add(new Bike(parts[1], parts[2],
                        Double.parseDouble(parts[3]),
                        Boolean.parseBoolean(parts[4]),
                        Integer.parseInt(parts[5])));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading vehicles: " + e.getMessage());
        }
    }

    public void addVehicle(Vehicle v) {
        vehicles.add(v);
    }

    public void displayAllVehicles() {
        for (Vehicle v : vehicles) {
            v.displayDetails();
            System.out.println("--------------------------------");
        }
    }

    public void displayAvailableVehicles() {
        for (Vehicle v : vehicles) {
            if (v.isAvailable()) {
                v.displayDetails();
                System.out.println("--------------------------------");
            }
        }
    }

    public void searchByBrandOrModel(String keyword) {
        for (Vehicle v : vehicles) {
            if (v.getBrand().equalsIgnoreCase(keyword) || v.getModel().equalsIgnoreCase(keyword)) {
                v.displayDetails();
                System.out.println("--------------------------------");
            }
        }
    }

    public double calculateTotalRentalPrice() {
        double total = 0;
        for (Vehicle v : vehicles) {
            total += v.getRentalPricePerDay();
        }
        return total;
    }

    public boolean rentVehicle(String model) {
        for (Vehicle v : vehicles) {
            if (v.getModel().equalsIgnoreCase(model) && v.isAvailable()) {
                v.rent();
                return true;
            }
        }
        return false;
    }

    public boolean returnVehicle(String model) {
        for (Vehicle v : vehicles) {
            if (v.getModel().equalsIgnoreCase(model) && !v.isAvailable()) {
                v.returned();
                return true;
            }
        }
        return false;
    }
}

