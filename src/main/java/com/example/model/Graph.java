package com.example.model;
import com.example.model.Edge.*;
import java.util.*;

public class Graph {

    //Create new Hash Map to store all stations
    public Map<String, Station> stations;

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
            Station fromStation = getOrCreateStation(from, 0.0, 0.0);
            Station toStation = getOrCreateStation(to, 0.0, 0.0);

            //fromStation and toStation are both used for bi-directional traversal
            fromStation.connect(toStation, dist, line);
            toStation.connect(fromStation, dist,line);
    }

}
