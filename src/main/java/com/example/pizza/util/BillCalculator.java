package com.example.pizza.util;

public class BillCalculator {

    public static double calculateBill(String size, int toppings) {
        double basePrice;

        switch (size.toUpperCase()) {
            case "XL" -> basePrice = 15.00;
            case "L"  -> basePrice = 12.00;
            case "M"  -> basePrice = 10.00;
            case "S"  -> basePrice = 8.00;
            default   -> basePrice = 0.00;
        }

        double toppingPrice = toppings * 1.50;
        double subtotal = basePrice + toppingPrice;
        double totalWithTax = subtotal * 1.15;

        return Math.round(totalWithTax * 100.0) / 100.0;
    }
}
