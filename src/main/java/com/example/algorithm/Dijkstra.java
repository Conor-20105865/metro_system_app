package com.example.algorithm;

import com.example.model.*;
import java.util.*;

public class Dijkstra {

    // This method finds the shortest path from 'start' to 'end' using Dijkstra's algorithm.
    // It adds a penalty when changing lines and avoids any stations in the 'avoid' set.
    public static List<Station> shortestPath(Graph graph, Station start, Station end, double penalty, Set<Station> avoid) {
        Map<Station, Double> distance = new HashMap<>();         // Keeps track of shortest known distance to each station
        Map<Station, Station> prev = new HashMap<>();            // Stores how we got to each station (for backtracking path)
        Map<Station, String> lineUsed = new HashMap<>();         // Tracks which line was used to reach each station
        PriorityQueue<Station> pq = new PriorityQueue<>(Comparator.comparingDouble(distance::get)); // Priority queue based on shortest distance

        // Initialize all distances to infinity
        for (Station s : graph.stations.values()) {
            distance.put(s, Double.POSITIVE_INFINITY);
        }

        distance.put(start, 0.0);  // Starting station has distance 0
        pq.add(start);            // Add start station to the queue

        while (!pq.isEmpty()) {
            Station current = pq.poll();  // Get station with smallest distance

            if (current.equals(end)) break;  // If we reached the destination, stop

            if (avoid.contains(current)) continue;  // Skip if this station is in the avoid list

            // Check all connected stations
            for (Map.Entry<Station, Edge> entry : current.connections.entrySet()) {
                Station neighbor = entry.getKey();  // The connected station
                Edge edge = entry.getValue();       // The connection (includes line and distance info)

                if (avoid.contains(neighbor)) continue;  // Skip if neighbor is in avoid list

                double newDist = distance.get(current) + edge.distance;  // Base distance

                // Add penalty if changing to a different line
                if (!Objects.equals(lineUsed.get(current), edge.line)) {
                    newDist += penalty;
                }

                // If we found a shorter path to this neighbor, update everything
                if (newDist < distance.get(neighbor)) {
                    distance.put(neighbor, newDist);        // Update distance
                    prev.put(neighbor, current);            // Remember how we got here
                    lineUsed.put(neighbor, edge.line);      // Track which line we used
                    pq.add(neighbor);                       // Add neighbor to the queue for processing
                }
            }
        }

        // Reconstruct path from end to start using the 'prev' map
        List<Station> path = new ArrayList<>();
        for (Station at = end; at != null; at = prev.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);  // Reverse it so it goes from start to end
        return path;                // Return the final path
    }
}
