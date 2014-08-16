package semester_ii.automobile;

public class Wheel extends VehiclePart {

    private final double rotation;

    private final Type type;

    public Wheel(double rotation, String partName, Type type) {
        super(partName);
        this.rotation = rotation;
        this.type = type;
    }

    public double getRotation() {
        return rotation;
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        OFFROAD,
        ONROAD,
        TRACK,
        ALL_WEATHER
    }
}