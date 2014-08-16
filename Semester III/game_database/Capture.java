package semester_iii.game_database;

/**
 * Class used to represent a Capture board game
 */
public class Capture extends Board {

    private boolean won;

    private String boardState;

    public Capture(int playerCount, int pieceCount, String title, String genre, String ageRecommendation, String estimatedTime, String boardSize, String boardState) {
        super(playerCount, pieceCount, title, genre, ageRecommendation, estimatedTime, boardSize);
        this.boardState = boardState;
    }

    /**
     * @return Returns the current state of the capture game
     */
    public String getBoardState() {
        return boardState;
    }

    /**
     * Used to set the state of the board
     *
     * @param boardState The state of the board to be updated
     */
    public void setBoardState(String boardState) {
        this.boardState = boardState;
    }

    /**
     * Assumes the last move of Checkers with both opponents maintaining a king
     * Odds are in favor of the opponent, however (as you are in a bad location to win the game)
     */
    @Override
    public void move() {
        boolean king = getPieceValue() > 5;
        if (king && getPieceValue() > 5) {
            won = true;
        }
        System.out.println(won ? "You jumped the opponent's last king!" : "The opponent's king jumped your last king");
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
     * @return Returns a String representation of a Capture board game
     */
    @Override
    public String toString() {
        return super.toString() + "\nBoard state: " + boardState;
    }
}