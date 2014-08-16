package semester_i.other;

import java.util.Scanner;

public class DateConverter {

    private static final String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    private static String retrieveDate(int day, int month, int year) {
        return String.valueOf(day) + ' ' + months[month - 1] + ' ' + year;
    }

    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a day: ");
        int day = scanner.nextInt();
        System.out.print("Enter a month: ");
        int month = scanner.nextInt();
        System.out.print("Enter a year: ");
        System.out.print(retrieveDate(day, month, scanner.nextInt()));
        scanner.close();
    }
}