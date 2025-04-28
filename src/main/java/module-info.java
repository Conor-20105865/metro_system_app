module com.example.metro_system_app {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.metro_system_app to javafx.fxml;
    exports com.example.metro_system_app;
    exports com.example.model;
    opens com.example.model to javafx.fxml;
    exports com.example.algorithm;
    opens com.example.algorithm to javafx.fxml;
}