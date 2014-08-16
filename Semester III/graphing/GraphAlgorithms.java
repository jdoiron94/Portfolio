package semester_iii.graphing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Jacob Doiron
 * COSC 201
 * GraphAlgorithms.java, Project III
 * 12/10/13
 * Class used to solve shortest path problems given a directed graph
 */
public class GraphAlgorithms {

    /*
     *  Method using the Bellman Ford shortest path algorithm
     *  Supplied with an adjacency matrix, a source node, and a destination node
     *  Finds and returns the shortest length of the path between source and destination (or -1 in the case of an error or negative cycle)
     */
    private int bellmanFord(int[][] graph, int source, int destination) {
        int[] distances = new int[graph.length];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;
        List<Edge> edges = new ArrayList<>(25);
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                if (graph[i][j] != 0) {
                    edges.add(new Edge(i, j, graph[i][j]));
                }
            }
        }
        for (int[] ignored : graph) {
            for (Edge edge : edges) {
                if (distances[edge.getSource()] != Integer.MAX_VALUE) {
                    distances[edge.getDestination()] = Math.min(distances[edge.getDestination()], distances[edge.getSource()] + edge.getWeight());
                }
            }
        }
        for (Edge edge : edges) {
            if (distances[edge.getSource()] != Integer.MAX_VALUE && distances[edge.getDestination()] > distances[edge.getSource()] + edge.getWeight()) {
                System.out.print("Negative cycle detected: ");
                return -1;
            }
        }
        if (distances[destination] == Integer.MAX_VALUE) {
            System.out.print("No path available: ");
            return -1;
        }
        return distances[destination];
    }

    /*
     *  Method using the Floyd Warshall shortest path algorithm
     *  Suppled with an adjacency matrix, a source node, and a destination node
     *  Finds and returns the shortest length of the path between source and destination (or -1 in the case of an error or negative cycle)
     */
    private int floydWarshall(int[][] graph, int s, int d) {
        double[][] distances = new double[graph.length][graph.length];
        double[][] temporary = new double[graph.length][graph.length];
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                distances[i][j] = graph[i][j] != 0 ? graph[i][j] : Double.MAX_VALUE;
            }
        }
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                for (int k = 0; k < graph.length; k++) {
                    temporary[j][k] = Math.min(distances[j][k], distances[j][i] + distances[i][k]);
                }
            }
            for (int j = 0; j < graph.length; j++) {
                System.arraycopy(temporary[j], 0, distances[j], 0, graph.length);
            }
        }
        for (int i = 0; i < graph.length; i++) {
            if (distances[i][i] < 0) {
                System.out.print("Negative cycle detected: ");
                return -1;
            }
        }
        if (distances[s][d] == Double.MAX_VALUE) {
            System.out.print("No path available: ");
            return -1;
        }
        return (int) distances[s][d];
    }

    /*
     *  Method which takes an adjacency matrix, algorithm, and two nodes
     *  Decides which algorithm to utilize
     *  Will return -1 if a source or destination node is not present, or if an invalid algorithm is specified
     *  Runs the appropriate algorithm and returns the result of the shortest path from source to destination
     */
    public int processGraph(int[][] graph, int algorithm, int source, int destination) {
        if (source < 0 || source > graph.length - 1) {
            System.out.print("Source doesn't exist: ");
            return -1;
        } else if (destination < 0 || destination > graph.length - 1) {
            System.out.print("Destination doesn't exist: ");
            return -1;
        } else if (source == destination) {
            return 0;
        }
        switch (algorithm) {
            case 1:
                return bellmanFord(graph, source, destination);
            case 2:
                return floydWarshall(graph, source, destination);
            default:
                System.out.print("Invalid algorithm");
                return -1;
        }
    }
}