package com.example.algorithm;
import com.example.model.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BFSTest {

    @Test
    void findShortestPath() {

            Graph graph = new Graph();

            Station a = graph.getOrCreateStation("A", 0.0, 0.0);
            Station b = graph.getOrCreateStation("B", 1.0, 1.0);
            Station c = graph.getOrCreateStation("C", 2.0, 2.0);
            Station d = graph.getOrCreateStation("D", 3.0, 3.0);

            // Unweighted connections (weight ignored by BFS)
            a.connect(b, 1.0, "Line 1");
            b.connect(a, 1.0, "Line 1");

            b.connect(c, 1.0, "Line 1");
            c.connect(b, 1.0, "Line 1");

            a.connect(d, 1.0, "Line 2");
            d.connect(a, 1.0, "Line 2");

            // Act: Find shortest unweighted path from A to C
            List<Station> path = BFS.findShortestPath(graph,a, c);

            // Assert
            assertNotNull(path);
            assertEquals(3, path.size());
            assertEquals(a, path.get(0));
            assertEquals(b, path.get(1));
            assertEquals(c, path.get(2));
        }
    }
