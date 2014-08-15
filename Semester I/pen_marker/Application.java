package semester_i.pen_marker;

import java.util.Scanner;

public class Application {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Marker[] marker = new Marker[3];

    private static void createMarker(int index) {
        System.out.print("Enter a name: ");
        String name = scanner.nextLine();
        System.out.print("Enter a color: ");
        String color = scanner.nextLine();
        marker[index] = new Marker(name, color);
    }

    public static void main(String[] args) {
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
            if (selected >= 0 && selected <= 2) {
                System.out.println("Marker belongs to " + marker[selected].getOwner() + " and the color of the marker itself is " + marker[selected].getColor());
            } else {
                System.out.println("Lol, enter a valid number... (0-2)");
            }
        }
    }
}