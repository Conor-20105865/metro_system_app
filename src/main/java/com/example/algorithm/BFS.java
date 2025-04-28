package com.example.algorithm;

import com.example.model.Graph;
import com.example.model.Station;
import com.example.model.*;
import java.util.*;

public class BFS {
    public static List<Station> findShortestPath(Graph graph, Station start, Station end) {
        Map<Station, Station> parentMap = new HashMap<>();
        Queue<Station> queue = new LinkedList<>();
        Set<Station> visited = new HashSet<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Station current = queue.poll();
            if (current.equals(end)) break;

            for (Station neighbor : current.connections.keySet()) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parentMap.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        List<Station> path = new ArrayList<>();
        for (Station at = end; at != null; at = parentMap.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }
}
