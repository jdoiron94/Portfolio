package semester_iii.lab_0;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Application {

    private static int sum(int a, int b, int c) {
        return a + b + c;
    }

    private static void fibonacci(int n) {
        if (n == 0) {
            System.out.println(n + "\n");
            return;
        }
        System.out.println(1);
        int result;
        int[] previous = {0, 1};
        for (int i = 1; i < n; i++) {
            result = previous[0] + previous[1];
            previous[0] = previous[1];
            previous[1] = result;
            System.out.println(result);
        }
    }

    private static int recursiveFibonacci(int n) {
        return n == 0 || n == 1 ? n : recursiveFibonacci(n - 1) + recursiveFibonacci(n - 2);
    }

    private static void fibonacciR(int n) {
        System.out.println(recursiveFibonacci(n) + "\n");
    }

    private static void readFile() {
        File file = new File("C:\\Users\\Jacob\\Downloads\\ints.txt"); // set to your own path to ints.txt file
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static void main(String... args) {
        System.out.println(sum(10, 20, 30) + "\n");
        fibonacci(5);
        System.out.println();
        fibonacciR(5);
        readFile();
        System.out.println();
        System.out.println(new CalculationSet(1, 2, 3, 4) + "\n");
        System.out.println(new CalculationSet(1, 2, 3, 4, 5));
    }
}