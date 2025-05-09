package com.example.model;

import java.util.*;
import static com.example.model.Edge.*;

public class Station {

    // === Variables ===

    public final String name;                    // Name of the station (e.g., "Karlsplatz")
    public final double latitude;                // Latitude coordinate
    public final double longitude;               // Longitude coordinate

    // Map of connected stations and the edges to them
    // Each connection goes from this station to another, with details in the Edge
    public final Map<Station, Edge> connections = new HashMap<>();

    // === Constructor ===

    // This creates a new Station object with a name and coordinates
    public Station(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // === Getter Methods ===

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public Map<Station, Edge> getConnections() {
        return connections;
    }

    // === Connect Method ===

    // This method connects the current station to another station using an edge.
    // It creates an edge from 'other' to 'this' (could also work the other way around depending on direction)
    public void connect(Station other, double distance, String line) {
        Edge edge = new Edge(other, this, distance, line);  // Create new edge object
        connections.put(other, edge);                       // Add it to the connections map
    }
}
