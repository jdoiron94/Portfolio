package semester_ii.automobile;

public class Vehicle {

    private final String model;
    private final Steering.Type steering;
    private final int year;
    private final boolean floatable;
    private final boolean flyable;

    public Vehicle(String model, Steering.Type steering, int year, boolean floatable, boolean flyable) {
        this.model = model;
        this.steering = steering;
        this.year = year;
        this.floatable = floatable;
        this.flyable = flyable;
    }

    public String getModel() {
        return model;
    }

    public Steering.Type getSteering() {
        return steering;
    }

    public int getYear() {
        return year;
    }

    public boolean isFloatable() {
        return floatable;
    }

    public boolean isFlyable() {
        return flyable;
    }
}