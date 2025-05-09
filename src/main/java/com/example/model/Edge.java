package com.example.model;

public class Edge {

    // Variables to represent the connection between two stations
    public Station from;      // The station the edge starts from
    public Station to;        // The station the edge goes to
    public double distance;   // Distance between the two stations
    public String line;       // The line (e.g., U1, U2) this edge belongs to

    // Constructor for creating an edge between two stations
    public Edge(Station from, Station to, double distance, String line) {
        this.from = from;           // Set the 'from' station
        this.to = to;               // Set the 'to' station
        this.distance = distance;   // Set the distance
        this.line = line;           // Set the line name
    }

    // Getter methods to access edge information

    public Station getFrom() {
        return from;
    }

    public Station getTo() {
        return to;
    }

    public double getDistance() {
        return distance;
    }

    public String getLine() {
        return line;
    }
}
