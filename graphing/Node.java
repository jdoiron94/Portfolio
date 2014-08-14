package graphing;

public class Node {

    private final int source;
    private final int destination;
    private final int distance;

    public Node(int source, int destination, int distance) {
        this.source = source;
        this.destination = destination;
        this.distance = distance;
    }

    public int getSource() {
        return source;
    }

    public int getDestination() {
        return destination;
    }

    public int getDistance() {
        return distance;
    }
}