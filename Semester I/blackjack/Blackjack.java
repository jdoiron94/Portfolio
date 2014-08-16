package semester_i.blackjack;

import java.util.Scanner;

public class Blackjack {

    private static int sum;

    private static final Scanner scanner = new Scanner(System.in);

    private static void handleAce() {
        System.out.print("Use Ace as 1 (enter 1) or 11 (enter 2): ");
        sum += scanner.nextInt() == 1 ? 1 : 11;
    }

    public static void main(String... args) {
        Deck deck = new Deck();
        while (true) {
            System.out.println("*************** New Round Commencing! ***************\n");
            sum = 0;
            deck.shuffle();
            Card[] cards = {deck.hit(), deck.hit()};
            System.out.println("First card: " + cards[0] + "\nSecond card: " + cards[1]);
            for (Card card : cards) {
                if (card.getValue() == 1) {
                    handleAce();
                    continue;
                }
                sum += card.getValue();
            }
            while (sum < 21) {
                System.out.print("Total sum: " + sum + "\nHit (enter 1) or Stand (enter 2): ");
                if (scanner.nextInt() == 1) {
                    Card newCard = deck.hit();
                    System.out.println("New card: " + newCard);
                    if (newCard.getValue() == 1) {
                        handleAce();
                    } else {
                        sum += newCard.getValue();
                    }
                } else {
                    System.out.println("Staying with " + sum);
                    break;
                }
            }
            System.out.println(sum == 21 ? "Blackjack!" : "Bust! (" + sum + ")");
            System.out.print("\nContinue (enter 1) or Quit (enter 2): ");
            if (scanner.nextInt() == 2) {
                break;
            }
            System.out.println();
            deck.create();
            System.out.println();
        }
    }
}