package semester_ii.automobile;

public class Steering {

    private final Type type;

    public Steering(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        WHEEL,
        HANDLEBARS,
        FLIGHT_CONTROL
    }
}