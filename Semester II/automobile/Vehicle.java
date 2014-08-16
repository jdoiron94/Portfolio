package semester_ii.automobile;

public class Vehicle {

    private final int year;
    private final boolean floatable;
    private final boolean flyable;

    private final String model;
    private final Steering.Type steering;

    public Vehicle(int year, boolean floatable, boolean flyable, String model, Steering.Type steering) {
        this.year = year;
        this.floatable = floatable;
        this.flyable = flyable;
        this.model = model;
        this.steering = steering;
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

    public String getModel() {
        return model;
    }

    public Steering.Type getSteering() {
        return steering;
    }
}