package semester_i.valid_date;

import java.util.Scanner;

public class Application {

    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter month: ");
        int month = scanner.nextInt();
        System.out.print("Enter day: ");
        int day = scanner.nextInt();
        System.out.print("Enter year: ");
        Day date = new Day(month, day, scanner.nextInt());
        System.out.print(date + " is valid: " + date.isValid());
    }
}