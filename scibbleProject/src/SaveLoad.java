import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
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



    /**
     * method which will add the result of games to a text file so they can be viewed in the game histoy option of the menu
     *
     * @param players to show who played this game
     * @param winnerName Shows which of the players won said game
     */
    public void addToHistory(Player[] players, String winnerName) {
        String fileName = "game_results.txt";
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write("Game Results:\n");
            writer.write("Winner: " + winnerName + "\n");
            writer.write("Players and Scores:\n");
            for (Player player : players) {
                writer.write(player.getPlayerName() + ": " + player.getPlayerScore() + " points\n");
            }
            writer.write("-----------------------------\n");
            System.out.println("Game results logged to " + fileName);
        } catch (IOException e) {
            System.err.println("Error saving game results: " + e.getMessage());
        }
    }

    /**
     * loads the file and displays the game history
     */
    public void displayHistory() {
        String fileName = "game_results.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            String winnerColour = "\033[33m";
            String resetColour = "\033[0m";

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Winner: ")) {
                    System.out.println(winnerColour + line + resetColour);
                } else {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading game history: " + e.getMessage());
        }
    }
}