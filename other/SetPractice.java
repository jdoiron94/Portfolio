package other;

import java.util.Collections;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * Jacob Doiron
 * 9/8/13
 * COSC 201
 * Lab 8
 */

public class SetPractice {

    /**
     * Purpose of this assignment is to show how HashSet does not have an assigned order, and that TreeSet's default order is ascending (though you can iterate descendingly).
     */
    public static void main(String[] args) {
        HashSet<Integer> myHash = new HashSet<>();
        Collections.addAll(myHash, 1, 10, 32, 12, 5, 4, 12, 33, 19, 10, 22, 4, 60, 89);
        TreeSet<Integer> myTree = new TreeSet<>();
        Collections.addAll(myTree, 1, 10, 32, 12, 5, 4, 12, 33, 19, 10, 22, 4, 60, 89);
        System.out.println("HashSet: ");
        for (int i : myHash) {
            System.out.println(i);
        }
        System.out.println("\nDescending tree:");
        for (int i : myTree.descendingSet()) {
            System.out.println(i);
        }
    }
}