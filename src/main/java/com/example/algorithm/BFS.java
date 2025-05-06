package com.example.algorithm;

import com.example.model.Graph;
import com.example.model.Station;

import java.util.*;

public class BFS {

    /**
     * Finds the shortest path between two stations using Breadth-First Search (BFS).
     * This method assumes all edges have equal weight (i.e., unweighted shortest path).
     *
     * @param graph
     * @param start The starting station.
     * @param end   The destination station.
     * @return A list of stations representing the shortest path from start to end.
     */
    public static List<Station> findShortestPath(Graph graph, Station start, Station end) {
        Map<Station, Station> parentMap = new HashMap<>(); // Maps each station to its predecessor
        Queue<Station> queue = new LinkedList<>();         // Queue for BFS traversal
        Set<Station> visited = new HashSet<>();            // Set of visited stations to avoid revisiting

        // Start BFS from the starting station
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Station current = queue.poll(); // Get the next station in the queue

            // Stop searching if the destination is reached
            if (current.equals(end)) break;

            // Explore all neighboring stations
            for (Station neighbor : current.connections.keySet()) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);             // Mark neighbor as visited
                    parentMap.put(neighbor, current);  // Record the path taken to reach this neighbor
                    queue.add(neighbor);               // Add neighbor to the queue for further exploration
                }
            }
        }

        // Reconstruct the shortest path by backtracking from end to start
        List<Station> path = new ArrayList<>();
        for (Station at = end; at != null; at = parentMap.get(at)) {
            path.add(at);
        }

        Collections.reverse(path); // Reverse the path to go from start to end
        return path;
    }
}
