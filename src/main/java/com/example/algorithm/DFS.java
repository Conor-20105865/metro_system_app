package com.example.algorithm;

import com.example.model.*;
import java.util.*;

public class DFS {

    /**
     * Finds all possible paths between the start and end stations using Depth-First Search.
     *
     * @param graph The graph containing stations and their connections.
     * @param start The starting station.
     * @param end   The destination station.
     * @return A list of all paths found, each represented as a list of stations.
     */
    public static List<List<Station>> findAllPaths(Graph graph, Station start, Station end) {
        List<List<Station>> result = new ArrayList<>(); // List to store all found paths
        dfs(start, end, new HashSet<>(), new ArrayList<>(), result); // Start recursive DFS
        return result;
    }

    /**
     * Recursive helper method to perform DFS.
     *
     * @param current The current station being visited.
     * @param end     The target station.
     * @param visited Set of visited stations to avoid cycles.
     * @param path    Current path being built.
     * @param result  List of all complete paths from start to end.
     */
    private static void dfs(Station current, Station end, Set<Station> visited, List<Station> path, List<List<Station>> result) {
        visited.add(current); // Mark current station as visited
        path.add(current);    // Add current station to the current path

        if (current.equals(end)) {
            // If the end station is reached, store a copy of the current path
            result.add(new ArrayList<>(path));
        } else {
            // Explore all unvisited neighboring stations
            for (Station neighbor : current.connections.keySet()) {
                if (!visited.contains(neighbor)) {
                    dfs(neighbor, end, visited, path, result); // Recursive DFS call
                }
            }
        }

        // Backtrack: remove the current station from path and visited set
        path.remove(path.size() - 1);
        visited.remove(current);
    }
}
