package com.litmus7.user_reg_system.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.litmus7.user_reg_system.exception.*;


public class DBConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/user_registration";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "qwerty";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL Driver not found. Please ensure MySQL Connector JAR is in the classpath.", e);
        }
    }

    public static Connection getConnection() throws DatabaseException {
        try {
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            throw new DatabaseException("Failed to connect to the database", e);
        }
    }

    public static void closeConnection(Connection connection) throws DatabaseException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DatabaseException("Error closing database connection", e);
            }
        }
    }

    public static boolean testConnection() throws DatabaseException {
        try (Connection connection = getConnection()) {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            throw new DatabaseException("Database connection test failed", e);
        }
    }
}

