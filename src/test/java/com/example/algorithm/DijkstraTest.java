package com.example.algorithm;

import org.junit.jupiter.api.Test;
import com.example.model.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraTest {

    @Test
    void shortestPath() {
        // Arrange: set up a small graph
        Graph graph = new Graph();

        Station a = graph.getOrCreateStation("A", 0.0, 0.0);
        Station b = graph.getOrCreateStation("B", 1.0, 1.0);
        Station c = graph.getOrCreateStation("C", 2.0, 2.0);

        a.connect(b, 2.0, "Red");
        b.connect(a, 2.0, "Red"); // bidirectional

        b.connect(c, 1.0, "Red");
        c.connect(b, 1.0, "Red"); // bidirectional

        a.connect(c, 10.0, "Green");
        c.connect(a, 10.0, "Green"); // bidirectional

        // Act
        List<Station> path = Dijkstra.shortestPath(graph, a, c, Double.MAX_VALUE);

        // Assert
        assertNotNull(path);
        assertEquals(2, path.size());
        assertEquals(a, path.get(0));
        assertEquals(c, path.get(1));
    }
}