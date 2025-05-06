package com.example.algorithm;

import com.example.model.*;
import java.util.*;

public class Dijkstra {

    public static List<Station> shortestPath(Graph graph, Station start, Station end, double penalty) {
        Map<Station, Double> distance = new HashMap<>();
        Map<Station, Station> prev = new HashMap<>();
        Map<Station, String> lineUsed = new HashMap<>();
        PriorityQueue<Station> pq = new PriorityQueue<>(Comparator.comparingDouble(distance::get));

        for (Station s : graph.stations.values()) {
            distance.put(s, Double.POSITIVE_INFINITY);
        }
        distance.put(start, 0.0);
        pq.add(start);

        while (!pq.isEmpty()) {
            Station current = pq.poll();
            if (current.equals(end)) break;

            for (Map.Entry<Station, Edge> entry : current.connections.entrySet()) {
                Station neighbor = entry.getKey();
                Edge edge = entry.getValue();
                double newDist = distance.get(current) + edge.distance;
                if (!Objects.equals(lineUsed.get(current), edge.line)) {
                    newDist += penalty;
                }

                if (newDist < distance.get(neighbor)) {
                    distance.put(neighbor, newDist);
                    prev.put(neighbor, current);
                    lineUsed.put(neighbor, edge.line);
                    pq.add(neighbor);
                }
            }
        }

        List<Station> path = new ArrayList<>();
        for (Station at = end; at != null; at = prev.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }
}

