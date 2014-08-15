package semester_iii.game_database;

import java.util.Comparator;

/**
 * Base class used to represent a Game (most basic)
 */
public abstract class Game {

    /**
     * Comparator later to be used to sort games by their titles
     */
    public static final Comparator<Game> COMPARATOR = new Comparator<Game>() {
        @Override
        public int compare(Game g1, Game g2) {
            return g1.getTitle().compareTo(g2.getTitle());
        }
    };

    private int playerCount;
    private String title;
    private String genre;
    private String ageRecommendation;
    private String estimatedTime;

    public Game(int playerCount, String title, String genre, String ageRecommendation, String estimatedTime) {
        this.playerCount = playerCount;
        this.title = title;
        this.genre = genre;
        this.ageRecommendation = ageRecommendation;
        this.estimatedTime = estimatedTime;
    }

    /**
     * @return Will return <tt>true</tt> if the player wins the game; otherwise <tt>false</tt>
     */
    public abstract boolean isWin();

    /**
     * @return Will return a String representation of the classes extending Game
     */
    public abstract String toString();

    /**
     * @return Returns the player count of the game
     */
    public int getPlayerCount() {
        return playerCount;
    }

    /**
     * Used to set the desired player count
     *
     * @param playerCount The amount of players to play the game
     */
    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

    /**
     * @return Returns the title of the game
     */
    public String getTitle() {
        return title;
    }

    /**
     * Used to set the title of the game
     *
     * @param title The title of the game
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return Returns the genre of the game
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Used to set the genre of the game
     *
     * @param genre The genre of the game
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * @return Returns the age recommendation of the game
     */
    public String getAgeRecommendation() {
        return ageRecommendation;
    }

    /**
     * Used to set the age recommendation of the game
     *
     * @param ageRecommendation The age recommendation of the game
     */
    public void setAgeRecommendation(String ageRecommendation) {
        this.ageRecommendation = ageRecommendation;
    }

    /**
     * @return Returns the estimated time the game will take to play
     */
    public String getEstimatedTime() {
        return estimatedTime;
    }

    /**
     * Used to set the estimated time a game will take
     *
     * @param estimatedTime The estimated time a game will take to play
     */
    public void setEstimatedTime(String estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    /**
     * @return Returns a String representation of a game (typical toString)
     */
    public String getRepresentation() {
        return title + " (" + genre + ")\nRecommended ages: " + ageRecommendation + "\nEstimated time: " + estimatedTime + "\nPlayers: " + playerCount;
    }
}