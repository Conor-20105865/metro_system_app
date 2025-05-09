package com.example.algorithm;

import com.example.model.*;
import java.util.*;

public class DFS {

    // This method finds *all possible paths* from 'start' to 'end' using Depth-First Search,
    // while avoiding any stations in the 'avoid' set.
    public static List<List<Station>> findAllPaths(Graph graph, Station start, Station end, Set<Station> avoid) {
        List<List<Station>> result = new ArrayList<>();  // Stores all the valid paths found
        // Start the DFS with an empty path and visited set
        dfs(start, end, new HashSet<>(), new ArrayList<>(), result, avoid);
        return result;  // Return the list of all found paths
    }

    // Recursive helper method for DFS traversal
    private static void dfs(Station current, Station end, Set<Station> visited, List<Station> path,
                            List<List<Station>> result, Set<Station> avoid) {
        // If this station is on the avoid list, skip it
        if (avoid.contains(current)) return;

        visited.add(current);  // Mark current station as visited
        path.add(current);     // Add current station to the path

        if (current.equals(end)) {
            // If we reached the destination, save a copy of the current path
            result.add(new ArrayList<>(path));
        } else {
            // Otherwise, continue exploring each unvisited neighbor
            for (Station neighbor : current.connections.keySet()) {
                if (!visited.contains(neighbor)) {
                    dfs(neighbor, end, visited, path, result, avoid);  // Recursive call
                }
            }
        }

        // Backtrack: remove current station from path and visited so we can try other paths
        path.remove(path.size() - 1);
        visited.remove(current);
    }
}
