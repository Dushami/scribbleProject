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

    public static int wordScore(String word, int[] startCoords, char direction, String[][] multiplier, char[][] board) {
        int score = 0;
        int wordMultiplier = 1;

        for (int i = 0; i < word.length(); i++) {
            int row = startCoords[0] + (direction == 'V' ? i : 0);
            int column = startCoords[1] + (direction == 'H' ? i : 0);
            char letter = word.charAt(i);

            int letterScore = letterValues[Character.toUpperCase(letter) - 'A'];
            score += letterScore;

            if (multiplier[row][column] != null) {
                switch (multiplier[row][column]) {
                    case "DL":
                        score += letterScore;
                        break;
                    case "TL":
                        score += 2 * letterScore;
                        break;
                    case "DW":
                        wordMultiplier *= 2;
                        break;
                    case "TW":
                        wordMultiplier *= 3;
                        break;
                }
            }
        }

        return score * wordMultiplier;
    }

    /**
     * method to display all the current scores
     */
    public static void displayAllScores(Player[] players) {
        System.out.println("+----------------------------+");
        System.out.println("|          Leaderboard       |");
        System.out.println("+----------------------------+");
        for (Player player : players) {
            System.out.printf("%s: %d Points\n", player.getPlayerName(), player.getPlayerScore());
        }
        System.out.println("+----------------------------+");
    }
}
