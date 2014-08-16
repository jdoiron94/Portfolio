package semester_ii.automobile;

public class Bicycle extends Vehicle {

    private final Steering.Type steering;
    private final Wheel[] wheels;

    public Bicycle(int year, boolean floatable, boolean flyable, String model, Steering.Type steering, Wheel[] wheels) {
        super(year, floatable, flyable, model, steering);
        this.steering = steering;
        this.wheels = wheels;
    }

    public Wheel[] getWheels() {
        return wheels;
    }

    @Override
    public Steering.Type getSteering() {
        return steering;
    }
}