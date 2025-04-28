package com.example.algorithm;

import com.example.model.*;
import java.util.*;

public class DFS {
    public static List<List<Station>> findAllPaths(Graph graph, Station start, Station end) {
        List<List<Station>> result = new ArrayList<>();
        dfs(start, end, new HashSet<>(), new ArrayList<>(), result);
        return result;
    }

    private static void dfs(Station current, Station end, Set<Station> visited, List<Station> path, List<List<Station>> result) {
        visited.add(current);
        path.add(current);

        if (current.equals(end)) {
            result.add(new ArrayList<>(path));
        } else {
            for (Station neighbor : current.connections.keySet()) {
                if (!visited.contains(neighbor)) {
                    dfs(neighbor, end, visited, path, result);
                }
            }
        }

        path.remove(path.size() - 1);
        visited.remove(current);
    }
}

