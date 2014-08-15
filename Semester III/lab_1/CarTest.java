package semester_iii.lab_1;

public class CarTest {

    public static void main(String[] args) {
        Car cop = new Car("A Cop", "Dodge", "Challenger", 2013);
        Evo evo = new Evo("Mark Allen", false, false);
        evo.setYear(2013);
        evo.modify();
        evo.dyno();
        evo.race();
        evo.setSpeed(150.8);
        cop.setSpeed(100);
        cop.total();
        cop.stop();
        evo.setSpeed(175.3);
        System.out.println("\n" + cop + "\n\n" + evo);
    }
}