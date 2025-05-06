package com.example.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    public Graph graph;

    @Test
    void getOrCreateStation() {
        Station station1 = graph.getOrCreateStation("Central", 40.0,-73.0);
        Station station2 = graph.getOrCreateStation("Central", 40.0,-73.0);

        assertNotNull(station1);
        assertSame(station1, station2);

    }

    @Test
    void connectStations() {
    }

    @Test
    void get() {

    }
}