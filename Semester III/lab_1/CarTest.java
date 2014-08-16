package semester_iii.lab_1;

public class CarTest {

    public static void main(String... args) {
        Car cop = new Car(2013, "A Cop", "Dodge", "Challenger");
        Evo evo = new Evo(false, false, "Mark Allen");
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