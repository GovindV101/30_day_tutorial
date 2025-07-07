package com.litmus7.user_reg_system.service;

import com.litmus7.user_reg_system.dto.*;
import com.litmus7.user_reg_system.exception.*;

public interface UserService {
	void registerUser(UserDTO user) throws InvalidAgeException, InvalidEmailException, WeakPasswordException,
			DuplicateUsernameException, DatabaseException;

	UserDTO getUser(String username) throws DatabaseException;
}
