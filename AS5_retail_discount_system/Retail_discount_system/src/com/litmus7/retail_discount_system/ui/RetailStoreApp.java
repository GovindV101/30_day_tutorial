package com.litmus7.retail_discount_system.ui;
import java.util.Scanner;
import com.litmus7.retail_discount_system.contoller.*;
import com.litmus7.retail_discount_system.exception.*;
import java.util.InputMismatchException;

public class RetailStoreApp {
    private Scanner scanner = new Scanner(System.in);

    public int getCustomerType() throws InvalidCustomerTypeException {
        System.out.println("Enter customer type (1- Regular, 2- Premium, 3- Wholesale): ");
        try {
            int type = scanner.nextInt();
            if (type < 1 || type > 3) {
                throw new InvalidCustomerTypeException("Customer type must be 1 (Regular), 2 (Premium), or 3 (Wholesale).");
            }
            return type;
        } catch (InputMismatchException e) {
            scanner.nextLine(); 
            throw new InvalidCustomerTypeException("Input must be an integer (1, 2, or 3).");
        }
    }

    public double getTotalAmount() {
        System.out.println("Enter total purchase amount: ");
        return scanner.nextDouble();
    }

    public void showResults(String customerType, double originalAmount, double discount, double finalAmount) {
        System.out.printf("Customer Type: %s%n", customerType);
        System.out.printf("Original Amount: ₹%.2f%n", originalAmount);
        System.out.printf("Discount Applied: ₹%.2f%n", discount);
        System.out.printf("Final Payable Amount: ₹%.2f%n", finalAmount);
    }

    public void showError(String message) {
        System.out.println("Error: " + message);
    }
    
    public static void main(String[] args) {
        RetailStoreApp view = new RetailStoreApp();
        RetailStoreController controller = new RetailStoreController(view);
        controller.processDiscount();
    }

}
