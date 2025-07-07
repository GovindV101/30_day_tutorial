package com.litmus7.user_reg_ststem.util;

import com.litmus7.user_reg_system.exception.*;
import java.util.regex.Pattern;

public class ValidationUtil {
    private static final int MIN_AGE = 18;
    private static final int MAX_AGE = 60;
    private static final int MIN_PASSWORD_LENGTH = 6;
    private static final Pattern EMAIL_PATTERN = 
        Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    
    public static void validateUsername(String username) throws IllegalArgumentException {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (username.trim().length() < 3) {
            throw new IllegalArgumentException("Username must be at least 3 characters long");
        }
        if (!username.matches("^[a-zA-Z0-9_]+$")) {
            throw new IllegalArgumentException("Username can only contain letters, numbers, and underscores");
        }
    }
    
    public static void validateAge(int age) throws InvalidAgeException {
        if (age < MIN_AGE || age > MAX_AGE) {
            throw new InvalidAgeException("Age must be between " + MIN_AGE + " and " + MAX_AGE + ".");
        }
    }
    
    public static void validateEmail(String email) throws InvalidEmailException {
        if (email == null || email.trim().isEmpty()) {
            throw new InvalidEmailException("Email cannot be empty");
        }
        
        if (!email.contains("@") || !email.contains(".")) {
            throw new InvalidEmailException("Invalid email format. Must contain '@' and '.'.");
        }
        
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new InvalidEmailException("Invalid email format");
        }
    }
    
    public static void validatePassword(String password) throws WeakPasswordException {
        if (password == null || password.length() < MIN_PASSWORD_LENGTH) {
            throw new WeakPasswordException("Password too weak. Must be at least " + MIN_PASSWORD_LENGTH + " characters.");
        }
        
        if (!password.matches(".*[A-Za-z].*")) {
            throw new WeakPasswordException("Password must contain at least one letter");
        }
        
        if (!password.matches(".*[0-9].*")) {
            throw new WeakPasswordException("Password must contain at least one number");
        }
    }
}
