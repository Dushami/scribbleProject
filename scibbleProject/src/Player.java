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
    public Player(){
        PlayerName = "Player 1";
        PlayerScore = 0;
        for (int i = 0; i < letters.length; i++){
            letters[i] = '_';
        }
    }

    /**
     * Method to
     */
}
