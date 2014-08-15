package semester_iii.curse_you_alan_the_platypus_project;

import java.util.ArrayDeque;
import java.util.Collections;

/**
 * Jacob Doiron
 * COSC 201
 * Platypus.java, Lab 9
 * Used to solve lab 9
 */
public class Platypus {

    /*
     *  Maximum contiguous subsequence sum solved recursively using divide and conquer
     */
    private static int mcss(int[] a, int l, int r) {
        if (l == r) {
            return a[l] < 0 ? 0 : a[l];
        }
        int c = (l + r) / 2;
        int lSum = 0, lTemp = 0, rSum = 0, rTemp = 0;
        for (int i = c; i >= l; i--) {
            lTemp += a[i];
            lSum = lTemp > lSum ? lTemp : lSum;
        }
        for (int i = c + 1; i <= r; i++) {
            rTemp += a[i];
            rSum = rTemp > rSum ? rTemp : rSum;
        }
        return Math.max(Math.max(mcss(a, l, c), mcss(a, c + 1, r)), lSum + rSum);
    }

    /*
     *  Base method
     */
    private static void permute(String s) {
        permute("", s);
    }

    /*
     *  Overload which prints all permutations of given String 's'
     */
    private static void permute(String p, String s) {
        if (s.length() == 1) {
            System.out.println(p + s);
        }
        for (int i = 0; i < s.length(); i++) {
            permute(p + s.charAt(i), s.substring(0, i) + s.substring(i + 1, s.length()));
        }
    }

    /*
     *  Used to test aforementioned methods appropriately
     */
    public static void main(String[] args) {
        int[] a = new int[]{-2, 11, -4, 13, -5, 2};
        System.out.println(mcss(a, 0, a.length - 1));
        permute("abc");
        ArrayDeque<Integer> s = new ArrayDeque<>();
        Collections.addAll(s, 1, 4, 22, -4, 3, 1);
        for (int i : s) {
            System.out.println(i);
        }
    }
}