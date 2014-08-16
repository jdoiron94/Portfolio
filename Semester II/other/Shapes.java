package semester_ii.other;

import java.util.Scanner;

public class Shapes {

    private static final StringBuilder builder = new StringBuilder(1000);
    private static int height;

    private static void createShape(int asterisks, int spaces, boolean tri, boolean newline) {
        if (asterisks != -1 && spaces != -1) {
            for (int h = 0; h < height; h++) {
                for (int s = 0; s < spaces; s++) {
                    builder.append(" ");
                }
                spaces--;
                for (int a = 0; a < asterisks; a++) {
                    builder.append("*");
                }
                asterisks += tri ? 2 : 0;
                builder.append("\n");
            }
        } else {
            builder.append("\n");
            for (int w = 0; w < height; w++) {
                for (int h = 0; h < height; h++) {
                    builder.append("*");
                }
                builder.append("\n");
            }
        }
        builder.append(newline ? "\n" : "");
    }

    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Specify a height: ");
        height = scanner.nextInt();
        scanner.close();
        if (height > 0) {
            createShape(-1, -1, false, true);
            createShape(1, height - 1, true, true);
            createShape(height, height - 1, false, false);
            System.out.print(builder);
        } else {
            throw new IllegalArgumentException(String.format("(%d) -> LOL, please enter a valid number (1+)", height));
        }
    }
}