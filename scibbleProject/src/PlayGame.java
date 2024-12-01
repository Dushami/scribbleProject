/**
 * Final semester 1 project called scribble, essentially scrabble
 *
 * PlayGame class, where main method is, it will play the game calling
 * all logic from other classes
 *
 * @author Archie Hamilton
 * @version 1.0
 */

import java.util.Scanner;

public class PlayGame {
    /**
     *Method to display menu and track their selection
     *
     * @return returns the user selection 1-6
     */
    public int menuChoice(){
        System.out.println("--------------------");
        System.out.println("Welcome to Scribble!");
        System.out.println("--------------------");
        System.out.println(
                        "1. Play New Game\n" +
                        "2. Load Saved Game\n" +
                        "3. Save a Game\n" +
                        "4. Score History\n" +
                        "5. How to Play Scribble\n" +
                        "6. Exit"
        );
        System.out.println("--------------------");
        System.out.println("Please enter your choice (1-6): ");
        Scanner choice = new Scanner(System.in);
        return choice.nextInt();
    }

    public static void main(String[] args) {
        PlayGame game = new PlayGame();
        do {
            switch (game.menuChoice()) {
                case 1:
                    boolean back = false;
                    NewGame game1 = new NewGame();
                    GameBoard gameBoard = new GameBoard();
                    PlayerMove playerMove = new PlayerMove();
                    while (!back){
                        switch (game1.getOpponentChoice()) {
                            case 1:
                                game1.displayGameSettings();
                                break;
                            case 2:
                                /**
                                 * When Player opts to play against others, get number of players and their names,
                                 * display a fresh board and prompt to insert a word
                                 */
                                int numPlayers = game1.getNumberPlayers();
                                game1.PlayerNames(numPlayers);

                                Bag bag = new Bag();
                                Player[] players = new Player[numPlayers];
                                for (int i = 0; i < numPlayers; i++) {
                                    String playerName = (i == 0) ? game1.playerName1 : (i == 1) ? game1.playerName2 : (i == 2) ? game1.playerName3 : game1.playerName4;
                                    players[i] = new Player(playerName, bag);
                                }

                                gameBoard.placeMultipliers(gameBoard.multiplier);
                                boolean gameOngoing = true;
                                while (gameOngoing) {
                                    for (Player player : players) {
                                        boolean wordPlaced = false;
                                        while (!wordPlaced) {
                                            gameBoard.displayBoard(gameBoard.board, gameBoard.multiplier);
                                            System.out.println("----------------------------------------------------------------");
                                            System.out.println("Type '/save' to save your game or '/quit' to exit without saving");
                                            System.out.println();
                                            String word = playerMove.getPlayerWord(player);

                                            if (word.equals("*")) {
                                                System.out.println(player.getPlayerName() + " has skipped turn.");
                                                break;
                                            }

                                            System.out.println();
                                            int[] coordinates = playerMove.getWordCoordinates();
                                            System.out.println();
                                            char direction = playerMove.getWordDirection();

                                            if (playerMove.validateWord(word, player, gameBoard, coordinates, direction)) {
                                                boolean success = gameBoard.placeWord(word, coordinates, direction);
                                                if (success) {
                                                    int wordScore = ScoreSystem.wordScore(word);
                                                    player.updateScore(wordScore);
                                                    player.updateHand(word, bag);
                                                    System.out.println("+--------------------------------------------------------+");
                                                    System.out.println(player.getPlayerName() + " successfully placed the word: " + word + ": +" + wordScore + " Points");
                                                    System.out.println("+--------------------------------------------------------+");
                                                    wordPlaced = true;
                                                } else {
                                                    System.out.println("+----------------------------------+");
                                                    System.out.println("Failed to place the word. Try again.");
                                                    System.out.println("+----------------------------------+");
                                                }
                                            } else {
                                                System.err.println("You don't have the letters for that word. Try again or enter '*' to skip.");
                                            }

                                            if (word.equalsIgnoreCase("/save")) {
                                                SaveLoad saveLoad = new SaveLoad();
                                                String gameState = saveLoad.getGame(gameBoard, players);
                                                saveLoad.saveGame(gameState);
                                            }
                                        }
                                    }
                                }
                                break;
                            case 3:
                                back = true;
                                break;
                        }
                    }
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    System.out.println("Thank you for playing Scribble, Now Exiting program");
                    System.exit(0);
                    break;
                default:
                    System.err.println("Invalid choice. Please try again.");
                    break;
            }
        }   while (game.menuChoice() != 6);
    }
}