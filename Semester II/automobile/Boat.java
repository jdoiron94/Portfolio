package semester_ii.automobile;

public class Boat extends Vehicle {

    private final Steering.Type steering;
    private final Motor motor;

    public Boat(String model, Steering.Type steering, int year, boolean floatable, boolean flyable, Motor motor) {
        super(model, steering, year, floatable, flyable);
        this.motor = motor;
        this.steering = steering;
    }

    public Steering.Type getSteering() {
        return steering;
    }

    public Motor getMotor() {
        return motor;
    }
}