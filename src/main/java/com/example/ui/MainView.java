package com.example.ui;

import com.example.util.CsvLoader;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import com.example.model.*;
import com.example.algorithm.*;
import com.example.model.Graph;

import java.util.List;

public class MainView extends VBox {
    // Input fields for start station, end station, and line change penalty
    private final TextField startField = new TextField();
    private final TextField endField = new TextField();
    private final TextField penaltyField = new TextField("0");

    // Output area for displaying route results
    private final TextArea resultArea = new TextArea();

    // The graph representing the Vienna U-Bahn network, loaded from a CSV file
    private final Graph graph = CsvLoader.loadGraphFromCsv("src/data/vienna_subway.csv");

    // Constructor: sets up the user interface
    public MainView() {
        setSpacing(10); // Vertical spacing between elements
        setPadding(new Insets(15)); // Padding around the VBox

        // Title label
        Label title = new Label("Vienna U-Bahn Route Finder");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        // GridPane for input fields
        GridPane inputGrid = new GridPane();
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);

        // Adding labels and fields to the grid
        inputGrid.add(new Label("Start Station:"), 0, 0);
        inputGrid.add(startField, 1, 0);
        inputGrid.add(new Label("End Station:"), 0, 1);
        inputGrid.add(endField, 1, 1);
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
        getChildren().addAll(title, inputGrid, buttonRow, new Label("Results:"), resultArea);

        // Set button actions
        bfsButton.setOnAction(e -> findBFS());
        dijkstraButton.setOnAction(e -> findDijkstra());
        dfsButton.setOnAction(e -> findDFS());
    }

    // Finds the shortest path using BFS (fewest number of stops)
    private void findBFS() {
        Station start = graph.get(startField.getText());
        Station end = graph.get(endField.getText());
        if (start == null || end == null) {
            resultArea.setText("Invalid stations specified.");
            return;
        }
        List<Station> path = BFS.findShortestPath(graph, start, end);
        displayPath(path);
    }

    // Finds the shortest route by distance using Dijkstra's algorithm
    private void findDijkstra() {
        Station start = graph.get(startField.getText());
        Station end = graph.get(endField.getText());
        double penalty;
        try {
            penalty = Double.parseDouble(penaltyField.getText());
        } catch (NumberFormatException e) {
            penalty = 0; // Default penalty if input is invalid
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
        Station start = graph.get(startField.getText());
        Station end = graph.get(endField.getText());
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
            sb.append(s.name).append(" → ");
        }
        if (sb.length() > 0) sb.setLength(sb.length() - 3); // Remove the last arrow
        return sb.toString();
    }
}
