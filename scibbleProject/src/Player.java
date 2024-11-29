/**
 * Final semester 1 project called scribble, essentially scrabble
 *
 * Player class, this will initialize a player in the game, Their name and their
 * current score as well as their current rack of 7 tiles which will be randomly filled
 * by the 'Bag' class
 *
 * @author Archie Hamilton
 * @version 1.0
 */

public class Player {
    /**
     * Fields for all player data
     */

    private String PlayerName;
    private int PlayerScore;
    private char[] letters = new char[7];

    /**
     * Default constructor,
     */
    public Player(String PlayerName, Bag bag) {
        this.PlayerName = PlayerName;
        PlayerScore = 0;
        letters = bag.takeLetters(7);
    }

    /**
     * Get Methods
     */
    public String getPlayerName() {
        return PlayerName;
    }

    public int getPlayerScore() {
        return PlayerScore;
    }

    public char[] getLetters() {
        return letters;
    }
}
