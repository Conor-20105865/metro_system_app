package com.example.model;

public class Edge {

    //Declare Variables
    public Station from;
    public Station to;
    public double distance;

    public String line;

    //Edge Class Constructor
    public Edge(Station from, Station to, double distance, String line) {
           this.from = from;
           this.to = to;
           this.distance = distance;
           this.line = line;
    }

}

