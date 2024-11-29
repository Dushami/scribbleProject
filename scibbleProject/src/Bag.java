/**
 * Final semester 1 project called scribble, essentially scrabble
 *
 * Bag class, this will hold all the letters available for the game as well as
 * distributing the letters to a players hand when they use a letter.
 *
 * @author Archie Hamilton
 * @version 1.0
 */

import java.util.Random;

public class Bag {
    /**
     * Fields
     */
    private char[] letters;
    private int[] numEachLetters;
    private char[] lettersAvailable;
    private int lettersLeft;

    /**
     * default constructor to set the bag to have standard number of
     * each letter.
     */
    public Bag() {
        letters = new char[] {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        numEachLetters = new int[] {9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1};

        lettersLeft = 0;
        for (int numEachLetter : numEachLetters){
            lettersLeft += numEachLetter;
        }

        lettersAvailable = new char[lettersLeft];
        int index = 0;
        for (int i = 0 ; i < letters.length; i++){
            for (int j = 0 ; j < numEachLetters[i]; j++){
               lettersAvailable[index++] = letters[i];
            }
        }
        randomizeAvailableLetters();
    }

    /**
     * randomize the order of Available letters, using Fisher-Yates shuffle
     */
    private void randomizeAvailableLetters(){
        Random rand = new Random();
        for (int i = lettersLeft - 1; i > 0; i--){
            int j = rand.nextInt(i + 1);
            char temp = lettersAvailable[i];
            lettersAvailable[i] = lettersAvailable[j];
            lettersAvailable[j] = temp;
        }
    }

    /**
     * Method allowing users to take tiles at the start of the game
     *
     * @param count
     * @return hand
     */
    public char[] takeLetters(int count) {
        char[] hand = new char[count];
        for (int i = 0; i < count; i++) {
            if (lettersLeft > 0) {
                hand[i] = lettersAvailable[--lettersLeft];
                for (int j = 0; j < letters.length; j++) {
                    if (letters[j] == hand[i]) {
                        numEachLetters[j]--;
                        break;
                    }
                }
            } else {
                hand[i] = '_';
            }
        }
        return hand;
    }


}
