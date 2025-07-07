package com.litmus7.user_reg_system.controller;

import com.litmus7.user_reg_system.dto.*;
import com.litmus7.user_reg_system.service.*;
import com.litmus7.user_reg_system.exception.*;

public class UserController {
	private final UserService userService;

	public UserController() {
		this.userService = new UserServiceImplementation();
	}

	public String registerUser(String username, int age, String email, String password) {
		try {
			UserDTO user = new UserDTO(username, age, email, password);
			userService.registerUser(user);
			return "Registration successful!";
		} catch (InvalidAgeException e) {
			return "Error: " + e.getMessage();
		} catch (InvalidEmailException e) {
			return "Error: " + e.getMessage();
		} catch (WeakPasswordException e) {
			return "Error: " + e.getMessage();
		} catch (DuplicateUsernameException e) {
			return "Error: " + e.getMessage();
		} catch (DatabaseException e) {
			return "Error: Database error - " + e.getMessage();
		} catch (IllegalArgumentException e) {
			return "Error: " + e.getMessage();
		}
	}

	public UserDTO getUser(String username) {
		try {
			return userService.getUser(username);
		} catch (DatabaseException e) {
			System.err.println("Database error: " + e.getMessage());
			return null;
		}
	}
}
