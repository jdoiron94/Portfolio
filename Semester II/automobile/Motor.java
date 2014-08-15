package semester_ii.automobile;

public class Motor extends VehiclePart {

    private final int horsepower;

    public Motor(String partName, int horsepower) {
        super(partName);
        this.horsepower = horsepower;
    }

    public int getHorsepower() {
        return horsepower;
    }
}