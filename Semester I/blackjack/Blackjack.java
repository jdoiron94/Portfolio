package semester_i.blackjack;

import java.util.Scanner;

public class Blackjack {

    private static final Scanner scanner = new Scanner(System.in);
    private static int sum = 0;

    private static void handleAce() {
        System.out.print("Use Ace as 1 (enter 1) or 11 (enter 2): ");
        int decision = scanner.nextInt();
        if (decision == 1) {
            sum += 1;
        } else if (decision == 2) {
            sum += 11;
        }
    }

    public static void main(String[] args) {
        Deck deck = new Deck();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("*************** New Round Commencing! ***************\n");
            deck.shuffle();
            Card cardOne = deck.getCards().get(0);
            deck.removeCard(cardOne);
            Card cardTwo = deck.getCards().get(0);
            deck.removeCard(cardTwo);
            System.out.println("First card: " + cardOne.toString() + "\nSecond card: " + cardTwo.toString());
            int valueOne = cardOne.getValue();
            int valueTwo = cardTwo.getValue();
            sum = 0;
            if (valueOne == 1) {
                handleAce();
            } else {
                sum += valueOne;
            }
            if (valueTwo == 1) {
                handleAce();
            } else {
                sum += valueTwo;
            }
            while (sum < 21) {
                System.out.print("Total sum: " + sum + "\nHit (enter 1) or Stand (enter 2): ");
                int decision = scanner.nextInt();
                if (decision == 1) {
                    Card newCard = deck.getCards().get(0);
                    deck.removeCard(newCard);
                    System.out.println("New card: " + newCard.toString());
                    int newValue = newCard.getValue();
                    if (newValue == 1) {
                        handleAce();
                    } else {
                        sum += newValue;
                    }
                } else {
                    System.out.println("Staying with " + sum);
                    break;
                }
            }
            if (sum == 21) {
                System.out.println("Blackjack!");
            } else if (sum > 21) {
                System.out.println("Bust! (" + sum + ")");
            }
            System.out.print("\nContinue (enter 1) or Quit (enter 2): ");
            int decision = scanner.nextInt();
            if (decision == 2) {
                break;
            }
            System.out.println();
            deck.create();
            System.out.println();
        }
    }
}