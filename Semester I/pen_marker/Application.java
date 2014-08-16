package semester_i.pen_marker;

import java.util.Scanner;

public class Application {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Marker[] marker = new Marker[3];

    private static void createMarker(int index) {
        System.out.print("Enter a name: ");
        String name = scanner.nextLine();
        System.out.print("Enter a color: ");
        marker[index] = new Marker(name, scanner.nextLine());
    }

    public static void main(String... args) {
        for (int i = 0; i < 3; i++) {
            createMarker(i);
            System.out.println();
        }
        System.out.println("Enter 1337 as the sentinel value to end the program.");
        while (true) {
            System.out.print("Enter an ID (int) to find one's name and color (0-2): ");
            int selected = scanner.nextInt();
            if (selected == 1337) {
                break;
            }
            System.out.println(selected >= 0 && selected <= 2 ? "Marker belongs to " + marker[selected].getOwner() + " and the color of the marker itself is " + marker[selected].getColor() : "Lol, enter a valid number... (0-2)");
        }
        scanner.close();
    }
}