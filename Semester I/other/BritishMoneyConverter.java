package semester_i.other;

import java.util.Scanner;

public class BritishMoneyConverter {

    private static int pennies;

    private static void setPennies(int pennies) {
        BritishMoneyConverter.pennies = pennies;
    }

    private static int calcPounds() {
        int pounds = pennies / 240;
        pennies -= pounds * 240;
        return pounds;
    }

    private static int calcShillings() {
        int shillings = pennies / 12;
        pennies -= shillings * 12;
        return shillings;
    }

    private static int calcPence() {
        return pennies;
    }

    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("How many pennies? ");
        pennies = scanner.nextInt();
        scanner.close();
        System.out.printf("£%d %ds %dd", calcPounds(), calcShillings(), calcPence());
    }
}