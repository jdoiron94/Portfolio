package semester_iii.game_database;

/**
 * Class used to represent a board game
 */
public abstract class Board extends Game {

    private int pieceCount;

    private String boardSize;

    public Board(int playerCount, int pieceCount, String title, String genre, String ageRecommendation, String estimatedTime, String boardSize) {
        super(playerCount, title, genre, ageRecommendation, estimatedTime);
        this.pieceCount = pieceCount;
        this.boardSize = boardSize;
    }

    /**
     * @return Returns the piece count
     */
    public int getPieceCount() {
        return pieceCount;
    }

    /**
     * Used to set the piece count of the board game
     *
     * @param pieceCount The amount of pieces to be used
     */
    public void setPieceCount(int pieceCount) {
        this.pieceCount = pieceCount;
    }

    /**
     * @return Returns the size of the board
     */
    public String getBoardSize() {
        return boardSize;
    }

    /**
     * Used to set the size of the board game
     *
     * @param boardSize The size of the board to be used
     */
    public void setBoardSize(String boardSize) {
        this.boardSize = boardSize;
    }

    /**
     * @return Returns the value of a piece
     */
    public int getPieceValue() {
        return (int) (Math.random() * 7);
    }

    /**
     * Will be required and used from within my classes extending Board
     */
    public abstract void move();

    /**
     * @return Returns <tt>false</tt> for the time being, is overridden later
     */
    @Override
    public boolean isWin() {
        return false;
    }

    /**
     * @return Returns a String representation of a board game
     */
    @Override
    public String toString() {
        return getRepresentation() + "\nPiece count: " + pieceCount + "\nBoard size: " + boardSize;
    }
}