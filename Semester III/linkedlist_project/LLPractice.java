package semester_iii.linkedlist_project;

import java.util.ArrayList;
import java.util.LinkedList;

public class LLPractice {

    private static final ArrayList<Double> testlist1 = new ArrayList<>();
    private static final LinkedList<Double> testlist2 = new LinkedList<>();

    public static void main(String[] args) {
        long start = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            System.out.println("Add " + i + " AL");
            testlist1.add((int) (Math.random() * testlist1.size()), Math.random() * 1000001);
        }
        for (int i = 0; i < 250000; i++) {
            System.out.println("Remove " + i + " AL");
            testlist1.remove((int) (Math.random() * testlist1.size()));
        }
        System.out.println("ArrayList insertion and removal time: " + (System.nanoTime() - start) + " nanos");
        start = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            System.out.println("Add " + i + " LL");
            testlist2.add((int) (Math.random() * testlist2.size()), Math.random() * 1000001);
        }
        for (int i = 0; i < 250000; i++) {
            System.out.println("Remove " + i + " LL");
            testlist2.remove((int) (Math.random() * testlist2.size()));
        }
        System.out.println("ArrayList insertion and removal time: " + (System.nanoTime() - start) + " nanos");
        System.out.println("LinkedList insertion and removal time: " + (System.nanoTime() - start) + " nanos");
    }
}