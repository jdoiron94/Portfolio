package semester_i.other;

import java.util.Scanner;

public class BouncyBall {

    private static int time = 1;
    private static int bounces;
    private static int rounds;
    private static double velocityDecay;
    private static double bouncinessFactor;
    private static double currentHeight;

    private static void compute(int world, double velocity) {
        switch (world) {
            case 1:
                velocityDecay = 32;
                bouncinessFactor = -0.5;
                break;
            case 2:
                velocityDecay = 6;
                bouncinessFactor = -3;
                break;
            case 3:
                velocityDecay = 74;
                bouncinessFactor = -0.22;
                break;
            default:
                velocityDecay = -1;
                bouncinessFactor = -1;
                break;
        }
        if (velocityDecay == -1) {
            return;
        }
        currentHeight += velocity;
        velocity -= velocityDecay;
        if (currentHeight < 0.0) {
            System.out.println("Bounce!");
            currentHeight *= bouncinessFactor;
            velocity *= bouncinessFactor;
            bounces++;
        }
        if (bounces != 5) {
            System.out.println(time == 1 ? "\nTime: " + time + " Height: " + currentHeight : "Time: " + time + " Height: " + currentHeight);
            time++;
        }
        if (bounces < 5) {
            compute(world, velocity);
        } else {
            bounces = 0;
            time = 1;
            currentHeight = 0.0;
        }
    }

    private static void printMenu() {
        System.out.print(rounds == 0 ? "*** Planet Selection ***\n        1: Earth\n      2: Discworld\n       3: Krypton\n        0: Quit\n************************\n\nEnter your choice: " : "\n*** Planet Selection ***\n        1: Earth\n      2: Discworld\n       3: Krypton\n        0: Quit\n************************\n\nEnter your choice: ");
    }

    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);
        while (velocityDecay != -1 && bouncinessFactor != -1) {
            printMenu();
            int world = scanner.nextInt();
            if (world == 0) {
                System.out.println("Goodbye!");
                break;
            }
            while (world < 1 || world > 3) {
                System.out.print("Please enter a valid planet (1-3): ");
                world = scanner.nextInt();
            }
            System.out.print("Initial velocity: ");
            double velocity = scanner.nextDouble();
            while (velocity <= 0.0) {
                System.out.print("Please enter a valid velocity (> 0.0): ");
                velocity = scanner.nextInt();
            }
            compute(world, velocity);
            rounds++;
        }
        scanner.close();
    }
}