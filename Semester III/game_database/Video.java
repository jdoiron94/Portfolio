package semester_iii.game_database;

/**
 * Class used to represent a Video game
 */
public abstract class Video extends Game {

    private String console;
    private String installationSize;

    public Video(int playerCount, String title, String genre, String ageRecommendation, String estimatedTime, String console, String installationSize) {
        super(playerCount, title, genre, ageRecommendation, estimatedTime);
        this.console = console;
        this.installationSize = installationSize;
    }

    /**
     * @return Returns the video game's console type
     */
    public String getConsole() {
        return console;
    }

    /**
     * @return Returns the installation size of the video game
     */
    public String getInstallationSize() {
        return installationSize;
    }

    /**
     * Used to set the console type
     *
     * @param console The type of console
     */
    public void setConsole(String console) {
        this.console = console;
    }

    /**
     * Used to set the installation size of the video game
     *
     * @param installationSize The installation size of the video game
     */
    public void setInstallationSize(String installationSize) {
        this.installationSize = installationSize;
    }

    public abstract void play();

    /**
     * @return Returns <tt>false</tt> for the time being, is overridden later
     */
    @Override
    public boolean isWin() {
        return false;
    }

    /**
     * @return Returns a String representation of a video game
     */
    @Override
    public String toString() {
        return getRepresentation() + "\nConsole: " + console + "\nInstallation size: " + installationSize;
    }
}