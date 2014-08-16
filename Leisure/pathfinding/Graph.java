package pathfinding;

import java.util.List;

public class Graph {

    private final List<Vertex> vertices;
    private final List<Edge> edges;

    private final String name;

    public Graph(List<Vertex> vertices, List<Edge> edges, String name) {
        this.vertices = vertices;
        this.edges = edges;
        this.name = name;
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return vertices.hashCode() + edges.hashCode() + name.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Graph) {
            Graph graph = (Graph) o;
            return vertices.equals(graph.getVertices()) && edges.equals(graph.getEdges()) && name.equals(graph.getName());
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(name).append('\t');
        builder.append("Vertices: ").append(vertices.size()).append(", Edges:").append(edges.size());
        return builder.toString();
    }
}