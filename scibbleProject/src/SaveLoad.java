import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Final semester 1 project called scribble, essentially scrabble
 *
 * SaveLoad class, this will simply handle when the user wants to save the game, or
 * when the load game option is selected it will be able to load a previously saved game
 *
 * @author Archie Hamilton
 * @version 1.0
 */

public class SaveLoad {

    /**
     * Gets the current stage of the game which will be saved
     *
     * @param gameBoard The current game board.
     * @param players Array of players in the game.
     * @return A formatted string representing the game state.
     */
    public String getGame(GameBoard gameBoard, Player[] players) {
        StringBuilder gameState = new StringBuilder();

        gameState.append("Game Board:\n");
        for (char[] row : gameBoard.board) {
            for (char tile : row) {
                gameState.append(tile == '\0' ? "." : tile).append(" ");
            }
            gameState.append("\n");
        }

        gameState.append("\nPlayer Details:\n");
        for (Player player : players) {
            gameState.append(player.getPlayerName()).append(" - Score: ").append(player.getPlayerScore()).append(" - Tiles: ").append(String.valueOf(player.getLetters())).append("\n");
        }

        return gameState.toString();
    }

    /**
     * Method to ask user to input a ane which the game will be saved to
     *
     * @param gameState current state which we want to save
     */
    public void saveGame(String gameState) {
        Scanner save = new Scanner(System.in);
        System.out.println("Enter a file name to save the game (e.g., 'mySave'): ");
        String fileName = save.nextLine();
        if (!fileName.endsWith(".txt")) {
            fileName += ".txt";
        }

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(gameState);
            System.out.println("Game successfully saved to " + fileName);
        } catch (IOException e) {
            System.err.println("Error saving game: " + e.getMessage());
        }
    }
}