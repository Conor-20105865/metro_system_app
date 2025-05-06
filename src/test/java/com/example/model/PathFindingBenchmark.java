package com.example.model;

package com.example.benchmark;

import com.example.model.*;
import com.example.util.CsvLoader;
import com.example.algorithm.BFS;
import org.openjdk.jmh.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class PathFindingBenchmark {

    private Graph graph;
    private Station start;
    private Station end;

    // Load graph once per trial to avoid measuring file I/O
    @Setup(Level.Trial)
    public void setup() {
        graph = CsvLoader.loadGraphFromCsv("src/data/vienna_subway.csv");
        start = graph.get("Karlsplatz");
        end = graph.get("Stephansplatz");
    }

    @Benchmark
    public List<Station> benchmarkBFS() {
        return BFS.findShortestPath(graph, start, end);
    }
}
