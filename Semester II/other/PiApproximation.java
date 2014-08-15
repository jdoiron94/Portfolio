package semester_ii.other;

import java.util.Random;

public class PiApproximation {

    public static void main(String[] args) {
        Random random = new Random(System.nanoTime());
        int inside = 0;
        int outside = 0;
        for (int i = 0; i < 10000; i++) {
            if (Math.pow(random.nextDouble(), 2) + Math.pow(random.nextDouble(), 2) < 1) {
                inside++;
            } else {
                outside++;
            }
        }
        System.out.printf("Pi approximation: %f", (double) inside / outside);
    }
}