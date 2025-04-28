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

    public MainView(){
}
