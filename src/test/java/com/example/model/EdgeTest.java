package com.example.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EdgeTest {

    @Test
    void getFrom() {
        Station From = new Station("Start", 10.0, 10.0);
        Station To = new Station("Finish", 9.0, 9.0);

        Edge edge = new Edge(From, To, 6.0, "Green Line");
        assertEquals(From, edge.getFrom());
    }

    @Test
    void getTo() {
        Station From = new Station("Start", 10.0, 10.0);
        Station To = new Station("Finish", 9.0, 9.0);

        Edge edge = new Edge(From, To, 9.0, "Purple Line");
        assertEquals(To, edge.getTo());
    }

    @Test
    void getDistance() {
        Station From = new Station("Start", 10.0, 10.0);
        Station To = new Station("Finish", 9.0, 9.0);

        Edge edge = new Edge(From, To, 9.0, "Yellow Line");
        assertEquals(9.0, edge.getDistance());
    }

    @Test
    void getLine() {
        Station From = new Station("Start", 10.0, 10.0);
        Station To = new Station("Finish", 9.0, 9.0);

        Edge edge = new Edge(From, To, 9.0, "Red Line");
        assertEquals("Red Line", edge.getLine());
    }
}