package semester_iii.game_database;

/**
 * Class used to represent a Banking card game
 */
public class Banking extends Card {

    private double money;
    private double bet;
    private boolean won;

    public Banking(int playerCount, int cardCount, String title, String genre, String ageRecommendation, String estimatedTime) {
        super(playerCount, cardCount, title, genre, ageRecommendation, estimatedTime);
    }

    /**
     * @return Returns the amount of money the player is in possession of
     */
    public double getMoney() {
        return money;
    }

    /**
     * Used to set the amount of playing money
     *
     * @param money The amount of money the player wishes to use
     */
    public void setMoney(double money) {
        this.money = money;
    }

    /**
     * @return Returns the bet the player has specified for the hand
     */
    public double getBet() {
        return bet;
    }

    /**
     * Used to set the player's bet
     *
     * @param bet The amount of money the player wishes to bet
     */
    public void setBet(double bet) {
        this.bet = bet;
    }

    /**
     * Used to add the player's winnings to their stack
     *
     * @param money The amount of money the player will be adding
     */
    public void addMoney(double money) {
        this.money += money;
    }

    /**
     * The player wins the game if their hand totals 21 (blackjack), or if their hand value is greater than the dealer's
     * The player's money is incremented or decremented according to their win status
     */
    @Override
    public void draw() {
        int dealer = getCardValue(1, 11) + getCardValue(1, 11);
        int player = getCardValue(1, 11) + getCardValue(1, 11);
        won = false;
        String status;
        if (dealer == 21 || dealer > player) {
            status = "You lost the hand";
        } else if (dealer == player) {
            status = "Tie";
        } else {
            status = "You won the hand";
            won = true;
        }
        addMoney(won ? bet : -bet);
        System.out.println("Dealer: " + dealer + "\nPlayer: " + player + "\nStatus: " + status);
    }

    /**
     * @return <tt>true</tt> if the player wins the game; otherwise <tt>false</tt>
     */
    @Override
    public boolean isWin() {
        draw();
        return won;
    }

    /**
     * @return Returns the string representation of a banking game
     */
    @Override
    public String toString() {
        return super.toString() + "\nMoney: $" + money + "\nBet: $" + bet;
    }
}