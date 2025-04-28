package com.example.ui;


import com.example.util.CsvLoader;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import com.example.model.*;
import com.example.algorithm.*;

import java.util.List;

public class MainView extends VBox {
    private final TextField startField = new TextField();
    private final TextField endField = new TextField();
    private final TextField penaltyField = new TextField("0");
    private final TextArea resultArea = new TextArea();
    private final Graph graph = CsvLoader.loadGraphFromCsv("src/data/vienna_subway.csv");
    // You should initialize this with real data

    public MainView() {
        setSpacing(10);
        setPadding(new Insets(15));

        Label title = new Label("Vienna U-Bahn Route Finder");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        GridPane inputGrid = new GridPane();
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);


        inputGrid.add(new Label("Start Station:"), 0, 0);
        inputGrid.add(startField, 1, 0);
        inputGrid.add(new Label("End Station:"), 0, 1);
        inputGrid.add(endField, 1, 1);
        inputGrid.add(new Label("Line Change Penalty:"), 0, 2);
        inputGrid.add(penaltyField, 1, 2);

        Button bfsButton = new Button("Find Fewest Stops (BFS)");
        Button dijkstraButton = new Button("Shortest Route (Dijkstra)");
        Button dfsButton = new Button("All Routes (DFS)");

        HBox buttonRow = new HBox(10, bfsButton, dijkstraButton, dfsButton);

        resultArea.setEditable(false);
        resultArea.setPrefHeight(300);

        getChildren().addAll(title, inputGrid, buttonRow, new Label("Results:"), resultArea);

        bfsButton.setOnAction(e -> findBFS());
        dijkstraButton.setOnAction(e -> findDijkstra());
        dfsButton.setOnAction(e -> findDFS());
    }

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

    private void findDijkstra() {
        Station start = graph.get(startField.getText());
        Station end = graph.get(endField.getText());
        double penalty;
        try {
            penalty = Double.parseDouble(penaltyField.getText());
        } catch (NumberFormatException e) {
            penalty = 0;
        }
        if (start == null || end == null) {
            resultArea.setText("Invalid stations specified.");
            return;
        }
        List<Station> path = Dijkstra.shortestPath(graph, start, end, penalty);
        displayPath(path);
    }

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

    private void displayPath(List<Station> path) {
        if (path == null || path.isEmpty()) {
            resultArea.setText("No route found.");
            return;
        }
        resultArea.setText(pathToString(path));
    }

    private String pathToString(List<Station> path) {
        StringBuilder sb = new StringBuilder();
        for (Station s : path) {
            sb.append(s.name).append(" → ");
        }
        if (sb.length() > 0) sb.setLength(sb.length() - 3); // remove last arrow
        return sb.toString();
    }
}
