module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.pizza to javafx.graphics, javafx.fxml;
    opens com.example.pizza.model to javafx.base;
    opens com.example.pizza.controller to javafx.fxml; // if controller package used

    exports com.example.pizza;
}
