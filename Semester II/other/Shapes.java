package semester_ii.other;

import java.util.Scanner;

public class Shapes {

    private static final StringBuilder BUILDER = new StringBuilder();
    private static int height;

    private static void createShape(int asterisks, int spaces, boolean tri, boolean newline) {
        if (asterisks != -1 && spaces != -1) {
            for (int h = 0; h < height; h++) {
                for (int s = 0; s < spaces; s++) {
                    BUILDER.append(" ");
                }
                spaces--;
                for (int a = 0; a < asterisks; a++) {
                    BUILDER.append("*");
                }
                asterisks += tri ? 2 : 0;
                BUILDER.append("\n");
            }
        } else {
            BUILDER.append("\n");
            for (int w = 0; w < height; w++) {
                for (int h = 0; h < height; h++) {
                    BUILDER.append("*");
                }
                BUILDER.append("\n");
            }
        }
        BUILDER.append(newline ? "\n" : "");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Specify a height: ");
        height = scanner.nextInt();
        scanner.close();
        if (height > 0) {
            createShape(-1, -1, false, true);
            createShape(1, height - 1, true, true);
            createShape(height, height - 1, false, false);
            System.out.print(BUILDER.toString());
        } else {
            throw new IllegalArgumentException(String.format("(%d) -> LOL, please enter a valid number (1+)", height));
        }
    }
}