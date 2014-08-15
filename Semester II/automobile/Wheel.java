package semester_ii.automobile;

public class Wheel extends VehiclePart {

    private final double rotation;
    private final Type type;

    public Wheel(String partName, Type type, double rotation) {
        super(partName);
        this.type = type;
        this.rotation = rotation;
    }

    public Type getType() {
        return type;
    }

    public double getRotation() {
        return rotation;
    }

    public enum Type {
        OFFROAD,
        ONROAD,
        TRACK,
        ALL_WEATHER
    }
}