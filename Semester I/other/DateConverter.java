package semester_i.other;

import java.util.Scanner;

public class DateConverter {

    private static final String[] MONTHS = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    private static String retrieveDate(int day, int month, int year) {
        String date = String.valueOf(day);
        date += ' ' + MONTHS[month - 1] + ' ' + year;
        return date;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a day: ");
        int day = scanner.nextInt();
        System.out.print("Enter a month: ");
        int month = scanner.nextInt();
        System.out.print("Enter a year: ");
        int year = scanner.nextInt();
        System.out.print(retrieveDate(day, month, year));
    }
}