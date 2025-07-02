package com.litmus7.retail_discount_system.model;

public class WholesaleCustomer implements Discountable {
    @Override
    public double applyDiscount(double totalAmount) {
        double discountRate = totalAmount > 10000 ? 0.15 : 0.10;
        return totalAmount - (discountRate * totalAmount);
    }
}

