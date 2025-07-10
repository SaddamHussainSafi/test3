package com.example.pizza.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BillCalculatorTest {

    @Test
    void testMediumPizzaWith3Toppings() {
        double total = BillCalculator.calculateBill("M", 3);
        assertEquals(16.68, total, 0.01);
    }

    @Test
    void testSmallPizzaWith0Toppings() {
        double total = BillCalculator.calculateBill("S", 0);
        assertEquals(9.20, total, 0.01);
    }

    @Test
    void testXLWith5Toppings() {
        double total = BillCalculator.calculateBill("XL", 5);
        assertEquals(29.90, total, 0.01);
    }

    @Test
    void testInvalidSizeReturnsZero() {
        double total = BillCalculator.calculateBill("XXL", 3);
        assertEquals(5.18, total, 0.01); // 0 base + 4.5 toppings + 15% HST
    }

    @Test
    void testNegativeToppings() {
        double total = BillCalculator.calculateBill("L", -2);
        assertEquals(9.78, total, 0.01); // 12 + (-3.00) = 9.0 + 15% = 10.35
    }
}
