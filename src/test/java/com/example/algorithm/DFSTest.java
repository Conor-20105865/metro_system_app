package com.example.algorithm;

import org.junit.jupiter.api.Test;
import com.example.model.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DFSTest {

    @Test
    void findAllPaths() {
        // Arrange: set up a small graph
        Graph graph = new Graph();

        Station a = graph.getOrCreateStation("A", 0.0, 0.0);
        Station b = graph.getOrCreateStation("B", 1.0, 1.0);
        Station c = graph.getOrCreateStation("C", 2.0, 2.0);
        Station d = graph.getOrCreateStation("D", 3.0, 3.0);

        // Connect stations (example of bidirectional connections)
        a.connect(b, 1.0, "Red");
        b.connect(a, 1.0, "Red");

        b.connect(c, 1.0, "Red");
        c.connect(b, 1.0, "Red");

        a.connect(d, 1.0, "Blue");
        d.connect(a, 1.0, "Blue");

        c.connect(d, 1.0, "Blue");
        d.connect(c, 1.0, "Blue");

        // Act: Call DFS to find all paths from A to D
        List<List<Station>> paths = DFS.findAllPaths(graph, a, d);

        // Assert: Check that the paths are correct
        assertNotNull(paths);
        assertEquals(2, paths.size()); // Expected paths A → B → C → D and A → D
        assertEquals(a, paths.get(0).get(0));
        assertEquals(d, paths.get(0).get(3));
        assertEquals(a, paths.get(1).get(0));
        assertEquals(d, paths.get(1).get(1));
    }
}