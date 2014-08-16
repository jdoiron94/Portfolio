package semester_ii.automobile;

public class Boat extends Vehicle {

    private final Steering.Type steering;
    private final Motor motor;

    public Boat(int year, boolean floatable, boolean flyable, String model, Steering.Type steering, Motor motor) {
        super(year, floatable, flyable, model, steering);
        this.steering = steering;
        this.motor = motor;
    }

    public Motor getMotor() {
        return motor;
    }

    @Override
    public Steering.Type getSteering() {
        return steering;
    }
}