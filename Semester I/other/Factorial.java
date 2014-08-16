package semester_i.other;

import java.util.Scanner;

public class Factorial {

    private static int getFactorial(int number) {
        if (number == 0) {
            return 0;
        } else if (number < 0) {
            return -1;
        }
        int sum = 1;
        for (int i = number; i > 1; i--) {
            sum *= i;
        }
        return sum;
    }

    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 1337 as the sentinel value\n");
        int n = 5;
        while (n != 1337) {
            System.out.print("Enter the 'n' number: ");
            n = scanner.nextInt();
            int factorial = getFactorial(n);
            System.out.println(factorial == -1 ? "Enter valid numbers only (0+)\n" : "The factorial of " + n + " is " + factorial + "\n");
        }
        scanner.close();
    }
}