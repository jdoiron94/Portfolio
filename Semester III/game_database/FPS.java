package semester_iii.game_database;

/**
 * Class used to represent a FPS video game
 */
public class FPS extends Video {

    private int butthurt;
    private int kills;
    private int deaths;

    private boolean won = false;

    public FPS(int playerCount, String title, String genre, String ageRecommendation, String estimatedTime, String console, String installationSize, int butthurt) {
        super(playerCount, title, genre, ageRecommendation, estimatedTime, console, installationSize);
        this.butthurt = butthurt;
    }

    /**
     * @return Returns the amount of butthurt players
     */
    public int getButthurt() {
        return butthurt;
    }

    /**
     * @return Returns the amount of kills the player has
     */
    public int getKills() {
        return kills;
    }

    /**
     * @return Returns the amount of deaths the player has
     */
    public int getDeaths() {
        return deaths;
    }

    /**
     * Used to update the butthurt count
     */
    public void addButthurt() {
        butthurt++;
    }

    /**
     * Used to kill members of the same team, further adding to the butthurt population
     */
    public void teamkill() {
        int killed = (int) (Math.random() * (getPlayerCount() / 2) - 1);
        deaths += killed;
        kills -= killed;
        butthurt = killed;
        System.out.println("You killed: " + killed + "teammates!");
    }

    /**
     * Used to rush the enemy, racks up a number of kills and a death
     */
    public void rush() {
        int killed = (int) (Math.random() * getPlayerCount());
        kills += killed;
        deaths++;
        System.out.println("You rushed the spawn and killed: " + killed + " before dying");
    }

    /**
     * If the player is bored, they will teamkill (teamkill is on); otherwise they will rush the spawn
     * Results may vary
     */
    @Override
    public void play() {
        int bored = (int) (Math.random() * 100);
        if (bored > 45) {
            teamkill();
        } else {
            rush();
        }
        won = kills >= getPlayerCount() / 2;
        System.out.println(won ? "You carried the team with " + kills + " and won" : "You suck");
    }

    /**
     * @return Returns <tt>true</tt> if the player wins the round; otherwise <tt>false</tt>
     */
    @Override
    public boolean isWin() {
        play();
        return won;
    }

    /**
     * @return Returns a String representation of a FPS video game
     */
    @Override
    public String toString() {
        return super.toString() + "\nButthurt: " + butthurt + "\nRound end: " + kills + ":" + deaths;
    }
}