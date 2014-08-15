package semester_ii.automobile;

public class Wing extends VehiclePart {

    private final double curvature;

    public Wing(String partName, double curvature) {
        super(partName);
        this.curvature = curvature;
    }

    public double getCurvature() {
        return curvature;
    }
}