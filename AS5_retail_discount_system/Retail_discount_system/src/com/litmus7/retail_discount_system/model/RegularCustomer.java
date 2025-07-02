package com.litmus7.retail_discount_system.model;

public class RegularCustomer implements Discountable {
    @Override
    public double applyDiscount(double totalAmount) {
        return totalAmount - (0.05 * totalAmount);
    }
}

