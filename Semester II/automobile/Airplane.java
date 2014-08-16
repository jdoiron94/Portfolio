package semester_ii.automobile;

public class Airplane extends Vehicle {

    private final Steering.Type steering;
    private final Wheel[] wheels;
    private final Engine engine;
    private final Wing[] wings;

    public Airplane(int year, boolean floatable, boolean flyable, String model, Steering.Type steering, Wheel[] wheels, Engine engine, Wing... wings) {
        super(year, floatable, flyable, model, steering);
        this.steering = steering;
        this.wheels = wheels;
        this.engine = engine;
        this.wings = wings;
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

    @Override
    public Steering.Type getSteering() {
        return steering;
    }
}