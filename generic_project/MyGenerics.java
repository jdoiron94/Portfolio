package generic_project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MyGenerics {

    public static <K extends Comparable<? super K>> K min(K[] k) {
        return Collections.min(Arrays.asList(k));
    }

    public static <K extends Comparable<? super K>> List<K> max2(K[] k) {
        Arrays.sort(k);
        List<K> maxes = new ArrayList<>(2);
        maxes.add(k[k.length - 1]);
        maxes.add(k[k.length - 2]);
        return maxes;
    }

    public static void main(String[] args) {
        Integer[] a = {1, 2, 3, 4, 5};
        System.out.println("Min: " + min(a));
        System.out.println("Maxes: " + Arrays.toString(max2(a).toArray()));
        String[] b = {"a", "b", "c", "d", "e"};
        System.out.println("Min: " + min(b));
        System.out.println("Maxes: " + Arrays.toString(max2(b).toArray()));
    }
}