package com.example.pizza.controller;

import com.example.pizza.dao.PizzaOrderDAO;
import com.example.pizza.model.PizzaOrder;
import com.example.pizza.util.BillCalculator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PizzaController {

    @FXML private TextField txtCustomerName;
    @FXML private TextField txtMobileNumber;
    @FXML private TextField txtToppings;

    @FXML private CheckBox cbXL;
    @FXML private CheckBox cbL;
    @FXML private CheckBox cbM;
    @FXML private CheckBox cbS;

    @FXML private TableView<PizzaOrder> tableOrders;
    @FXML private TableColumn<PizzaOrder, String> colName;
    @FXML private TableColumn<PizzaOrder, String> colMobile;
    @FXML private TableColumn<PizzaOrder, String> colSize;
    @FXML private TableColumn<PizzaOrder, Integer> colToppings;
    @FXML private TableColumn<PizzaOrder, Double> colTotal;

    private final PizzaOrderDAO dao = new PizzaOrderDAO();
    private final ObservableList<PizzaOrder> orderList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colName.setCellValueFactory(data -> data.getValue().customerNameProperty());
        colMobile.setCellValueFactory(data -> data.getValue().mobileNumberProperty());
        colSize.setCellValueFactory(data -> data.getValue().pizzaSizeProperty());
        colToppings.setCellValueFactory(data -> data.getValue().toppingsProperty().asObject());
        colTotal.setCellValueFactory(data -> data.getValue().totalBillProperty().asObject());

        tableOrders.setItems(orderList);
        loadOrders();
    }

    private String getSelectedSize() {
        if (cbXL.isSelected()) return "XL";
        if (cbL.isSelected()) return "L";
        if (cbM.isSelected()) return "M";
        if (cbS.isSelected()) return "S";
        return "";
    }

    private void clearSizeSelection() {
        cbXL.setSelected(false);
        cbL.setSelected(false);
        cbM.setSelected(false);
        cbS.setSelected(false);
    }

    @FXML
    private void handleAdd() {
        String name = txtCustomerName.getText();
        String mobile = txtMobileNumber.getText();
        String size = getSelectedSize();
        int toppings = Integer.parseInt(txtToppings.getText());

        double total = BillCalculator.calculateBill(size, toppings);

        PizzaOrder order = new PizzaOrder(0, name, mobile, size, toppings, total);
        dao.insert(order);
        loadOrders();
        clearForm();
    }

    @FXML
    private void handleView() {
        loadOrders();
    }

    @FXML
    private void handleUpdate() {
        PizzaOrder selected = tableOrders.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        selected.setCustomerName(txtCustomerName.getText());
        selected.setMobileNumber(txtMobileNumber.getText());
        selected.setPizzaSize(getSelectedSize());
        int toppings = Integer.parseInt(txtToppings.getText());
        selected.setToppings(toppings);
        selected.setTotalBill(BillCalculator.calculateBill(selected.getPizzaSize(), toppings));

        dao.update(selected);
        loadOrders();
        clearForm();
    }

    @FXML
    private void handleDelete() {
        PizzaOrder selected = tableOrders.getSelectionModel().getSelectedItem();
        if (selected != null) {
            dao.delete(selected.getId());
            loadOrders();
        }
    }

    @FXML
    private void handleClear() {
        clearForm();
    }

    private void clearForm() {
        txtCustomerName.clear();
        txtMobileNumber.clear();
        txtToppings.clear();
        clearSizeSelection();
    }

    private void loadOrders() {
        orderList.setAll(dao.getAll());
    }
}
