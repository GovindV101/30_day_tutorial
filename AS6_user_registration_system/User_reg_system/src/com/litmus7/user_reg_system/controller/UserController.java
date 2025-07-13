package com.litmus7.user_reg_system.controller;

import com.litmus7.user_reg_system.dto.*;
import com.litmus7.user_reg_system.service.*;
import com.litmus7.user_reg_system.exception.*;
import com.litmus7.user_reg_system.util.*;

public class UserController {
	private final UserService userService;

	public UserController() {
		this.userService = new UserServiceImplementation();
	}

	public Response<UserDTO> registerUser(String username, int age, String email, String password) {
		try {
			UserDTO user = new UserDTO(username, age, email, password);
			userService.registerUser(user);
			UserDTO savedUser = userService.getUser(username); 
			return Response.success("Registration successful!", savedUser);
		} catch (InvalidAgeException e) {
			return Response.error(e.getMessage(), "INVALID_AGE");
		} catch (InvalidEmailException e) {
			return Response.error(e.getMessage(), "INVALID_EMAIL");
		} catch (WeakPasswordException e) {
			return Response.error(e.getMessage(), "WEAK_PASSWORD");
		} catch (DuplicateUsernameException e) {
			return Response.error(e.getMessage(), "DUPLICATE_USERNAME");
		} catch (DatabaseException e) {
			return Response.error("Database error - " + e.getMessage(), "DB_ERROR");
		} catch (IllegalArgumentException e) {
			return Response.error(e.getMessage(), "ILLEGAL_ARGUMENT");
		} catch (Exception e) {
			return Response.error(e.getMessage(), "UNEXPECTED ERROR OCCURED");
		}
	}

	public Response<UserDTO> getUser(String username) {
		try {
			UserDTO user = userService.getUser(username);
			if (user != null) {
				return Response.success("User found", user);
			} else {
				return Response.error("User not found", "USER_NOT_FOUND");
			}
		} catch (DatabaseException e) {
			return Response.error("Database error - " + e.getMessage(), "DB_ERROR");
		}
	}
}
