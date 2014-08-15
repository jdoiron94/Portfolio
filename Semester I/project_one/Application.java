package semester_i.project_one;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter first name: ");
        String first = scanner.nextLine();
        System.out.print("Enter last name: ");
        String last = scanner.nextLine();
        System.out.print("Enter month of birth: ");
        int month = scanner.nextInt();
        System.out.print("Enter day of birth: ");
        int day = scanner.nextInt();
        System.out.print("Enter year of birth: ");
        int year = scanner.nextInt();
        HeartRate rate = new HeartRate(first, last, month, day, year);
        System.out.print("\n" + String.format("%s %s %d/%d/%d %d years old\nMax heart rate: %d beats per minute\nTarget heart range: %.1f-%.2f beats per minute", rate.getFirst(), rate.getLast(), rate.getMonth(), rate.getDay(), rate.getYear(), rate.getAge(), rate.getMaximumBPM(), rate.getLowRate(), rate.getHighRate()));
    }
}