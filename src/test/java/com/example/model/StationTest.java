package com.example.model;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StationTest {

    @Test
    void connect() {
        Station s1 = new Station("Alpha", 0.0, 0.0);
        Station s2 = new Station("Beta", 1.0, 1.0);

        s1.connect(s2, 3.5, "Red Line");

        Map<Station, Edge> connections = s1.getConnections(); // Adjust getter
        assertTrue(connections.containsKey(s2));

        Edge edge = connections.get(s2);
        assertEquals(3.5, edge.getDistance());
        assertEquals("Red Line", edge.getLine());
    }
}