package com.example.util;

import com.example.model.Graph;
import com.example.model.Station;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is used to load the U-Bahn stations and connections from a CSV file.
 * It creates a graph with all the stations and their links.
 */
public class CsvLoader {

    // Stores fake (dummy) coordinates for each station (mostly AI-generated and not verified lol)
    private static final Map<String, double[]> dummyCoordinates = new HashMap<>();

    // Static block that fills the dummyCoordinates map with station names and coordinates
    // These values are just approximations and don't reflect real GPS data
    static {
        // Station name => {latitude, longitude}
        // You can replace these later with proper coordinates if needed
        dummyCoordinates.put("Aderklaaer Straße", new double[]{48.2731, 16.4342});
        dummyCoordinates.put("Alaudagasse", new double[]{48.1425, 16.3730});
        // (many more stations here...)
        dummyCoordinates.put("Zippererstraße", new double[]{48.1800, 16.4130});
        // you can add more if you're missing any
    }

    /**
     * This method reads the CSV file and builds the graph.
     * It adds all the stations and the connections between them based on the data.
     *
     * @param path the location of the CSV file
     * @return the graph representing the U-Bahn network
     */
    public static Graph loadGraphFromCsv(String path) {
        Graph graph = new Graph();

        // Try to open and read the file
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;

            // Skip the first line of the CSV (header row)
            reader.readLine();

            // Read each connection from the file
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                // Make sure we have enough columns
                if (parts.length < 4) continue;

                // Extract station names and line number
                String from = parts[0].trim();           // e.g., Station A
                String to = parts[1].trim();             // e.g., Station B
                String lineName = "U" + parts[2].trim(); // e.g., "1" becomes "U1"

                // Get coordinates for both stations, default to (0,0) if not found
                double[] fromCoords = dummyCoordinates.getOrDefault(from, new double[]{0, 0});
                double[] toCoords = dummyCoordinates.getOrDefault(to, new double[]{0, 0});

                // Get or create Station objects for both ends
                Station fromStation = graph.getOrCreateStation(from, fromCoords[0], fromCoords[1]);
                Station toStation = graph.getOrCreateStation(to, toCoords[0], toCoords[1]);

                // Calculate distance between them using simple math
                double distance = euclidean(fromCoords[0], fromCoords[1], toCoords[0], toCoords[1]);

                // Add an edge (connection) to the graph with distance and line info
                graph.connectStations(from, to, distance, lineName);
            }
        } catch (IOException e) {
            // Print any errors that happen while trying to read the file
            e.printStackTrace();
        }

        // Finally return the graph we just built
        return graph;
    }

    /**
     * Calculates the straight-line distance between two points (just for estimation).
     * This uses the Euclidean formula (not great for real-world distances but good enough here).
     */
    private static double euclidean(double lat1, double lon1, double lat2, double lon2) {
        return Math.sqrt(Math.pow(lat1 - lat2, 2) + Math.pow(lon1 - lon2, 2));
    }
}
