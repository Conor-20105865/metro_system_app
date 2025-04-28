package com.example.util;

import com.example.model.Graph;
import com.example.model.Station;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CsvLoader {
    // Placeholder lat/lon - you can enhance with real coordinates later
    private static final Map<String, double[]> dummyCoordinates = new HashMap<>();

    static {
        // If you know actual coordinates, replace here
        dummyCoordinates.put("Oberlaa", new double[]{48.146, 16.393});
        dummyCoordinates.put("Neulaa", new double[]{48.145, 16.400});
        dummyCoordinates.put("Alaudagasse", new double[]{48.143, 16.410});
        // ... add more as needed
    }

    public static Graph loadGraphFromCsv(String path) {
        Graph graph = new Graph();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            reader.readLine(); // skip header
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 4) continue;
                String from = parts[0].trim();
                String to = parts[1].trim();
                String lineName = "U" + parts[2].trim();

                double[] fromCoords = dummyCoordinates.getOrDefault(from, new double[]{0, 0});
                double[] toCoords = dummyCoordinates.getOrDefault(to, new double[]{0, 0});

                Station fromStation = graph.getOrCreateStation(from, fromCoords[0], fromCoords[1]);
                Station toStation = graph.getOrCreateStation(to, toCoords[0], toCoords[1]);

                double distance = euclidean(fromCoords[0], fromCoords[1], toCoords[0], toCoords[1]);
                graph.connectStations(from, to, distance, lineName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return graph;
    }

    private static double euclidean(double lat1, double lon1, double lat2, double lon2) {
        return Math.sqrt(Math.pow(lat1 - lat2, 2) + Math.pow(lon1 - lon2, 2));
    }
}
