package com.example.algorithm;

import com.example.model.*;
import java.util.*;

public class Dijkstra {

    /**
     * Calculates the shortest path between two stations in a graph using Dijkstra's algorithm,
     * with an additional penalty for switching train lines.
     *
     * @param graph   The graph containing all stations and connections.
     * @param start   The starting station.
     * @param end     The destination station.
     * @param penalty The additional cost incurred when switching train lines.
     * @return A list of stations representing the shortest path from start to end.
     */
    public static List<Station> shortestPath(Graph graph, Station start, Station end, double penalty) {
        // Maps each station to the currently known shortest distance from the start.
        Map<Station, Double> distance = new HashMap<>();
        // Maps each station to the previous station in the shortest path.
        Map<Station, Station> prev = new HashMap<>();
        // Maps each station to the train line used to reach it (for line-switch penalty calculation).
        Map<Station, String> lineUsed = new HashMap<>();
        // Priority queue that selects the station with the smallest known distance.
        PriorityQueue<Station> pq = new PriorityQueue<>(Comparator.comparingDouble(distance::get));

        // Initialize distances to infinity, except the start station which is 0.
        for (Station s : graph.stations.values()) {
            distance.put(s, Double.POSITIVE_INFINITY);
        }
        distance.put(start, 0.0);
        pq.add(start);

        // Main loop: visit stations in order of increasing distance.
        while (!pq.isEmpty()) {
            Station current = pq.poll();

            // Stop once the destination is reached.
            if (current.equals(end)) break;

            // Visit all neighbors of the current station.
            for (Map.Entry<Station, Edge> entry : current.connections.entrySet()) {
                Station neighbor = entry.getKey();
                Edge edge = entry.getValue();

                // Compute tentative distance to the neighbor.
                double newDist = distance.get(current) + edge.distance;

                // Apply line-switch penalty if the train line has changed.
                if (!Objects.equals(lineUsed.get(current), edge.line)) {
                    newDist += penalty;
                }

                // If this path to the neighbor is better, update its data and add to the queue.
                if (newDist < distance.get(neighbor)) {
                    distance.put(neighbor, newDist);
                    prev.put(neighbor, current);
                    lineUsed.put(neighbor, edge.line);
                    pq.add(neighbor);
                }
            }
        }

        // Reconstruct the shortest path by backtracking from the destination.
        List<Station> path = new ArrayList<>();
        for (Station at = end; at != null; at = prev.get(at)) {
            path.add(at);
        }
        Collections.reverse(path); // Reverse to get path from start to end.
        return path;
    }
}
