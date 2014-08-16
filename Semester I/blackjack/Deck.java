package semester_i.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private final List<Card> cards = new ArrayList<>(52);
    private final List<Card> discardedCards = new ArrayList<>(52);

    public Deck() {
        create();
    }

    /**
     * Returns the deck of cards
     *
     * @return the deck of cards
     */
    public List<Card> getCards() {
        return cards;
    }

    /**
     * Returns the discarded cards of the previous game
     *
     * @return the discarded cards of the previous game
     */
    public List<Card> getDiscardedCards() {
        return discardedCards;
    }

    /**
     * Creates a new deck in order (all suits of one card before moving to next card)
     */
    public void create() {
        if (!discardedCards.isEmpty()) {
            for (Card card : discardedCards) {
                cards.add(card);
            }
            discardedCards.clear();
        } else {
            for (int i = 1; i < 14; i++) {
                String name = i == 1 ? "Ace" : i <= 10 ? String.valueOf(i) : i == 11 ? "Jack" : i == 12 ? "Queen" : i == 13 ? "King" : null;
                for (Card.Suit suit : Card.Suit.values()) {
                    cards.add(new Card(i <= 10 ? i : 10, name, suit));
                }
            }
        }
    }

    /**
     * Shuffles with default Collections class (uses default Random seed)
     *
     * Shuffles a few more times (human-like), up to 5 times
     */
    public void shuffle() {
        Collections.shuffle(cards);
        for (int i = 0; i < (int) (Math.random() * 5) + 1; i++) {
            for (int j = 0; j < 52; j++) {
                Collections.swap(cards, j, (int) (Math.random() * 52));
            }
        }
    }

    /**
     * Takes the topmost card from the deck and then moves it to the discard pile
     *
     * @return the newly most picked card
     */
    public Card hit() {
        Card card = cards.get(0);
        removeCard(card);
        return card;
    }

    /**
     * Removes a specific card from the deck
     *
     * @param card the card to be removed from the deck
     */
    private void removeCard(Card card) {
        cards.remove(card);
        discardedCards.add(card);
    }
}