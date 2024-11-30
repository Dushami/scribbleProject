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
     * Default constructor
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

    /**
     * Method to Remove and refill when used
     */
    public void updateHand(String word, Bag bag) {
        for (char letter : word.toCharArray()) {
            for (int i = 0; i < letters.length; i++) {
                if (letters[i] == letter) {
                    letters[i] = '_';
                    break;
                }
            }
        }

        for (int i = 0; i < letters.length; i++) {
            if (letters[i] == '_') {
                letters[i] = bag.takeLetters(1)[0];
            }
        }
    }

    /** Upadte players score*/
    public void updateScore(int score) {
        PlayerScore += score;
    }
}
