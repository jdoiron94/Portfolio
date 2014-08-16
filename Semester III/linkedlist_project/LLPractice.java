package semester_iii.linkedlist_project;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LLPractice {

    private static final List<Double> arrayList = new ArrayList<>(1000000);
    private static final List<Double> linkedList = new LinkedList<>();

    public static void main(String... args) {
        long start = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            System.out.println("Add " + i + " AL");
            arrayList.add((int) (Math.random() * arrayList.size()), Math.random() * 1000001);
        }
        for (int i = 0; i < 250000; i++) {
            System.out.println("Remove " + i + " AL");
            arrayList.remove((int) (Math.random() * arrayList.size()));
        }
        System.out.println("ArrayList insertion and removal time: " + (System.nanoTime() - start) + " nanos");
        start = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            System.out.println("Add " + i + " LL");
            linkedList.add((int) (Math.random() * linkedList.size()), Math.random() * 1000001);
        }
        for (int i = 0; i < 250000; i++) {
            System.out.println("Remove " + i + " LL");
            linkedList.remove((int) (Math.random() * linkedList.size()));
        }
        System.out.println("ArrayList insertion and removal time: " + (System.nanoTime() - start) + " nanos");
        System.out.println("LinkedList insertion and removal time: " + (System.nanoTime() - start) + " nanos");
    }
}