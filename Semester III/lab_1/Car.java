package semester_iii.lab_1;

public class Car {

    private int year;
    private double speed;
    private boolean totaled;

    private String owner;
    private String make = "Make not set";
    private String model = "Model not set";

    public Car(int year, String owner, String make, String model) {
        this.year = year;
        this.owner = owner;
        this.make = make;
        this.model = model;
    }

    public Car(String owner) {
        this.owner = owner;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void stop() {
        speed = 0.0;
    }

    public void total() {
        totaled = true;
    }

    public boolean isMoving() {
        return speed > 0.0;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public boolean isTotaled() {
        return totaled;
    }

    @Override
    public String toString() {
        return owner + "\n" + year + " " + make + " " + model;
    }
}