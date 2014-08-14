package game_database;

/**
 * Used to represent a Strategy board game
 */
public class Strategy extends Board {

    private int piecesRemaining;
    private int territories;

    private boolean won = false;

    public Strategy(int playerCount, String title, String genre, String ageRecommendation, String estimatedTime, int pieceCount, String boardSize) {
        super(playerCount, title, genre, ageRecommendation, estimatedTime, pieceCount, boardSize);
        piecesRemaining = pieceCount;
    }

    /**
     * @return Returns the amount of pieces remaining in the game
     */
    public int getPiecesRemaining() {
        return piecesRemaining;
    }

    /**
     * @return Returns the amount of territories the player possesses
     */
    public int getTerritories() {
        return territories;
    }

    /**
     * Used to decrement the amount of pieces the player has
     *
     * @param pieces The amount of pieces to lose
     */
    public void decrementPieces(int pieces) {
        piecesRemaining -= pieces;
    }

    /**
     * Used to increment the amount of pieces the player has
     *
     * @param pieces The amount of pieces to gain
     */
    public void addPieces(int pieces) {
        piecesRemaining += pieces;
    }

    /**
     * Used to increase the number of territories the player has ownership of
     */
    public void addTerritory() {
        territories++;
    }

    /**
     * The last round of Risk, both players roll their dice to see whom will become victorious and acquire the other's land
     * Player with the higher roll will receive the territory. If roll is equal, the defender keeps their territory
     * Receiver of the territory determines the winner of the game
     */
    @Override
    public void move() {
        int roll = 2 + (int) (Math.random() * 12);
        int player = 2 + (int) (Math.random() * 12);
        if (roll >= player) {
            decrementPieces(piecesRemaining);
            territories -= 1;
        } else {
            won = true;
            addPieces(piecesRemaining);
            addTerritory();
        }
        String[] territories = new String[]{"Asia", "North America", "Europe", "Africa", "Australia", "South America"};
        System.out.println(won ? "You just won " + territories[(int) (Math.random() * territories.length)] : "You just lost " + territories[(int) (Math.random() * territories.length)]);
    }

    /**
     * @return Returns <tt>true</tt> if the player wins the game; otherwise <tt>false</tt>
     */
    @Override
    public boolean isWin() {
        move();
        return won;
    }

    /**
     * @return Returns a String representation of a Strategy board game
     */
    @Override
    public String toString() {
        return super.toString() + "\nPieces remaining: " + piecesRemaining + "\nTerritories: " + territories;
    }
}