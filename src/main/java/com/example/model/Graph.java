package com.example.model;
import com.example.model.Edge.*;
import java.util.*;

public class Graph {

    //Create new Hash Map to store all stations
    public Map<String, Station> stations = new HashMap<>();

    public Station getOrCreateStation(String name, double lat, double lon) {
           Station station = stations.get(name); //Get current station
           //If station is null create a new station
           if (station == null) {
               station = new Station(name, lat, lon); //Create the new station
               stations.put(name, station);
           }
           return station; // Return back station
    }

    public void connectStations(String from, String to, double dist, String line) {
        Station s1 = stations.get(from);
        Station s2 = stations.get(to);
        if (s1 != null && s2 != null) {
            s1.connect(s2, dist, line);
            s2.connect(s1, dist, line);
        }
    }

    public Station get(String name) {
        return stations.get(name);
    }
}
