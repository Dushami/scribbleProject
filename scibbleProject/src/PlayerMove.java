import java.util.Scanner;

/**
 * Final semester 1 project called scribble, essentially scrabble
 *
 * PlayerMove class, this will handle the input of each player and allow them
 * to pick its position on the board, before placing the word it will validate
 * that it:
 * 1. Is connected to another word on the board, or is the first word and therefore connects to [7][7]
 * 2. Can be fit onto the board
 * 3. Can be made with the tiles in the players hand
 *
 * @author Archie Hamilton
 * @version 1.0
 */

public class PlayerMove {

    /**
     * display player tiles and ask them to enter the word they want to play
     *
     * @param player
     * @return word the user has typed
     */
    public String getPlayerWord(Player player) {
        Scanner playerWord = new Scanner(System.in);
        System.out.println("-------------------------------------------------------------");
        System.out.println(player.getPlayerName() + ", your current tiles: " + String.valueOf(player.getLetters()));
        System.out.println();
        System.out.print("Please enter the word you want to play: ");
        return playerWord.nextLine().toUpperCase();
    }

    /**
     * Ask user to unput coordinate of first letter
     * @return
     */
    public int[] getWordCoordinates() {
        Scanner input = new Scanner(System.in);
        int[] coordinates = new int[2];
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter the coordinate of the first letter, Row then Column (e.g., 4 8): ");
            String[] inputCoordinate = input.nextLine().split(" ");
            if (inputCoordinate.length == 2) {
                try {
                    coordinates[0] = Integer.parseInt(inputCoordinate[0]) - 1;
                    coordinates[1] = Integer.parseInt(inputCoordinate[1]) - 1;

                    if (coordinates[0] >= 0 && coordinates[0] < 15 && coordinates[1] >= 0 && coordinates[1] < 15) {
                        validInput = true;
                    } else {
                        System.err.println("Invalid input! Coordinates must be between 1 and 15.");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Invalid input! Please enter two numbers separated by a space.");
                }
            } else {
                System.err.println("Invalid input! Please enter two numbers separated by a space.");
            }
        }

        return coordinates;
    }

    /**
     * ask the user if they want to place the word horizontally or vertically
     * @return
     */
    public char getWordDirection() {
        Scanner input = new Scanner(System.in);
        char direction;

        do {
            System.out.print("Enter the direction for the word (H for horizontal, V for vertical): ");
            direction = input.next().toUpperCase().charAt(0);
        } while (direction != 'H' && direction != 'V');

        return direction;
    }

    /** Method allowing players to only use the letters they have*/
    public boolean validateWord(String word, Player player, GameBoard board, int[] startCoords, char direction) {
        char[] playerLetters = player.getLetters();
        int[] letterCounts = new int[26];
        for (char inBag : playerLetters) {
            if (Character.isLetter(inBag)) {
                letterCounts[inBag - 'A']++;
            }
        }

        /** allow use of letters on the board previously*/
        int row = startCoords[0];
        int col = startCoords[1];

        for (int i = 0; i < word.length(); i++) {
            int currentRow = row + (direction == 'V' ? i : 0);
            int currentCol = col + (direction == 'H' ? i : 0);
            if (currentRow < 15 && currentCol < 15 && Character.isLetter(board.board[currentRow][currentCol])) {
                char boardLetter = board.board[currentRow][currentCol];
                letterCounts[boardLetter - 'A']++;
            }
        }

        for (char inHand : word.toCharArray()) {
            if (letterCounts[inHand - 'A'] > 0) {
                letterCounts[inHand - 'A']--;
            } else {
                return false;
            }
        }
        return true;
    }

}
