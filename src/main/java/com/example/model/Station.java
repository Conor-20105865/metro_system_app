package com.example.model;
import java.util.*;
import static com.example.model.Edge.*;

public class Station {

    //Declare Variables
    public final String name;
    public final double latitude;
    public final double longitude;
    public final Map<Station, Edge>connections = new HashMap<>();

    //Create Constructor Class
    public Station(String name, double latitude, double longitude) {
           this.name = name;
           this.latitude = latitude;
           this.longitude = longitude;
    }

    //
    public void connect(Station other, double distance, String line) {
        connections.put(other, new Edge(this, other, distance, line));
    }

}
