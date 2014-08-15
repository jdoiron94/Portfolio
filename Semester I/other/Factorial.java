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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 1337 as the sentinel value\n");
        while (true) {
            System.out.print("Enter the 'n' number: ");
            int n = scanner.nextInt();
            if (n == 1337) {
                break;
            }
            int factorial = getFactorial(n);
            if (factorial == -1) {
                System.out.println("Enter valid numbers only (0+)\n");
            } else {
                System.out.println("The factorial of " + n + " is " + factorial + "\n");
            }
        }
    }
}