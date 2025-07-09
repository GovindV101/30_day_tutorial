package com.litmus7.retail_product_catalog_manager.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import com.litmus7.retail_product_catalog_manager.exception.*;

public class DBConnection {
    private static String DB_URL;
    private static String DB_USER;
    private static String DB_PASSWORD;

    static {
        try (InputStream input = DBConnection.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                throw new DBConnectionException("Could not find db.properties file in classpath.");
            }

            Properties prop = new Properties();
            prop.load(input);

            DB_URL = prop.getProperty("db.url");
            DB_USER = prop.getProperty("db.username");
            DB_PASSWORD = prop.getProperty("db.password");

            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (IOException | ClassNotFoundException ex) {
            throw new DBConnectionException("Failed to load DB configuration", ex);
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            throw new DBConnectionException("Unable to establish database connection.", e);
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }

    public static boolean testConnection() {
        try (Connection connection = getConnection()) {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            System.err.println("Database connection test failed: " + e.getMessage());
            return false;
        }
    }
}