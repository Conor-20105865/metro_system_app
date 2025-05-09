package com.example.algorithm;

import com.example.model.Graph;
import com.example.model.Station;

import java.util.*;

public class BFS {

    // This method finds the shortest path from 'start' to 'end' station using Breadth-First Search (BFS),
    // while avoiding any stations listed in the 'avoid' set.
    public static List<Station> findShortestPath(Graph graph, Station start, Station end, Set<Station> avoid) {
        Map<Station, Station> parentMap = new HashMap<>(); // Keeps track of each station's parent in the path
        Queue<Station> queue = new LinkedList<>();         // Queue for BFS traversal
        Set<Station> visited = new HashSet<>();            // Set to keep track of visited stations

        queue.add(start);  // Start from the start station
        visited.add(start);

        while (!queue.isEmpty()) {
            Station current = queue.poll(); // Get the next station to process

            // If we reached the destination, stop the search
            if (current.equals(end)) break;

            // Go through all the connected stations (neighbors)
            for (Station neighbor : current.connections.keySet()) {
                // Only visit neighbors that haven't been visited and aren't in the avoid list
                if (!visited.contains(neighbor) && !avoid.contains(neighbor)) {
                    visited.add(neighbor);              // Mark the neighbor as visited
                    parentMap.put(neighbor, current);   // Save the path (how we got here)
                    queue.add(neighbor);                // Add the neighbor to the queue for further processing
                }
            }
        }

        // Reconstruct the path from end to start using the parentMap
        List<Station> path = new ArrayList<>();
        for (Station at = end; at != null; at = parentMap.get(at)) {
            path.add(at);
        }
        Collections.reverse(path); // Reverse the path to go from start to end
        return path;               // Return the final path
    }
}
