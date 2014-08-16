package semester_i.blackjack;

public class Card {

    private final int value;

    private final String name;
    private final Suit suit;

    public Card(int value, String name, Suit suit) {
        this.value = value;
        this.name = name;
        this.suit = suit;
    }

    /**
     * Returns the card's value
     *
     * @return the value of the card
     */
    public int getValue() {
        return value;
    }

    /**
     * Returns the card's name
     *
     * @return the name of the card
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the card's suit
     *
     * @return the suit of the card
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Overrides traditional toString derived from Object superclass
     *
     * @return a String representation of the Card object
     */
    @Override
    public String toString() {
        return String.format("%s of %s", name, suit);
    }

    public enum Suit {
        SPADES,
        DIAMONDS,
        CLUBS,
        HEARTS
    }
}