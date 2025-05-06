package com.example.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {
//bin
    @Test
    void getOrCreateStation() {
        Graph graph = new Graph();

        Station station1 = graph.getOrCreateStation("Central", 40.0,-73.0);
        Station station2 = graph.getOrCreateStation("Central", 40.0,-73.0);

        assertNotNull(station1);
        assertSame(station1, station2);

    }

    @Test
    void get() {
        Graph graph = new Graph();

        graph.getOrCreateStation("Plunkett", 10.0, 20.0);
        Station result = graph.get("Plunkett");

        assertNotNull(result);
        assertEquals("Plunkett", result.getName());
    }

}