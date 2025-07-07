package com.litmus7.user_reg_system.dao;

import com.litmus7.user_reg_system.dto.*;
import com.litmus7.user_reg_system.exception.*;
import com.litmus7.user_reg_system.util.*;

import java.sql.*;

public class UserDAOImplementation implements UserDAO {

	@Override
	public void saveUser(UserDTO user) throws DatabaseException, DuplicateUsernameException {
		String sql = "INSERT INTO users (username, age, email, password) VALUES (?, ?, ?, ?)";

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql)) {

			if (UsernameExists(user.getUsername())) {
				throw new DuplicateUsernameException("Username '" + user.getUsername() + "' already exists");
			}

			if (EmailExists(user.getEmail())) {
				throw new DuplicateUsernameException("Email '" + user.getEmail() + "' already exists");
			}

			stmt.setString(1, user.getUsername());
			stmt.setInt(2, user.getAge());
			stmt.setString(3, user.getEmail());
			stmt.setString(4, user.getPassword());

			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected == 0) {
				throw new DatabaseException("Failed to save user");
			}

		} catch (SQLException e) {
			throw new DatabaseException("Database error while saving user", e);
		}
	}

	@Override
	public boolean UsernameExists(String username) throws DatabaseException {
		String sql = "SELECT COUNT(*) FROM users WHERE username = ?";

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql)) {

			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return rs.getInt(1) > 0;
			}

		} catch (SQLException e) {
			throw new DatabaseException("Database error while checking username", e);
		}

		return false;
	}

	@Override
	public boolean EmailExists(String email) throws DatabaseException {
		String sql = "SELECT COUNT(*) FROM users WHERE email = ?";

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql)) {

			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return rs.getInt(1) > 0;
			}

		} catch (SQLException e) {
			throw new DatabaseException("Database error while checking email", e);
		}

		return false;
	}

	@Override
	public UserDTO getUserByUsername(String username) throws DatabaseException {
		String sql = "SELECT username, age, email, password FROM users WHERE username = ?";

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql)) {

			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return new UserDTO(rs.getString("username"), rs.getInt("age"), rs.getString("email"),
						rs.getString("password"));
			}

		} catch (SQLException e) {
			throw new DatabaseException("Database error while retrieving user", e);
		}

		return null;
	}
}
