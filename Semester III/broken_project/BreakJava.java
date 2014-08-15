package semester_iii.broken_project;

import java.util.Arrays;

public class BreakJava {

    private static int seqStart;
    private static int seqEnd;

    /**
     * Technically, this will not break (assuming you do not try to use integers over Integer.MAX_VALUE (2,147,483,647)) (as well as assuming you do not
     * try to use a number of integers that will exceed the amount of memory being dedicated to the program. More than ~45M integers will throw an OutOfMemoryError, dependent
     * on the amount of memory you are allocating to your program) It will just take an extremely long period of time due to being O(N^3)
     * Using more than 7500 elements is not efficient with this algorithm (7500 takes roughly a minute)
     */
    private static int subSum(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += nums[k];
                }
                if (sum > max) {
                    max = sum;
                    seqStart = i;
                    seqEnd = j;
                }
            }
        }
        return max;
    }

    /**
     * Fibonacci will have integer overflow (break) using numbers 47 -> ∞
     */
    private static int fib(int n) {
        return (n == 0 || n == 1) ? n : fib(n - 1) + fib(n - 2);
    }

    public static void main(String[] args) {
        switch (args[0].toLowerCase()) {
            case "f":
                System.out.println("Fibonacci of " + Integer.parseInt(args[1]) + " is " + fib(Integer.parseInt(args[1])));
                break;
            case "s":
                int[] randoms = new int[Integer.parseInt(args[1])];
                for (int i = 0; i < randoms.length; i++) {
                    randoms[i] = -1000 + (int) (Math.random() * 1001);
                    System.out.println(randoms[i]);
                }
                int sum = subSum(randoms);
                int[] seq = new int[seqEnd - seqStart + 1];
                System.arraycopy(randoms, seqStart, seq, 0, seqEnd - seqStart + 1);
                System.out.println("\nStart: " + seqStart + ", end: " + seqEnd + ", sum: " + sum + "\nSequence: " + Arrays.toString(seq));
                break;
            default:
                System.err.println("Command-line fail");
                break;
        }
    }
}