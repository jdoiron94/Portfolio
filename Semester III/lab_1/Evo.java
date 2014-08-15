package lab_1;

public class Evo extends Car {

    private boolean decals = false;
    private boolean modified = false;

    public Evo(String owner, boolean decals, boolean modified) {
        super(owner);
        setMake("Mitsubishi");
        setModel("Evo");
        this.decals = decals;
        this.modified = modified;
    }

    public void dyno() {
        System.out.println("Actual wheel horsepower: " + (modified ? 750 : 299));
    }

    public void race() {
        System.out.println(modified && (int) (Math.random() * 100) > 35 ? "You won!" : (int) (Math.random() * 100) > 65 ? "You lost." : "You lost.");
    }

    public void modify() {
        modified = true;
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

    @Override
    public String toString() {
        return super.toString() + "\nDecals: " + decals + "\nModified: " + modified;
    }
}