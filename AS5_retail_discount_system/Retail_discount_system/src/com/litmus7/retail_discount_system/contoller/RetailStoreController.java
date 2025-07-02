package com.litmus7.retail_discount_system.contoller;

import com.litmus7.retail_discount_system.model.*;
import com.litmus7.retail_management_system.ui.*;
import com.litmus7.retail_discount_system.exception.*;

public class RetailStoreController {
    private RetailStoreApp view;

    public RetailStoreController(RetailStoreApp view) {
        this.view = view;
    }

    public void processDiscount() {
        try {
            int customerType = view.getCustomerType();
            double totalAmount = view.getTotalAmount();

            validatePurchaseAmount(totalAmount);

            Discountable customer = null;
            String customerName = "";

            switch (customerType) {
                case 1:
                    customer = new RegularCustomer();
                    customerName = "RegularCustomer";
                    break;
                case 2:
                    customer = new PremiumCustomer();
                    customerName = "PremiumCustomer";
                    break;
                case 3:
                    customer = new WholesaleCustomer();
                    customerName = "WholesaleCustomer";
                    break;
                default:
                    throw new InvalidCustomerTypeException(
                        "Customer type must be 1 (Regular), 2 (Premium), or 3 (Wholesale)."
                    );
            }

            double finalAmount = customer.applyDiscount(totalAmount);
            double discount = totalAmount - finalAmount;

            view.showResults(customerName, totalAmount, discount, finalAmount);

        } catch (InvalidCustomerTypeException | InvalidPurchaseAmountException e) {
            view.showError(e.getMessage());
        } catch (Exception e) {
            view.showError("An unexpected error occurred: " + e.getMessage());
        }
    }

    private void validatePurchaseAmount(double amount) throws InvalidPurchaseAmountException {
        if (amount < 0) {
            throw new InvalidPurchaseAmountException("Purchase amount cannot be negative.");
        }
    }
}

