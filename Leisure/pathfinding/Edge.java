package pathfinding;

public class Edge {

    private final Vertex source;
    private final Vertex destination;

    private final double weight;

    public Edge(Vertex source, Vertex destination, double weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public Vertex getSource() {
        return source;
    }

    public Vertex getDestination() {
        return destination;
    }

    public Vertex getAdjacent(Vertex vertex) {
        return vertex.equals(source) ? destination : source;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public int hashCode() {
        return source.hashCode() + destination.hashCode() + (int) weight;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Edge) {
            Edge edge = (Edge) o;
            return source.equals(edge.getSource()) && destination.equals(edge.getDestination()) && weight == edge.getWeight();
        }
        return false;
    }

    @Override
    public String toString() {
        return source.getName() + " to " + destination.getName() + ": " + weight;
    }
}