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
        System.out.println(player.getPlayerName() + ", your current tiles: " + String.valueOf(player.getLetters()));
        System.out.print("Please enter the word you want to play: ");
        return playerWord.nextLine().toUpperCase();
    }


    /**
     * Ask user to unput coordinate of first letter
     * @return
     */
    public int[] getPlacementCoordinates() {
        Scanner input = new Scanner(System.in);
        int[] coordinates = new int[2];
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter the coordinate of the first letter (e.g., 4 8): ");
            String[] inputCoords = input.nextLine().split(" ");
            if (inputCoords.length == 2) {
                try {
                    coordinates[0] = Integer.parseInt(inputCoords[0]) - 1;
                    coordinates[1] = Integer.parseInt(inputCoords[1]) - 1;

                    // Validate that coordinates are within bounds
                    if (coordinates[0] >= 0 && coordinates[0] < 15 && coordinates[1] >= 0 && coordinates[1] < 15) {
                        validInput = true;
                    } else {
                        System.out.println("Invalid input! Coordinates must be between 1 and 15.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter two numbers separated by a space.");
                }
            } else {
                System.out.println("Invalid input! Please enter two numbers separated by a space.");
            }
        }

        return coordinates;
    }

    public char getPlacementDirection() {
        Scanner input = new Scanner(System.in);
        char direction;

        do {
            System.out.print("Enter the direction for the word (H for horizontal, V for vertical): ");
            direction = input.next().toUpperCase().charAt(0);
        } while (direction != 'H' && direction != 'V');

        return direction;
    }
}
