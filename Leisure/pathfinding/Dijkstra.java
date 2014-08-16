package pathfinding;

import java.util.ArrayList;
import java.util.List;

public class Dijkstra {

    private final Graph graph;

    public Dijkstra(Graph graph) {
        this.graph = graph;
    }

    public Graph getGraph() {
        return graph;
    }

    public void findPath(Vertex source, Vertex destination) {
        List<Vertex> unvisited = new ArrayList<>(20);
        for (Vertex vertex : graph.getVertices()) {
            vertex.setWeight(Double.POSITIVE_INFINITY);
            vertex.setVisited(false);
            vertex.setPrevious(null);
        }
        source.setWeight(0.0);
        unvisited.add(source);
        Vertex vertex = source;
        while (!unvisited.isEmpty() && !destination.visited() && vertex != null) {
            for (Edge edge : vertex.getAdjacent()) {
                edge.getAdjacent(vertex).setWeight(Math.min(vertex.getWeight(), vertex.getWeight() + edge.getWeight()));
            }
            vertex.setVisited(true);
            unvisited.remove(vertex);
            Vertex next = null;
            for (Edge edge : vertex.getAdjacent()) {
                Vertex vert = edge.getAdjacent(vertex);
                if (next == null || !vert.visited() && vert.getWeight() < next.getWeight()) {
                    next = vert;
                }
            }
            vertex = next;
            unvisited.add(vertex);
        }
    }
}