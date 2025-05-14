package com.example.metro_system_app;
//~70%
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.example.ui.MainView;

public class ViennaUbahnApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        MainView mainView = new MainView();
        Scene scene = new Scene(mainView, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Vienna U-Bahn Route Finder");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}