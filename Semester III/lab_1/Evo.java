package semester_iii.lab_1;

public class Evo extends Car {

    private boolean decals;
    private boolean modified;

    public Evo(boolean decals, boolean modified, String owner) {
        super(owner);
        this.decals = decals;
        this.modified = modified;
        setMake("Mitsubishi");
        setModel("Evo");
    }

    public void dyno() {
        System.out.println("Actual wheel horsepower: " + (modified ? 750 : 299));
    }

    public void race() {
        System.out.println(modified && (int) (Math.random() * 100) > 35 ? "You won!" : (int) (Math.random() * 100) > 65 ? "You lost." : "You lost.");
    }

    public boolean hasDecals() {
        return decals;
    }

    public boolean isModified() {
        return modified;
    }

    public void setModified(boolean modified) {
        this.modified = modified;
    }

    public void setDecals(boolean decals) {
        this.decals = decals;
    }

    public void modify() {
        modified = true;
    }

    @Override
    public String toString() {
        return super.toString() + "\nDecals: " + decals + "\nModified: " + modified;
    }
}