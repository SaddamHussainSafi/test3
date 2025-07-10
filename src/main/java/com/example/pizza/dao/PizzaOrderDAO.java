package com.example.pizza.dao;

import com.example.pizza.model.PizzaOrder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PizzaOrderDAO {
    private final String URL = "jdbc:mysql://localhost:3306/pizza_db";
    private final String USER = "root";
    private final String PASSWORD = "";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void insert(PizzaOrder order) {
        String sql = "INSERT INTO pizza_orders (customer_name, mobile_number, pizza_size, toppings, total_bill) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, order.getCustomerName());
            stmt.setString(2, order.getMobileNumber());
            stmt.setString(3, order.getPizzaSize());
            stmt.setInt(4, order.getToppings());
            stmt.setDouble(5, order.getTotalBill());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<PizzaOrder> getAll() {
        List<PizzaOrder> orders = new ArrayList<>();
        String sql = "SELECT * FROM pizza_orders";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                PizzaOrder order = new PizzaOrder(
                        rs.getInt("id"),
                        rs.getString("customer_name"),
                        rs.getString("mobile_number"),
                        rs.getString("pizza_size"),
                        rs.getInt("toppings"),
                        rs.getDouble("total_bill")
                );
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public void update(PizzaOrder order) {
        String sql = "UPDATE pizza_orders SET customer_name=?, mobile_number=?, pizza_size=?, toppings=?, total_bill=? WHERE id=?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, order.getCustomerName());
            stmt.setString(2, order.getMobileNumber());
            stmt.setString(3, order.getPizzaSize());
            stmt.setInt(4, order.getToppings());
            stmt.setDouble(5, order.getTotalBill());
            stmt.setInt(6, order.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM pizza_orders WHERE id=?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
