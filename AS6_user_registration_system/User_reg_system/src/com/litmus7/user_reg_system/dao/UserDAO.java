package com.litmus7.user_reg_system.dao;

import com.litmus7.user_reg_system.dto.*;
import com.litmus7.user_reg_system.exception.*;

public interface UserDAO {
	void saveUser(UserDTO user) throws DatabaseException, DuplicateUsernameException;

	boolean UsernameExists(String username) throws DatabaseException;

	boolean EmailExists(String email) throws DatabaseException;

	UserDTO getUserByUsername(String username) throws DatabaseException;
}
