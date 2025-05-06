package com.example.model;

public class Edge {

    //Declare Variables
    public static Station from;
    public static Station to;
    public static double distance;

    public static String line;

    //Edge Class Constructor
    public Edge(Station from, Station to, double distance, String line) {
           this.from = from;
           this.to = to;
           this.distance = distance;
           this.line = line;
    }


}

