/**
 * Final semester 1 project called scribble, essentially scrabble
 *
 * ScoreSystem class, this will be used to hold all information about the game score.
 * It will be used for both storing the current score and calculating the score of words
 *
 * @author Archie Hamilton
 * @version 1.0
 */

public class ScoreSystem {
    /**
     * Fields to be used throughout this class
     */
    private int scorePlayer1;
    private int scorePlayer2;
    private int scorePlayer3;
    private int scorePlayer4;
    private int scoreComputer;
    private int wordScore;

    /**
     * Default Constructor, initializes all scores to 0
     */
    public ScoreSystem() {
        scorePlayer1 = 0;
        scorePlayer2 = 0;
        scorePlayer3 = 0;
        scorePlayer4 = 0;
        scoreComputer = 0;
        wordScore = 0;
    }

    /**
     * Secondary Constructor, initializes all scores to input from PlayGame class when called
     */
    public ScoreSystem(int player1Score, int player2Score, int player3Score, int player4Score, int computerScore, int wordValue){
        scorePlayer1 = player1Score;
        scorePlayer2 = player2Score;
        scorePlayer3 = player3Score;
        scorePlayer4 = player4Score;
        scoreComputer = computerScore;
        wordScore = wordValue;
    }

    private static final int[] letterValues = {
            1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3,
            1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10
    };

    public static int wordScore(String word) {
        int score = 0;
        for (char letter : word.toCharArray()) {
            if (Character.isLetter(letter)) {
                score += letterValues[Character.toUpperCase(letter) - 'A'];
            }
        }
        return score;
    }
}
