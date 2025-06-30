package com.litmus7.vehicle_rental_system.dao;

import com.litmus7.vehicle_rental_system.model.*;
import java.io.*;
import java.util.*;

public class VehicleFileDAO {
    private static final String FILE_PATH = "vehicles.txt";

    public List<Vehicle> fetchVehiclesFromFile() {
        List<Vehicle> vehicles = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String type = data[0].trim();

                if (type.equalsIgnoreCase("Car")) {
                    vehicles.add(new Car(data[1], data[2], Double.parseDouble(data[3]),
                                         Integer.parseInt(data[4]), Boolean.parseBoolean(data[5])));
                } else if (type.equalsIgnoreCase("Bike")) {
                    vehicles.add(new Bike(data[1], data[2], Double.parseDouble(data[3]),
                                          Boolean.parseBoolean(data[4]), Integer.parseInt(data[5])));
                }
            }
            System.out.println(" Vehicles loaded successfully.");
        } catch (IOException e) {
            System.out.println(" Error reading vehicle data: " + e.getMessage());
        }
        return vehicles;
    }
}
