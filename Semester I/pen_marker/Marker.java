package semester_i.pen_marker;

public class Marker {

    private final String owner;
    private final String color;

    public Marker(String owner, String color) {
        this.owner = owner;
        this.color = color;
    }

    public String getOwner() {
        return owner;
    }

    public String getColor() {
        return color;
    }
}