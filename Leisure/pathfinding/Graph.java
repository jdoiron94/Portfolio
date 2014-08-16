package pathfinding;

import java.util.List;

public class Graph {

    private final String name;

    private final List<Vertex> vertices;
    private final List<Edge> edges;

    public Graph(String name, List<Vertex> vertices, List<Edge> edges) {
        this.name = name;
        this.vertices = vertices;
        this.edges = edges;
    }

    public String getName() {
        return name;
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    @Override
    public int hashCode() {
        return vertices.hashCode() + edges.hashCode() + name.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Graph) {
            Graph graph = (Graph) o;
            return vertices.equals(graph.vertices) && edges.equals(graph.edges) && name.equals(graph.name);
        }
        return false;
    }

    @Override
    public String toString() {
        return name + '\t' + "Vertices: " + vertices.size() + ", Edges:" + edges.size();
    }
}