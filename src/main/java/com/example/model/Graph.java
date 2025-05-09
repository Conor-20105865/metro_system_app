package com.example.model;

import com.example.model.Edge.*;
import java.util.*;

public class Graph {

    // Map to store all stations by their names (like a dictionary: name → Station object)
    public Map<String, Station> stations = new HashMap<>();

    // Gets a station by name if it exists, or creates it if it doesn't
    public Station getOrCreateStation(String name, double lat, double lon) {
        Station station = stations.get(name);  // Try to get the station from the map

        if (station == null) {
            // If it doesn't exist yet, create a new station and add it to the map
            station = new Station(name, lat, lon);
            stations.put(name, station);
        }

        return station;  // Return the existing or newly created station
    }

    // Connects two stations together in *both* directions (undirected graph)
    public void connectStations(String from, String to, double dist, String line) {
        Station s1 = stations.get(from);  // Get the 'from' station
        Station s2 = stations.get(to);    // Get the 'to' station

        if (s1 != null && s2 != null) {
            // If both stations exist, connect them in both directions
            s1.connect(s2, dist, line);
            s2.connect(s1, dist, line);
        }
    }

    // Returns a station object by its name
    public Station get(String name) {
        return stations.get(name);
    }
}
