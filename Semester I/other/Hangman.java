package semester_i.other;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Hangman {

    private static final List<String> ANSWERS = new ArrayList<>();

    private static int correct = 0;
    private static int incorrect = 0;

    private static void drawStickMan(int incorrect) {
        switch (incorrect) {
            case 0:
                System.out.println("\t  ------\n\t |     |\n\t       |\n\t       |\n\t       |\n\t       |\n\t       |\n\t_______|");
                break;
            case 1:
                System.out.println("\t  ------\n\t |     |\n\t O     |\n\t       |\n\t       |\n\t       |\n\t       |\n\t_______|");
                break;
            case 2:
                System.out.println("\t  ------\n\t |     |\n\t O     |\n\t\\      |\n\t       |\n\t       |\n\t       |\n\t_______|");
                break;
            case 3:
                System.out.println("\t  ------\n\t |     |\n\t O     |\n\t\\ /    |\n\t       |\n\t       |\n\t       |\n\t_______|");
                break;
            case 4:
                System.out.println("\t  ------\n\t |     |\n\t O     |\n\t\\ /    |\n\t |     |\n\t       |\n\t       |\n\t_______|");
                break;
            case 5:
                System.out.println("\t  ------\n\t |     |\n\t O     |\n\t\\ /    |\n\t |     |\n\t/      |\n\t       |\n\t_______|");
                break;
            case 6:
                System.out.println("\t  ------\n\t |     |\n\t O     |\n\t\\ /    |\n\t |     |\n\t/ \\    |\n\t       |\n\t_______|\n");
                break;
        }
    }

    private static void search() {
        try {
            System.out.println("Finding le words..");
            BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("http://www.hangmanproject.x10.mx").openStream()));
            String[] words = reader.readLine().split("<br />");
            reader.close();
            Collections.addAll(ANSWERS, words);
        } catch (Exception ignored) {
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int rounds = correct + incorrect;
            if (rounds == 0 || rounds % 20 == 0) {
                search();
                if (ANSWERS.size() != 20) {
                    if (ANSWERS.size() > 0) {
                        ANSWERS.clear();
                    }
                    Collections.addAll(ANSWERS, "BOAT", "TRUCK", "ANIMAL", "ALLIGATOR", "KEYBOARD", "INPUT", "PROGRAMMING", "COMPUTERS", "NETWORKING", "NEUROBIOLOGY", "FIREMEN", "POLICE", "NUMBERS", "BINARY", "HEXADECIMAL", "OCTAL", "WIFE", "HUSBAND", "WEDDING", "MARRIAGE");
                }
            }
            int guesses = 6;
            int tries = 0;
            int incorrectGuesses = 0;
            String word = ANSWERS.get((int) (Math.random() * ANSWERS.size()));
            String masterWord = word;
            System.out.println((correct + incorrect != 0 ? "\n----------New Round Commencing!----------" : "----------New Round Commencing!----------")/* + "\nAnswer: " + word + "\n"*/);
            String blooped = "";
            for (int i = 0; i < word.length(); i++) {
                blooped += ".";
            }
            while (guesses > 0) {
                System.out.print((tries != 0 ? "\nCurrently: " : "Currently: ") + blooped + "\nEnter a letter or solve: ");
                String input = scanner.nextLine().toUpperCase().trim();
                tries++;
                if (input.length() > 1) {
                    if (input.equals(masterWord)) {
                        correct++;
                        System.out.println("Correct guess!\n");
                        break;
                    } else {
                        incorrectGuesses++;
                        guesses--;
                        System.out.println("How about no.  You have " + guesses + " incorrect " + (guesses != 1 ? "guesses " : "guess ") + "left.");
                    }
                } else if (input.length() == 1) {
                    int activator = word.indexOf(input);
                    if (activator >= 0) {
                        while (true) {
                            int index = word.indexOf(input);
                            if (index == -1) {
                                break;
                            }
                            blooped = blooped.substring(0, index) + input + blooped.substring(index + 1, blooped.length());
                            word = word.substring(0, index) + "." + word.substring(index + 1, word.length());
                        }
                    } else {
                        incorrectGuesses++;
                        guesses--;
                        System.out.println("How about no.  You have " + guesses + " incorrect guesses left.");
                    }
                }
                if (blooped.equals(masterWord)) {
                    correct++;
                    System.out.println("Correct guess!\n");
                    break;
                } else {
                    drawStickMan(incorrectGuesses);
                }
                if (guesses == 0) {
                    incorrect++;
                    System.out.println("The word was \"" + masterWord + "\"");
                }
            }
            ANSWERS.remove(masterWord);
            System.out.print("Play again? (Enter anything but 'N' to continue): ");
            String answer = scanner.nextLine().toUpperCase();
            if (answer.contains("N")) {
                break;
            }
        }
        System.out.println("You ended with " + correct + " correct " + (correct != 1 ? "solutions " : "solution ") + "and " + incorrect + " incorrect " + (incorrect != 1 ? "solutions." : "solution."));
    }
}