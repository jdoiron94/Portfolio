package semester_ii.automobile;

public class Bicycle extends Vehicle {

    private final Steering.Type steering;
    private final Wheel[] wheels;

    public Bicycle(String model, Steering.Type steering, int year, boolean floatable, boolean flyable, Wheel[] wheels) {
        super(model, steering, year, floatable, flyable);
        this.steering = steering;
        this.wheels = wheels;
    }

    public Steering.Type getSteering() {
        return steering;
    }

    public Wheel[] getWheels() {
        return wheels;
    }
}