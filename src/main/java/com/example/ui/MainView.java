package com.example.ui;

import com.example.util.CsvLoader;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import com.example.model.*;
import com.example.algorithm.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MainView extends VBox {

    // === UI Components ===
    private final ComboBox<String> startComboBox = new ComboBox<>();
    private final ComboBox<String> endComboBox = new ComboBox<>();
    private final ComboBox<String> avoidComboBox = new ComboBox<>();
    private final TextField penaltyField = new TextField("0");
    private final TextArea resultArea = new TextArea();

    // Load the graph (subway map) from the CSV file
    private final Graph graph = CsvLoader.loadGraphFromCsv("src/data/vienna_subway.csv");

    // === Constructor ===
    public MainView() {
        // Set layout spacing and padding
        setSpacing(20);
        setPadding(new Insets(20));
        setStyle("-fx-background-color: #f0f8ff;"); // Light blue background

        populateStationList(); // Fill the combo boxes with station names

        // Title label
        Label title = new Label("Vienna U-Bahn Route Finder");
        title.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        title.setAlignment(Pos.CENTER);

        // Subway map image
        ImageView imageView = new ImageView(new Image("file:src/main/resources/com/example/metro_system_app/map.png"));
        imageView.setFitWidth(800);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);

        StackPane imagePane = new StackPane(imageView);
        imagePane.setStyle("-fx-border-color: #ccc; -fx-border-width: 2px; -fx-background-color: white;");
        imagePane.setPadding(new Insets(10));

        // Input section (start, end, avoid, penalty)
        GridPane inputGrid = new GridPane();
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);
        inputGrid.setPadding(new Insets(10));
        inputGrid.setStyle("-fx-background-color: white; -fx-border-color: #ccc; -fx-border-width: 1px; -fx-background-radius: 10px;");

        inputGrid.add(new Label("Start Station:"), 0, 0);
        inputGrid.add(startComboBox, 1, 0);
        inputGrid.add(new Label("End Station:"), 0, 1);
        inputGrid.add(endComboBox, 1, 1);
        inputGrid.add(new Label("Line Change Penalty:"), 0, 2);
        inputGrid.add(penaltyField, 1, 2);
        inputGrid.add(new Label("Station to Avoid:"), 0, 3);
        inputGrid.add(avoidComboBox, 1, 3);

        // Route finding buttons
        Button bfsButton = new Button("Find Fewest Stops (BFS)");
        Button dijkstraButton = new Button("Shortest Route (Dijkstra)");
        Button dfsButton = new Button("All Routes (DFS)");

        for (Button b : List.of(bfsButton, dijkstraButton, dfsButton)) {
            b.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-weight: bold;");
        }

        HBox buttonRow = new HBox(15, bfsButton, dijkstraButton, dfsButton);
        buttonRow.setAlignment(Pos.CENTER);
        buttonRow.setPadding(new Insets(10));

        // Text area to show results
        resultArea.setEditable(false);
        resultArea.setPrefHeight(300);
        resultArea.setStyle("-fx-control-inner-background: #ffffff; -fx-border-color: #ccc; -fx-border-width: 1px;");

        VBox resultBox = new VBox(5, new Label("Results:"), resultArea);
        resultBox.setPadding(new Insets(10));
        resultBox.setStyle("-fx-background-color: white; -fx-border-color: #ccc; -fx-border-width: 1px; -fx-background-radius: 10px;");

        // Add all UI elements to the main layout
        getChildren().addAll(title, imagePane, inputGrid, buttonRow, resultBox);

        // Set button actions
        bfsButton.setOnAction(e -> findBFS());
        dijkstraButton.setOnAction(e -> findDijkstra());
        dfsButton.setOnAction(e -> findDFS());
    }

    // === Populates combo boxes with station names ===
    private void populateStationList() {
        List<String> stationNames = graph.stations.values()
                .stream()
                .map(Station::getName)
                .sorted()
                .collect(Collectors.toList());

        startComboBox.setItems(FXCollections.observableArrayList(stationNames));
        endComboBox.setItems(FXCollections.observableArrayList(stationNames));
        avoidComboBox.setItems(FXCollections.observableArrayList(stationNames));
    }

    // === Finds a path using BFS (fewest stops) ===
    private void findBFS() {
        String startName = startComboBox.getValue();
        String endName = endComboBox.getValue();

        if (startName == null || endName == null) {
            resultArea.setText("Please select both start and end stations.");
            return;
        }

        Station start = graph.get(startName);
        Station end = graph.get(endName);
        Station avoid = graph.get(avoidComboBox.getValue());
        Set<Station> avoidSet = (avoid != null) ? Set.of(avoid) : Set.of();

        List<Station> path = BFS.findShortestPath(graph, start, end, avoidSet);
        displayPath(path);
    }

    // === Finds the shortest route using Dijkstra's algorithm ===
    private void findDijkstra() {
        String startName = startComboBox.getValue();
        String endName = endComboBox.getValue();

        if (startName == null || endName == null) {
            resultArea.setText("Please select both start and end stations.");
            return;
        }

        Station start = graph.get(startName);
        Station end = graph.get(endName);
        Station avoid = graph.get(avoidComboBox.getValue());
        Set<Station> avoidSet = (avoid != null) ? Set.of(avoid) : Set.of();

        double penalty;
        try {
            penalty = Double.parseDouble(penaltyField.getText());
        } catch (NumberFormatException e) {
            resultArea.setText("Enter a valid penalty value.");
            return;
        }

        List<Station> path = Dijkstra.shortestPath(graph, start, end, penalty, avoidSet);
        displayPath(path);
    }

    // === Finds all possible paths using DFS ===
    private void findDFS() {
        String startName = startComboBox.getValue();
        String endName = endComboBox.getValue();

        if (startName == null || endName == null) {
            resultArea.setText("Please select both start and end stations.");
            return;
        }

        Station start = graph.get(startName);
        Station end = graph.get(endName);
        Station avoid = graph.get(avoidComboBox.getValue());
        Set<Station> avoidSet = (avoid != null) ? Set.of(avoid) : Set.of();

        List<List<Station>> paths = DFS.findAllPaths(graph, start, end, avoidSet);
        StringBuilder sb = new StringBuilder();
        for (List<Station> path : paths) {
            sb.append(pathToString(path)).append("\n\n");
        }
        resultArea.setText(sb.toString());
    }

    // === Displays a single path (used for BFS and Dijkstra) ===
    private void displayPath(List<Station> path) {
        if (path == null || path.isEmpty()) {
            resultArea.setText("No route found.");
            return;
        }
        resultArea.setText(pathToString(path));
    }

    // === Converts a path (list of stations) into a string like "A → B → C" ===
    private String pathToString(List<Station> path) {
        return path.stream().map(Station::getName).collect(Collectors.joining(" → "));
    }
}
