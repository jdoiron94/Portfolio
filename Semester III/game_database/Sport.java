package semester_iii.game_database;

/**
 * Class used to represent a Sport video game
 */
public class Sport extends Video {

    private String team;

    private int scores;
    private int fails;

    private boolean won = false;

    public Sport(int playerCount, String title, String genre, String ageRecommendation, String estimatedTime, String console, String installationSize, String team) {
        super(playerCount, title, genre, ageRecommendation, estimatedTime, console, installationSize);
        this.team = team;
    }

    /**
     * @return Returns the player's current team
     */
    public String getTeam() {
        return team;
    }

    /**
     * Used to set the current team
     *
     * @param team The team to change to
     */
    public void setTeam(String team) {
        this.team = team;
    }

    /**
     * @return Returns the scores the player has made
     */
    public int getScores() {
        return scores;
    }

    /**
     * @return Returns the amount of fails the player has accumulated (the opponent's score)
     */
    public int getFails() {
        return fails;
    }

    /**
     * Used as an attempt to score the final goal
     */
    public void score() {
        int offence = (int) (Math.random() * 100);
        int defence = (int) (Math.random() * 100);
        if (offence > defence) {
            scores++;
        } else if (offence == defence) {
            scores = fails;
        } else {
            fails++;
        }
    }

    /**
     * The game is tied, and the player is about to try for another goal. Will they succeed, or will they fail?
     */
    @Override
    public void play() {
        scores = (int) (Math.random() * 6);
        fails = scores;
        score();
        if (scores > fails) {
            won = true;
            System.out.println("You won the game!");
        } else {
            System.out.println("You missed the goal, failure");
        }
    }

    /**
     * @return Returns <tt>true</tt> if the player wins the game; otherwise <tt>false</tt>
     */
    @Override
    public boolean isWin() {
        play();
        return won;
    }

    /**
     * @return Returns a String representation of a Sport video game
     */
    @Override
    public String toString() {
        return super.toString() + "\nTeam: " + team + "\nScores: " + scores + "\nOpponent: " + fails;
    }
}