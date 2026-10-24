# Vienna U-Bahn Route Finder

A JavaFX desktop application demonstrating pathfinding algorithms on a graph representing Vienna's U-Bahn network. Users can select start and destination stations, optionally avoid a station, and compare route-finding methods.

## Features

- Breadth-First Search (BFS) to find a path with the fewest connections
- Dijkstra's algorithm for weighted route finding, with a configurable line-change penalty
- Depth-First Search (DFS) to enumerate possible routes
- Station selection through dropdown menus
- Optional station avoidance
- Text-based route output
- CSV-based network loading and a reference map image
- JUnit tests for models and pathfinding algorithms
- JMH benchmark source

## Technologies

- Java 17
- JavaFX 21.0.2
- Maven with the Maven Wrapper
- JUnit 5
- JMH
- CSV data

## Requirements

- JDK 17 or newer
- A graphical desktop environment
- Internet access for initial Maven dependency resolution

## Getting started

Clone the repository and enter its root directory:

```bash
git clone https://github.com/Conor-20105865/metro_system_app.git
cd metro_system_app
```

Run the application on Linux or macOS:

```bash
./mvnw javafx:run
```

On Windows:

```powershell
.\mvnw.cmd javafx:run
```

The Maven JavaFX plugin is configured to launch `com.example.metro_system_app.ViennaUbahnApp`.

**Note:** These commands are based on the project configuration. A successful build and launch have not been independently verified.

## Using the application

1. Select a start station and destination.
2. Optionally select a station to avoid.
3. Enter a line-change penalty when using Dijkstra's algorithm.
4. Select **Find Fewest Stops (BFS)**, **Shortest Route (Dijkstra)**, or **All Routes (DFS)**.
5. Read the results in the text area.

## Algorithms

**BFS:** Traverses the network to find a route with the fewest station connections, without using edge weights.

**Dijkstra:** Uses weighted connections and optionally accounts for line-change penalties.

**DFS:** Searches for possible paths. Enumerating paths may be expensive for large networks.

The station coordinates in `CsvLoader.java` are approximations. The edge weights use Euclidean differences between coordinates, not verified journey times or real-world travel distances.

## Project structure

```text
src/
├── data/
│   └── vienna_subway.csv
├── main/
│   ├── java/
│   │   ├── com/example/algorithm/       # BFS, DFS, Dijkstra
│   │   ├── com/example/model/           # Graph, Station, Edge
│   │   ├── com/example/ui/              # MainView
│   │   ├── com/example/util/            # CsvLoader
│   │   ├── com/example/metro_system_app/ # ViennaUbahnApp
│   │   └── module-info.java
│   └── resources/com/example/metro_system_app/map.png
└── test/java/com/example/
    ├── algorithm/
    ├── benchmark/
    └── model/
```

## Testing

Run the Maven test lifecycle:

```bash
./mvnw test
```

On Windows use `./mvnw.cmd test` or `.\mvnw.cmd test` in PowerShell. Tests are present in the repository, but their current pass status has not been independently verified.

## Known limitations

- Calculated paths appear as text and are not highlighted on the map.
- Station coordinates are approximate and should not be treated as real geographic measurements.
- DFS may produce a large number of possible routes.
- The CSV and map are loaded using relative filesystem paths, so run the application from the repository root.
- Build and runtime behavior has not been independently tested across operating systems.

## Possible improvements

- Highlight selected routes on the map.
- Use verified coordinates and realistic journey-time weights.
- Load resources through the Java classpath instead of project-relative paths.
- Add route comparison and performance visualization.
- Expand test coverage and record benchmark results.

## Purpose

This is an educational project exploring graph representations, pathfinding algorithms, and desktop application development with JavaFX.
