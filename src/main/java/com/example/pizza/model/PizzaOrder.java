package com.example.pizza.model;

import javafx.beans.property.*;

public class PizzaOrder {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty customerName = new SimpleStringProperty();
    private final StringProperty mobileNumber = new SimpleStringProperty();
    private final StringProperty pizzaSize = new SimpleStringProperty();
    private final IntegerProperty toppings = new SimpleIntegerProperty();
    private final DoubleProperty totalBill = new SimpleDoubleProperty();

    public PizzaOrder(int id, String customerName, String mobileNumber, String pizzaSize, int toppings, double totalBill) {
        this.id.set(id);
        this.customerName.set(customerName);
        this.mobileNumber.set(mobileNumber);
        this.pizzaSize.set(pizzaSize);
        this.toppings.set(toppings);
        this.totalBill.set(totalBill);
    }

    public int getId() { return id.get(); }
    public void setId(int value) { id.set(value); }
    public IntegerProperty idProperty() { return id; }

    public String getCustomerName() { return customerName.get(); }
    public void setCustomerName(String value) { customerName.set(value); }
    public StringProperty customerNameProperty() { return customerName; }

    public String getMobileNumber() { return mobileNumber.get(); }
    public void setMobileNumber(String value) { mobileNumber.set(value); }
    public StringProperty mobileNumberProperty() { return mobileNumber; }

    public String getPizzaSize() { return pizzaSize.get(); }
    public void setPizzaSize(String value) { pizzaSize.set(value); }
    public StringProperty pizzaSizeProperty() { return pizzaSize; }

    public int getToppings() { return toppings.get(); }
    public void setToppings(int value) { toppings.set(value); }
    public IntegerProperty toppingsProperty() { return toppings; }

    public double getTotalBill() { return totalBill.get(); }
    public void setTotalBill(double value) { totalBill.set(value); }
    public DoubleProperty totalBillProperty() { return totalBill; }
}
