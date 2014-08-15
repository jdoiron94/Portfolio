package semester_iii.other;

import java.util.Arrays;

public class Classwork {

    private static <K> K copyOne(K a, K b) {
        return a = b;
    }

    private static <K> K[] copyTwo(K[] a, K[] b) {
        a = (K[]) new Object[b.length];
        System.arraycopy(b, 0, a, 0, b.length);
        return a;
    }

    private static <K> K[] returnCopy(K[] a) {
        return Arrays.copyOf(a, a.length);
    }

    public static void main(String[] args) {
        Object a = "k";
        Object b = ";-;";
        a = copyOne(a, b);
        System.out.println(a);
        System.out.println(Arrays.toString(copyTwo(new Object[]{"a", "b", "c"}, new Object[]{"c, d, e"})));
        Object[] z = new Object[]{"a", "b", "c"};
        System.out.println(a.hashCode());
        Object[] copied = returnCopy(z);
        System.out.println(Arrays.hashCode(copied) + "\n" + Arrays.toString(copied));
    }

    /**
     * What's a graph: G = (V, E)
     * G: graph
     * V: set of vertices/nodes
     * E: set of edges/links
     * directed vs undirected
     * cyclic vs acyclic
     * how to represent graphs?
     *      1: adjacency matrix: inefficient from a memory standpoint if graph is sparse
     *      2: adjacency list: a list of lists. Each entry in my adjacency list is a list of all edges that that vertex is connected to
     * positive weighted shortest path: directed, weighted, arbitrary graph
     *
     * dijkstra's algorithm: edsger dijkstra
     * the cruelty of really teaching computer science - 1988
     * "testing shows the presence, not the absence, of bugs"
     * "computer science is no more about computers than astronomy is about telescopes"
     * "the use of cobol cripples the mind; its teaching should, therefore, be regarded as a criminal offense"
     * "programming is one of the most difficult branches of applied mathematics; the poorer mathematicians had better remain pure mathematicians"
     *
     * start by initializing a distance value to every vertex
     * set the source to 0, all other vertices to large number
     * mark all vertices as unvisited
     * loop:
     *      looking at the current vertex (start with source), add the current vertex's value to the edge weight of one of its neighbors
     *      if that neighbor has a value greater than the new value, replace
     *      once we've looked at all neighbors of the current vertex, mark it as visited, then move to the next vertex according to distance
     *      repeat until we've visited the destination vertex
     *
     */
}

class MaxSumTest {

    /**
     * Cubic maximum contiguous subsequence sum algorithm.
     */
    public static int maxSubSum1(int[] a) {
        int maxSum = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j < a.length; j++) {
                int thisSum = 0;
                for (int k = i; k <= j; k++) {
                    thisSum += a[k];
                }
                if (thisSum > maxSum) {
                    maxSum = thisSum;
                }
            }
        }
        return maxSum;
    }


    /**
     * Quadratic maximum contiguous subsequence sum algorithm.
     */
    public static int maxSubSum2(int[] a) {
        int maxSum = 0;
        for (int i = 0; i < a.length; i++) {
            int thisSum = 0;
            for (int j = i; j < a.length; j++) {
                thisSum += a[j];
                if (thisSum > maxSum) {
                    maxSum = thisSum;
                }
            }
        }
        return maxSum;
    }

    /**
     * Recursive maximum contiguous subsequence sum algorithm.
     * Finds maximum sum in subarray spanning a[left..right].
     * Does not attempt to maintain actual best sequence.
     */
    private static int maxSumRec(int[] a, int left, int right) {
        if (left == right) { // Base case
            if (a[left] > 0) {
                return a[left];
            } else {
                return 0;
            }
        }
        int center = (left + right) / 2;
        int maxLeftSum = maxSumRec(a, left, center);
        int maxRightSum = maxSumRec(a, center + 1, right);
        int maxLeftBorderSum = 0, leftBorderSum = 0;
        for (int i = center; i >= left; i--) {
            leftBorderSum += a[i];
            if (leftBorderSum > maxLeftBorderSum) {
                maxLeftBorderSum = leftBorderSum;
            }
        }
        int maxRightBorderSum = 0, rightBorderSum = 0;
        for (int i = center + 1; i <= right; i++) {
            rightBorderSum += a[i];
            if (rightBorderSum > maxRightBorderSum) {
                maxRightBorderSum = rightBorderSum;
            }
        }
        return max3(maxLeftSum, maxRightSum, maxLeftBorderSum + maxRightBorderSum);
    }

    /**
     * Driver for divide-and-conquer maximum contiguous
     * subsequence sum algorithm.
     */
    public static int maxSubSum3(int[] a) {
        return maxSumRec(a, 0, a.length - 1);
    }

    /**
     * Return maximum of three integers.
     */
    private static int max3(int a, int b, int c) {
        return a > b ? a > c ? a : c : b > c ? b : c;
    }

    /**
     * Linear-time maximum contiguous subsequence sum algorithm.
     */
    public static int maxSubSum4(int[] a) {
        int maxSum = 0, thisSum = 0;
        for (int anA : a) {
            thisSum += anA;
            if (thisSum > maxSum) {
                maxSum = thisSum;
            } else if (thisSum < 0) {
                thisSum = 0;
            }
        }
        return maxSum;
    }

    /**
     * Simple test program.
     */
    public static void main(String[] args) {
        int a[] = {4, -3, 5, -2, -1, 2, 6, -2};
        int maxSum;
        maxSum = maxSubSum1(a);
        System.out.println("Max sum is " + maxSum);
        maxSum = maxSubSum2(a);
        System.out.println("Max sum is " + maxSum);
        maxSum = maxSubSum3(a);
        System.out.println("Max sum is " + maxSum);
        maxSum = maxSubSum4(a);
        System.out.println("Max sum is " + maxSum);
    }
}