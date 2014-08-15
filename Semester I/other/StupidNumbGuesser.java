package semester_i.other;

import java.util.Scanner;

public class StupidNumbGuesser {

    public static void main(String[] args) {
        String[] encouragement = {"Good job loser!", "You suck (you got it)!", "Slow down, bro."};
        String[] discouragement = {"You suck, wrong.", "Fail.", "Nope."};
        Scanner scanner = new Scanner(System.in);
        int answer = (int) (Math.random() * 10) + 3;
        System.out.print("Guess a number, 2-12: ");
        if (scanner.nextInt() == answer) {
            System.out.print(encouragement[(int) (Math.random() * encouragement.length)]);
        } else {
            System.out.print(discouragement[(int) (Math.random() * discouragement.length)]);
        }
    }
}