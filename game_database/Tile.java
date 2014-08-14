package game_database;

/**
 * Class used to represent a Tile board game
 */
public class Tile extends Board {

    private int tiles;
    private int pieces = 7;
    private int pairs;

    private boolean won = false;

    public Tile(int playerCount, String title, String genre, String ageRecommendation, String estimatedTime, int pieceCount, String boardSize, int tiles) {
        super(playerCount, title, genre, ageRecommendation, estimatedTime, pieceCount, boardSize);
        this.tiles = tiles;
    }

    /**
     * Used to decrement the amount of pieces by one
     */
    public void decrementPieces() {
        pieces -= 1;
    }

    /**
     * Used to add a pair
     */
    public void addPair() {
        pairs++;
    }

    /**
     * A player is assumed to have won if they are able to pair four or more of seven dominoes (for the sake of the program)
     * Remaining pieces and pairs made are updating accordingly
     */
    @Override
    public void move() {
        int[] pieces = {getPieceValue(), getPieceValue(), getPieceValue(), getPieceValue(), getPieceValue(), getPieceValue(), getPieceValue()};
        for (int i = 0; i < this.pieces; i++) {
            int value = getPieceValue();
            for (int p : pieces) {
                if (p == value) {
                    addPair();
                    decrementPieces();
                }
            }
        }
        if (pairs > 4) {
            won = true;
        }
        System.out.println(won ? "You won the game!" : "You lost the game");
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
     * @return Returns a String representation of a Tile board game
     */
    @Override
    public String toString() {
        return super.toString() + "\nTiles: " + tiles + "\nPairs: " + pairs + "\nPieces left: " + pieces;
    }
}