package pathfinding;

import java.util.List;

public class Vertex {

    private double weight;
    private boolean visited = false;

    private Vertex previous;
    private final String name;

    private final List<Edge> adjacent;
    
    public Vertex(int weight, List<Edge> adjacent, String name) {
        this.weight = weight;
        this.adjacent = adjacent;
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public boolean visited() {
        return visited;
    }

    public Vertex getPrevious() {
        return previous;
    }

    public List<Edge> getAdjacent() {
        return adjacent;
    }

    public String getName() {
        return name;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void setPrevious(Vertex previous) {
        this.previous = previous;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + (int) weight;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Vertex) {
            Vertex vertex = (Vertex) o;
            return name.equals(vertex.getName()) && adjacent.equals(vertex.getAdjacent()) && previous.equals(vertex.getPrevious()) && weight == vertex.getWeight();
        }
        return false;
    }

    @Override
    public String toString() {
        return name + ": " + weight;
    }
}