package pathfinding;

import java.util.List;

public class Vertex {

    private double weight;
    private boolean visited;

    private final String name;
    private Vertex previous;
    private final List<Edge> adjacent;

    public Vertex(double weight, String name, List<Edge> adjacent) {
        this.weight = weight;
        this.name = name;
        this.adjacent = adjacent;
    }

    public boolean visited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public Vertex getPrevious() {
        return previous;
    }

    public void setPrevious(Vertex previous) {
        this.previous = previous;
    }

    public List<Edge> getAdjacent() {
        return adjacent;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + (int) weight;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Vertex) {
            Vertex vertex = (Vertex) o;
            return name.equals(vertex.name) && adjacent.equals(vertex.adjacent) && previous.equals(vertex.previous) && weight == vertex.weight;
        }
        return false;
    }

    @Override
    public String toString() {
        return name + ": " + weight;
    }
}