package com.litmus7.user_reg_system.service;

import com.litmus7.user_reg_system.dto.*;
import com.litmus7.user_reg_system.dao.*;
import com.litmus7.user_reg_system.exception.*;
import com.litmus7.user_reg_system.util.*;

public class UserServiceImplementation implements UserService {
    private final UserDAO userDAO;
    
    public UserServiceImplementation() {
        this.userDAO = new UserDAOImplementation();
    }
    
    @Override
    public void registerUser(UserDTO user) throws InvalidAgeException, InvalidEmailException, 
                                                 WeakPasswordException, DuplicateUsernameException, 
                                                 DatabaseException {

        ValidationUtil.validateUsername(user.getUsername());
        ValidationUtil.validateAge(user.getAge());
        ValidationUtil.validateEmail(user.getEmail());
        ValidationUtil.validatePassword(user.getPassword());
        
        userDAO.saveUser(user);
    }
    
    @Override
    public UserDTO getUser(String username) throws DatabaseException {
        return userDAO.getUserByUsername(username);
    }
}
