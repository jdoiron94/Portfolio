package semester_ii.automobile;

public class Engine extends VehiclePart {

    private final int thrustPower;

    public Engine(int thrustPower, String partName) {
        super(partName);
        this.thrustPower = thrustPower;
    }

    public int getThrustPower() {
        return thrustPower;
    }
}