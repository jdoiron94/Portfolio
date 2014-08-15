package semester_ii.automobile;

public class Airplane extends Vehicle {

    private final Steering.Type steering;
    private final Wheel[] wheels;
    private final Engine engine;
    private final Wing[] wings;

    public Airplane(String model, Steering.Type steering, int year, boolean floatable, boolean flyable, Wheel[] wheels, Engine engine, Wing[] wings) {
        super(model, steering, year, floatable, flyable);
        this.steering = steering;
        this.wheels = wheels;
        this.engine = engine;
        this.wings = wings;
    }

    public Steering.Type getSteering() {
        return steering;
    }

    public Wheel[] getWheels() {
        return wheels;
    }

    public Engine getEngine() {
        return engine;
    }

    public Wing[] getWings() {
        return wings;
    }
}