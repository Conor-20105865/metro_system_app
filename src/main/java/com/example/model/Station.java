package com.example.model;

import java.util.*;

public class Station {

    //Declare Variables
    private String name;
    private double latitude;
    private double longitude;

    //Create Constructor Class
    public Station(String name, double latitude, double longitude) {
           this.name = name;
           this.latitude = latitude;
           this.longitude = longitude;
    }

    //
    public void connect(Station other, double distance, String line) {
           Edge edge = new Edge(from, to, distance, line);
           connections.put(edge);
    }


}
