package com.litmus7.user_reg_system.ui;

import com.litmus7.user_reg_system.controller.UserController;
import com.litmus7.user_reg_system.dto.UserDTO;
import com.litmus7.user_reg_system.util.DBConnection;
import com.litmus7.user_reg_system.util.Response;
import com.litmus7.user_reg_system.exception.*;

import java.util.Scanner;

public class UserRegistration {
	private final UserController userController;
	private Scanner scanner;

	public UserRegistration() {
		this.userController = new UserController();
		this.scanner = new Scanner(System.in);
	}

	public void startRegistration() {
		System.out.println("User Registration System\n");

		try {
			if (!DBConnection.testConnection()) {
				System.err.println("Cannot connect to database. Please check your MySQL configuration.");
				return;
			}
		} catch (DatabaseException e) {
			System.err.println("Error testing database connection: " + e.getMessage());
			return;
		}

		try {
			System.out.print("Enter username: ");
			String username = scanner.nextLine().trim();

			System.out.print("Enter age: ");
			int age = Integer.parseInt(scanner.nextLine().trim());

			System.out.print("Enter email: ");
			String email = scanner.nextLine().trim();

			System.out.print("Enter password: ");
			String password = scanner.nextLine().trim();

			Response<UserDTO> response = userController.registerUser(username, age, email, password);
			System.out.println(response.getMessage());

			if (response.isSuccess()) {
				response.getData().displayDetails();
			} else {
				System.out.println("Registration failed. Error Code: " + response.getErrorCode());
			}

		} catch (NumberFormatException e) {
			System.out.println("Error: Please enter a valid number for age.");
		} catch (Exception e) {
			System.out.println("Unexpected Error: " + e.getMessage());
		} finally {
			closeScanner();
		}
	}

	private void closeScanner() {
		if (scanner != null) {
			scanner.close();
		}
	}
}
