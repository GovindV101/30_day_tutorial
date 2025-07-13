package com.litmus7.user_reg_system;

import com.litmus7.user_reg_system.ui.*;

public class RegistrationApp {
    public static void main(String[] args) {
        UserRegistration registrationUI = null;
        
        try {
            registrationUI = new UserRegistration();
            
            registrationUI.startRegistration();
            
        } catch (Exception e) {
            System.err.println("Application error: " + e.getMessage());
            e.printStackTrace();
        } 
            
            System.out.println("\nThank you for using the User Registration System!");
        }
    }
