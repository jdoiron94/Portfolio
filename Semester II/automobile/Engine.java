package semester_ii.automobile;

public class Engine extends VehiclePart {

    private final int thrustPower;

    public Engine(String partName, int thrustPower) {
        super(partName);
        this.thrustPower = thrustPower;
    }

    public int getThrustPower() {
        return thrustPower;
    }
}