package semester_iii.game_database;

/**
 * Used to represent an RPG video game
 */
public class RPG extends Video {

    private int strangeCharacters;
    private boolean won;

    public RPG(int playerCount, int strangeCharacters, String title, String genre, String ageRecommendation, String estimatedTime, String console, String installationSize) {
        super(playerCount, title, genre, ageRecommendation, estimatedTime, console, installationSize);
        this.strangeCharacters = strangeCharacters;
    }

    /**
     * @return Returns the amount of strange characters encountered
     */
    public int getStrangeCharacters() {
        return strangeCharacters;
    }

    /**
     * Used to add to the count of strange characters found
     */
    public void addStrangeCharacter() {
        strangeCharacters++;
    }

    /**
     * Used to determine if the player beats the quest (or if they can even find the npc to talk with)
     */
    public void quest() {
        if ((int) (Math.random() * 100) > 50) {
            addStrangeCharacter();
            System.out.println("You found the npc to talk with");
            if ((int) (Math.random() * 75) > 50) {
                addStrangeCharacter();
                System.out.println("And you beat the baws");
            } else {
                System.out.println("And he baws owned you");
            }
        } else {
            System.out.println("You couldn't even find the npc, lol n00b");
        }
    }

    /**
     * If the player beats the quest, will there be another one awaiting them?
     * Should there be another quest, the player has not yet beaten the game; otherwise, the player has won
     */
    @Override
    public void play() {
        quest();
        if ((int) (Math.random() * 10) % 2 == 0) {
            won = true;
            System.out.println("You beat the last quest!");
        } else {
            System.out.println("There are many more adventures for you, young warrior");
        }
    }

    /**
     * @return Returns <tt>true</tt> if the player beats the game; otherwise <tt>false</tt>
     */
    @Override
    public boolean isWin() {
        play();
        return won;
    }

    /**
     * @return Returns a String representation of an RPG video game
     */
    @Override
    public String toString() {
        return super.toString() + "\nStrange characters: " + strangeCharacters;
    }
}