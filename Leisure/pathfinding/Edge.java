package pathfinding;

public class Edge {

    private final double weight;

    private final Vertex source;
    private final Vertex destination;

    public Edge(double weight, Vertex source, Vertex destination) {
        this.weight = weight;
        this.source = source;
        this.destination = destination;
    }

    public double getWeight() {
        return weight;
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

    @Override
    public int hashCode() {
        return source.hashCode() + destination.hashCode() + (int) weight;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Edge) {
            Edge edge = (Edge) o;
            return source.equals(edge.source) && destination.equals(edge.destination) && weight == edge.weight;
        }
        return false;
    }

    @Override
    public String toString() {
        return source.getName() + " to " + destination.getName() + ": " + weight;
    }
}