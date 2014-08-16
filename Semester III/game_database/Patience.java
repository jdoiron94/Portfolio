package semester_iii.game_database;

/**
 * Class used to represent a Patience card game
 */
public class Patience extends Card {

    private int remainingCards;
    private int pairs;
    private boolean won;

    public Patience(int playerCount, int cardCount, String title, String genre, String ageRecommendation, String estimatedTime) {
        super(playerCount, cardCount, title, genre, ageRecommendation, estimatedTime);
    }

    /**
     * @return Returns the amount of remaining cards
     */
    public int getRemainingCards() {
        return remainingCards;
    }

    /**
     * @return Returns the amount of cards paired
     */
    public int getPairs() {
        return pairs;
    }

    /**
     * Used to decrement the amount of remaining cards
     */
    public void decrementRemaining() {
        remainingCards -= 2;
    }

    /**
     * Used to add a pair of cards paired
     */
    public void addPair() {
        pairs++;
    }

    /**
     * For the sake of Pyramid (and this program), if 10 or more pairs are made, the player is assumed to have won
     * A pair is considered to be two cards whose sum is 13 (King - using French deck method)
     */
    @Override
    public void draw() {
        for (int i = 0; i < 28; i++) {
            int[] cards = {getCardValue(1, 13), getCardValue(1, 13)};
            if (cards[0] + cards[1] == 13) {
                System.out.println(cards[0] + " + " + cards[1] + " = 13 (King)");
                addPair();
                decrementRemaining();
            }
        }
        if (pairs > 10) {
            won = true;
        }
        System.out.println(won ? "You won the game!" : "You lost the game");
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
     * @return Returns a String representation of a Patience card game
     */
    @Override
    public String toString() {
        return super.toString() + "\nRemaining cards: " + remainingCards + "\nPairs: " + pairs;
    }
}