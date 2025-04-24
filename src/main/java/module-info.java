module com.example.metro_system_app {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.metro_system_app to javafx.fxml;
    exports com.example.metro_system_app;
}