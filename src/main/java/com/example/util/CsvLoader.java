package com.example.util;

import com.example.model.Graph;
import com.example.model.Station;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class responsible for loading U-Bahn stations and connections from a CSV file
 * and creating a graph representation of the network.
 */
public class CsvLoader {

    // Map storing dummy coordinates for stations (can be replaced with real data)
    private static final Map<String, double[]> dummyCoordinates = new HashMap<>();

    // Static block to populate dummyCoordinates with hardcoded values
    static {
        // Station name mapped to latitude and longitude
        // These are approximations and can be replaced with actual coordinates
        dummyCoordinates.put("Aderklaaer Straße", new double[]{48.2731, 16.4342});
        dummyCoordinates.put("Alaudagasse", new double[]{48.1425, 16.3730});
        dummyCoordinates.put("Alser Straße", new double[]{48.2186, 16.3452});
        dummyCoordinates.put("Alte Donau", new double[]{48.2390, 16.4170});
        dummyCoordinates.put("Alterlaa", new double[]{48.1500, 16.3200});
        dummyCoordinates.put("Altes Landgut", new double[]{48.1575, 16.3686});
        dummyCoordinates.put("Am Schöpfwerk", new double[]{48.1545, 16.3205});
        dummyCoordinates.put("Aspern Nord", new double[]{48.2360, 16.4830});
        dummyCoordinates.put("Aspernstraße", new double[]{48.2365, 16.4500});
        dummyCoordinates.put("Bahnhof Meidling", new double[]{48.1750, 16.3350});
        dummyCoordinates.put("Braunschweiggasse", new double[]{48.1920, 16.3000});
        dummyCoordinates.put("Burggasse-Stadthalle", new double[]{48.2030, 16.3400});
        dummyCoordinates.put("Donauinsel", new double[]{48.2360, 16.4100});
        dummyCoordinates.put("Donaumarina", new double[]{48.2100, 16.4200});
        dummyCoordinates.put("Donauspital", new double[]{48.2400, 16.4600});
        dummyCoordinates.put("Donaustadtbrücke", new double[]{48.2200, 16.4300});
        dummyCoordinates.put("Dresdner Straße", new double[]{48.2400, 16.3700});
        dummyCoordinates.put("Enkplatz", new double[]{48.1800, 16.4100});
        dummyCoordinates.put("Erdberg", new double[]{48.1913, 16.4144});
        dummyCoordinates.put("Erlaaer Straße", new double[]{48.1450, 16.3200});
        dummyCoordinates.put("Floridsdorf", new double[]{48.2570, 16.4000});
        dummyCoordinates.put("Friedensbrücke", new double[]{48.2300, 16.3700});
        dummyCoordinates.put("Gasometer", new double[]{48.1900, 16.4200});
        dummyCoordinates.put("Großfeldsiedlung", new double[]{48.2700, 16.4200});
        dummyCoordinates.put("Gumpendorfer Straße", new double[]{48.1900, 16.3400});
        dummyCoordinates.put("Handelskai", new double[]{48.2500, 16.3800});
        dummyCoordinates.put("Hardeggasse", new double[]{48.2400, 16.4500});
        dummyCoordinates.put("Hausfeldstraße", new double[]{48.2400, 16.4700});
        dummyCoordinates.put("Heiligenstadt", new double[]{48.2500, 16.3600});
        dummyCoordinates.put("Herrengasse", new double[]{48.2100, 16.3700});
        dummyCoordinates.put("Hietzing", new double[]{48.1875, 16.3050});
        dummyCoordinates.put("Hütteldorf", new double[]{48.2000, 16.2700});
        dummyCoordinates.put("Hütteldorfer Straße", new double[]{48.2000, 16.3200});
        dummyCoordinates.put("Jägerstraße", new double[]{48.2400, 16.3700});
        dummyCoordinates.put("Johnstraße", new double[]{48.2000, 16.3200});
        dummyCoordinates.put("Josefstädter Straße", new double[]{48.2100, 16.3400});
        dummyCoordinates.put("Kagran", new double[]{48.2433, 16.4332});
        dummyCoordinates.put("Kagraner Platz", new double[]{48.2500, 16.4400});
        dummyCoordinates.put("Kaisermühlen/VIC", new double[]{48.2329, 16.4165});
        dummyCoordinates.put("Kardinal-Nagl-Platz", new double[]{48.1950, 16.4000});
        dummyCoordinates.put("Karlsplatz", new double[]{48.2000, 16.3700});
        dummyCoordinates.put("Kendlerstraße", new double[]{48.2000, 16.3100});
        dummyCoordinates.put("Keplerplatz", new double[]{48.1800, 16.3800});
        dummyCoordinates.put("Kettenbrückengasse", new double[]{48.2000, 16.3600});
        dummyCoordinates.put("Krieau", new double[]{48.2200, 16.4100});
        dummyCoordinates.put("Längenfeldgasse", new double[]{48.1900, 16.3300});
        dummyCoordinates.put("Landstraße", new double[]{48.2061, 16.3852});
        dummyCoordinates.put("Leopoldau", new double[]{48.2700, 16.4300});
        dummyCoordinates.put("Margaretengürtel", new double[]{48.1946, 16.3504});
        dummyCoordinates.put("Meidling Hauptstraße", new double[]{48.1800, 16.3300});
        dummyCoordinates.put("Messe-Prater", new double[]{48.2200, 16.4100});
        dummyCoordinates.put("Michelbeuern - AKH", new double[]{48.2200, 16.3400});
        dummyCoordinates.put("Museumsquartier", new double[]{48.2000, 16.3600});
        dummyCoordinates.put("Nestroyplatz", new double[]{48.2150, 16.3900});
        dummyCoordinates.put("Neubaugasse", new double[]{48.2000, 16.3500});
        dummyCoordinates.put("Neue Donau", new double[]{48.2500, 16.4000});
        dummyCoordinates.put("Neulaa", new double[]{48.1400, 16.3700});
        dummyCoordinates.put("Niederhofstraße", new double[]{48.1800, 16.3300});
        dummyCoordinates.put("Nußdorfer Straße", new double[]{48.2300, 16.3500});
        dummyCoordinates.put("Ober St. Veit", new double[]{48.1900, 16.2800});
        dummyCoordinates.put("Oberlaa", new double[]{48.1460, 16.3930});
        dummyCoordinates.put("Ottakring", new double[]{48.2100, 16.3100});
        dummyCoordinates.put("Perfektastraße", new double[]{48.1400, 16.3200});
        dummyCoordinates.put("Pilgramgasse", new double[]{48.2000, 16.3600});
        dummyCoordinates.put("Praterstern", new double[]{48.2200, 16.3900});
        dummyCoordinates.put("Rathaus", new double[]{48.2100, 16.3500});
        dummyCoordinates.put("Rennbahnweg", new double[]{48.2600, 16.4300});
        dummyCoordinates.put("Reumannplatz", new double[]{48.1800, 16.3800});
        dummyCoordinates.put("Rochusgasse", new double[]{48.2000, 16.3900});
        dummyCoordinates.put("Rossauer Lände", new double[]{48.2200, 16.3700});
        dummyCoordinates.put("Schlachthausgasse", new double[]{48.1900, 16.4100});
        dummyCoordinates.put("Schönbrunn", new double[]{48.1900, 16.3100});
        dummyCoordinates.put("Schottenring", new double[]{48.2200, 16.3700});
        dummyCoordinates.put("Schottentor", new double[]{48.2200, 16.3600});
        dummyCoordinates.put("Schwedenplatz", new double[]{48.2100, 16.3800});
        dummyCoordinates.put("Schweglerstraße", new double[]{48.2000, 16.3200});
        dummyCoordinates.put("Seestadt", new double[]{48.2400, 16.5000});
        dummyCoordinates.put("Siebenhirten", new double[]{48.1300, 16.3200});
        dummyCoordinates.put("Simmering", new double[]{48.1694, 16.4208});
        dummyCoordinates.put("Spittelau", new double[]{48.2355, 16.3582});
        dummyCoordinates.put("Stadion", new double[]{48.2100, 16.4200});
        dummyCoordinates.put("Stadlau", new double[]{48.2300, 16.4600});
        dummyCoordinates.put("Stadtpark", new double[]{48.2000, 16.3800});
        dummyCoordinates.put("Stephansplatz", new double[]{48.2048, 16.3697});
        dummyCoordinates.put("Stubentor", new double[]{48.2060, 16.3800});
        dummyCoordinates.put("Südtiroler Platz - Hauptbahnhof", new double[]{48.1850, 16.3750});
        dummyCoordinates.put("Taborstraße", new double[]{48.2200, 16.3900});
        dummyCoordinates.put("Taubstummengasse", new double[]{48.2000, 16.3700});
        dummyCoordinates.put("Thaliastraße", new double[]{48.2100, 16.3200});
        dummyCoordinates.put("Troststraße", new double[]{48.1600, 16.3700});
        dummyCoordinates.put("Tscherttegasse", new double[]{48.1500, 16.3200});
        dummyCoordinates.put("Unter St. Veit", new double[]{48.1900, 16.2900});
        dummyCoordinates.put("Volkstheater", new double[]{48.2050, 16.3600});
        dummyCoordinates.put("Vorgartenstraße", new double[]{48.2200, 16.4000});
        dummyCoordinates.put("Währinger Straße - Volksoper", new double[]{48.2300, 16.3500});
        dummyCoordinates.put("Westbahnhof", new double[]{48.1950, 16.3400});
        dummyCoordinates.put("Zieglergasse", new double[]{48.2000, 16.3400});
        dummyCoordinates.put("Zippererstraße", new double[]{48.1800, 16.4130});
        // ... add more as needed
    }

    /**
     * Reads the CSV file, constructs the graph by adding stations and edges.
     * @param path the path to the CSV file
     * @return the populated graph
     */
    public static Graph loadGraphFromCsv(String path) {
        Graph graph = new Graph();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            reader.readLine(); // Skip CSV header line
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 4) continue; // Ignore malformed lines

                String from = parts[0].trim();       // Station A
                String to = parts[1].trim();         // Station B
                String lineName = "U" + parts[2].trim(); // Line number with prefix "U"

                // Get coordinates or default to (0,0)
                double[] fromCoords = dummyCoordinates.getOrDefault(from, new double[]{0, 0});
                double[] toCoords = dummyCoordinates.getOrDefault(to, new double[]{0, 0});

                // Create or get existing stations from the graph
                Station fromStation = graph.getOrCreateStation(from, fromCoords[0], fromCoords[1]);
                Station toStation = graph.getOrCreateStation(to, toCoords[0], toCoords[1]);

                // Compute the distance using Euclidean formula
                double distance = euclidean(fromCoords[0], fromCoords[1], toCoords[0], toCoords[1]);

                // Connect the stations in the graph with edge weight and line label
                graph.connectStations(from, to, distance, lineName);
            }
        } catch (IOException e) {
            e.printStackTrace(); // Print error if file read fails
        }
        return graph; // Return the fully constructed graph
    }

    /**
     * Calculates Euclidean distance between two geographic points.
     * Used as a simple distance metric for graph edge weights.
     */
    private static double euclidean(double lat1, double lon1, double lat2, double lon2) {
        return Math.sqrt(Math.pow(lat1 - lat2, 2) + Math.pow(lon1 - lon2, 2));
    }
}
