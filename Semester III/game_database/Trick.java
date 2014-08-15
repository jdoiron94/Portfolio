package semester_iii.game_database;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class used to represent a Trick card game
 */
public class Trick extends Card {

    private int tricks;

    private boolean won = false;

    public Trick(int playerCount, String title, String genre, String ageRecommendation, String estimatedTime, int cardCount) {
        super(playerCount, title, genre, ageRecommendation, estimatedTime, cardCount);
    }

    /**
     * @return Returns the amount of tricks the player has
     */
    public int getTricks() {
        return tricks;
    }

    /**
     * Used to add to the player's tricks
     *
     * @param tricks Amount of tricks to add
     */
    public void addTrick(int tricks) {
        this.tricks += tricks;
    }

    /**
     * The player wins the game if they are able to draw a higher card value than the dealer (of the same suit [same suit is always assumed])
     * If the player wins the hand, they receive a trick; otherwise they lose one
     */
    @Override
    public void draw() {
        List<Integer> values = new ArrayList<>(6);
        Collections.addAll(values, 9, 10, 11, 12, 13, 14);
        int dealerIndex = (int) (Math.random() * values.size());
        int dealer = values.get(dealerIndex);
        values.remove(dealer);
        int playerIndex = (int) (Math.random() * values.size());
        int player = values.get(playerIndex);
        values.remove(player);
        String suit = new String[]{"Hearts", "Spades", "Clubs", "Diamonds"}[getCardValue(9, 14)];
        String[] cards = new String[]{"9", "10", "Jack", "Queen", "King", "Ace"};
        if (dealer > player) {
            System.out.println("You lost the trick (" + cards[dealerIndex] + " of " + suit + " > " + cards[playerIndex] + ")");
        } else {
            won = true;
            System.out.println("You won the trick! (" + cards[playerIndex] + " of " + suit + " > " + cards[dealerIndex] + ")");
        }
        addTrick(won ? 1 : -1);
    }

    /**
     * @return Returns <tt>true</tt> if the player wins the game; otherwise <tt>false</tt>
     */
    @Override
    public boolean isWin() {
        draw();
        return won;
    }

    /**
     * @return Returns a String representation of a Trick card game
     */
    @Override
    public String toString() {
        return super.toString() + "\nTricks: " + tricks;
    }
}