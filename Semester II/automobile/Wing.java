package semester_ii.automobile;

public class Wing extends VehiclePart {

    private final double curvature;

    public Wing(double curvature, String partName) {
        super(partName);
        this.curvature = curvature;
    }

    public double getCurvature() {
        return curvature;
    }
}