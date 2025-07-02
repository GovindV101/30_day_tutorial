package com.litmus7.retail_discount_system.model;

public class PremiumCustomer implements Discountable {
    @Override
    public double applyDiscount(double totalAmount) {
        double discountRate = totalAmount > 5000 ? 0.10 : 0.07;
        return totalAmount - (discountRate * totalAmount);
    }
}
