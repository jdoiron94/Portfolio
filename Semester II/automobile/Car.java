package semester_ii.automobile;

public class Car extends Vehicle {

    private final Steering.Type steering;
    private final Wheel[] wheels;

    public Car(int year, boolean floatable, boolean flyable, String model, Steering.Type steering, Wheel... wheels) {
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