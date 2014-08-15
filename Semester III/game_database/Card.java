package game_database;

/**
 * Class used to represent a Card game
 */
public abstract class Card extends Game {

    private int cardCount;

    public Card(int playerCount, String title, String genre, String ageRecommendation, String estimatedTime, int cardCount) {
        super(playerCount, title, genre, ageRecommendation, estimatedTime);
        this.cardCount = cardCount;
    }

    /**
     * Method which will later be used from within children of this class
     */
    public abstract void draw();

    /**
     * @return Returns the card count
     */
    public int getCardCount() {
        return cardCount;
    }

    /**
     * Used to set the card count
     *
     * @param cardCount The card count to be set
     */
    public void setCardCount(int cardCount) {
        this.cardCount = cardCount;
    }

    /**
     * @param min The minimum card value
     * @param max The maximum card value
     * @return Returns a card of value between the minimum and maximum, all inclusive
     */
    public int getCardValue(int min, int max) {
        return min + (int) (Math.random() * max);
    }

    /**
     * @return Returns <tt>false</tt> for the time being, is overridden later
     */
    @Override
    public boolean isWin() {
        return false;
    }

    /**
     * @return Returns a String representation of a Card game
     */
    @Override
    public String toString() {
        return getRepresentation() + "\nCard count: " + cardCount;
    }
}