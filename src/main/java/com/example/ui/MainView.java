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
import java.util.stream.Collectors;

public class MainView extends VBox {
    // Dropdown boxes for selecting start and end stations
    private final ComboBox<String> startComboBox = new ComboBox<>();
    private final ComboBox<String> endComboBox = new ComboBox<>();
    private final TextField penaltyField = new TextField("0");

    // Output area for displaying route results
    private final TextArea resultArea = new TextArea();

    // The graph representing the Vienna U-Bahn network, loaded from a CSV file
    private final Graph graph = CsvLoader.loadGraphFromCsv("src/data/vienna_subway.csv");

    // Constructor: sets up the user interface
    public MainView() {
        setSpacing(10); // Vertical spacing between elements
        setPadding(new Insets(15)); // Padding around the VBox

        // Populate station lists in ComboBoxes
        populateStationList();

        // Title label
        Label title = new Label("Vienna U-Bahn Route Finder");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        // Adding the image at the top
        Image image = new Image("file:src/main/resources/com/example/metro_system_app/map.png");
        ImageView imageView = new ImageView(image);

        // Set dimensions for the image
        imageView.setFitHeight(300); // Adjusted height for better visibility
        imageView.setFitWidth(300);
        imageView.setPreserveRatio(true);

        // Wrapping the image view in an HBox to offset it to the right
        HBox imageBox = new HBox(imageView);
        imageBox.setPadding(new Insets(0, 0, 0, 50)); // Add padding to the left to shift the image right
        imageBox.setAlignment(Pos.BASELINE_RIGHT); // Ensures alignment to the left center

        // GridPane for input fields
        GridPane inputGrid = new GridPane();
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);

        // Adding labels and dropdown lists to the grid
        inputGrid.add(new Label("Start Station:"), 0, 0);
        inputGrid.add(startComboBox, 1, 0);
        inputGrid.add(new Label("End Station:"), 0, 1);
        inputGrid.add(endComboBox, 1, 1);
        inputGrid.add(new Label("Line Change Penalty:"), 0, 2);
        inputGrid.add(penaltyField, 1, 2);

        // Buttons to trigger each algorithm
        Button bfsButton = new Button("Find Fewest Stops (BFS)");
        Button dijkstraButton = new Button("Shortest Route (Dijkstra)");
        Button dfsButton = new Button("All Routes (DFS)");

        // Grouping buttons in a horizontal layout
        HBox buttonRow = new HBox(10, bfsButton, dijkstraButton, dfsButton);

        // Setting up result display area
        resultArea.setEditable(false);
        resultArea.setPrefHeight(300);

        // Adding all UI components to the main VBox
        getChildren().addAll(imageBox, title, inputGrid, buttonRow, new Label("Results:"), resultArea);

        // Set button actions
        bfsButton.setOnAction(e -> findBFS());
        dijkstraButton.setOnAction(e -> findDijkstra());
        dfsButton.setOnAction(e -> findDFS());
    }

    // Populates the station list in the ComboBoxes
    private void populateStationList() {
        // Get station names from the graph and sort them in alphabetical order
        List<String> stationNames = graph.stations.values()
                .stream()
                .map(Station::getName)
                .sorted()
                .collect(Collectors.toList());

        // Populate the ComboBoxes
        startComboBox.setItems(FXCollections.observableArrayList(stationNames));
        endComboBox.setItems(FXCollections.observableArrayList(stationNames));
    }

    // Finds the shortest path using BFS (fewest number of stops)
    private void findBFS() {
        String startName = startComboBox.getValue();
        String endName = endComboBox.getValue();

        if (startName == null || endName == null) {
            resultArea.setText("Please select both start and end stations.");
            return;
        }

        Station start = graph.get(startName);
        Station end = graph.get(endName);
        if (start == null || end == null) {
            resultArea.setText("Invalid stations specified.");
            return;
        }
        List<Station> path = BFS.findShortestPath(graph, start, end);
        displayPath(path);
    }

    // Finds the shortest route by distance using Dijkstra's algorithm
    private void findDijkstra() {
        String startName = startComboBox.getValue();
        String endName = endComboBox.getValue();

        if (startName == null || endName == null) {
            resultArea.setText("Please select both start and end stations.");
            return;
        }

        Station start = graph.get(startName);
        Station end = graph.get(endName);
        double penalty;

        try {
            penalty = Double.parseDouble(penaltyField.getText());
        } catch (NumberFormatException e) {
            resultArea.setText("Enter a valid penalty value.");
            return;
        }

        if (start == null || end == null) {
            resultArea.setText("Invalid stations specified.");
            return;
        }

        List<Station> path = Dijkstra.shortestPath(graph, start, end, penalty);
        displayPath(path);
    }

    // Finds all possible routes using DFS
    private void findDFS() {
        String startName = startComboBox.getValue();
        String endName = endComboBox.getValue();

        if (startName == null || endName == null) {
            resultArea.setText("Please select both start and end stations.");
            return;
        }

        Station start = graph.get(startName);
        Station end = graph.get(endName);
        if (start == null || end == null) {
            resultArea.setText("Invalid stations specified.");
            return;
        }

        List<List<Station>> paths = DFS.findAllPaths(graph, start, end);
        StringBuilder sb = new StringBuilder();
        for (List<Station> path : paths) {
            sb.append(pathToString(path)).append("\n\n");
        }
        resultArea.setText(sb.toString());
    }

    // Displays a single path in the result area
    private void displayPath(List<Station> path) {
        if (path == null || path.isEmpty()) {
            resultArea.setText("No route found.");
            return;
        }
        resultArea.setText(pathToString(path));
    }

    // Converts a list of stations into a readable string with arrows between them
    private String pathToString(List<Station> path) {
        StringBuilder sb = new StringBuilder();
        for (Station s : path) {
            sb.append(s.getName()).append(" → ");
        }
        if (sb.length() > 0) sb.setLength(sb.length() - 3); // Remove the last arrow
        return sb.toString();
    }
}