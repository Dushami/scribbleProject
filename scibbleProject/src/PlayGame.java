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
                        "3. Score History\n" +
                        "4. How to Play Scribble\n" +
                        "5. Exit"
        );
        System.out.println("--------------------");
        System.out.println("Please enter your choice (1-6): ");
        Scanner choice = new Scanner(System.in);
        return choice.nextInt();
    }

    public static void main(String[] args) {
        PlayGame game = new PlayGame();
        int choice;
        do {
            choice = game.menuChoice();
            switch (choice) {
                case 1:
                    boolean back = false;
                    NewGame game1 = new NewGame();
                    GameBoard gameBoard = new GameBoard();
                    PlayerMove playerMove = new PlayerMove();
                    while (!back){
                        switch (game1.getOpponentChoice()) {
                            case 1:
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

                                            if (word.equalsIgnoreCase("/save")) {
                                                SaveLoad saveLoad = new SaveLoad();
                                                String gameState = saveLoad.getGame(gameBoard, players);
                                                saveLoad.saveGame(gameState);
                                                System.out.println("Game saved successfully!");
                                                continue;
                                            } else if (word.equalsIgnoreCase("/quit")) {
                                                System.out.println("Exiting to the main menu...");
                                                gameOngoing = false;
                                                back = true;
                                                break;
                                            } else if (word.equals("*")) {
                                                System.out.println(player.getPlayerName() + " has skipped their turn.");
                                                break;
                                            }

                                            System.out.println();
                                            int[] coordinates = playerMove.getWordCoordinates();
                                            System.out.println();
                                            char direction = playerMove.getWordDirection();

                                            if (playerMove.validateWord(word, player, gameBoard, coordinates, direction)) {
                                                boolean success = gameBoard.placeWord(word, coordinates, direction);
                                                if (success) {
                                                    int wordScore = ScoreSystem.wordScore(word, coordinates, direction, gameBoard.multiplier, gameBoard.board);
                                                    player.updateScore(wordScore);
                                                    player.updateHand(word, bag);
                                                    System.out.println("+--------------------------------------------------------+");
                                                    System.out.println(player.getPlayerName() + " successfully placed the word: " + word + ": +" + wordScore + " Points");
                                                    System.out.println();
                                                    ScoreSystem.displayAllScores(players);
                                                    System.out.println("+--------------------------------------------------------+");

                                                    if (player.getPlayerScore() >= 250) {
                                                        System.out.println("+--------------------------------------------------------+");
                                                        System.out.println("Congratulations " + player.getPlayerName() + "! You have won the game with " + player.getPlayerScore() + " points!");
                                                        System.out.println("+--------------------------------------------------------+");

                                                        SaveLoad saveLoad = new SaveLoad();
                                                        saveLoad.addToHistory(players, player.getPlayerName());

                                                        gameOngoing = false;
                                                        back = true;
                                                        break;
                                                    }

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
                                        if (!gameOngoing) {
                                            break;
                                        }
                                    }
                                }
                                break;
                            case 2:
                                back = true;
                                break;
                        }
                    }
                    break;
                case 2:
                    System.err.println("I cannot do it. I have been trying to load any game files for days, and i know what your thinking... and yes i agree, i should've asked for help \n" +
                            "however i had faith in myself to figure it out but alas i must fall on my sword and concede. I cannot do it, i am losing my mind but hey you can save files");
                    break;
                case 3:
                    SaveLoad historyLoader = new SaveLoad();
                    System.out.println("Game History:");
                    System.out.println("-----------------------------");
                    historyLoader.displayHistory();
                    System.out.println("-----------------------------");
                    break;
                case 4:
                    boolean goBack = false;
                    Scanner backToMenu = new Scanner(System.in);
                    while (!goBack) {
                        System.out.println("--------------------------------------------------------------------------------------------");
                        System.out.println("                                         Scribble                                           ");
                        System.out.println("--------------------------------------------------------------------------------------------");
                        System.out.println("                                  How To Play Scribble:                                     ");
                        System.out.println();
                        System.out.println("1. Select your opponent / specify quantity & names");
                        System.out.println("2. Using the letters in your hand type the word you would like to play");
                        System.out.println("3. Then specify the position you would like to place the first letter");
                        System.out.println("4. Then specify the direction you would like your word to be placed");
                        System.out.println("5. If you wish to skip your turn type: '*' instead of your desired word");
                        System.out.println("6. Type: '/save' or '/quit' if you want to save, or quit without saving");
                        System.out.println();
                        System.out.println("--------------------------------------------------------------------------------------------");
                        System.out.print("                                      Rules of Scribble                                       ");
                        System.out.println();
                        System.out.println("1. The first word MUST be placed whereby it connects to the centre square (8 8)");
                        System.out.println("2. Any word after must connect in some way to another word on the board");
                        System.out.println("3. A word must be placed either horizontally or vertically");
                        System.out.println("4. A player can opt to skip their turn by typing: '*'");
                        System.out.println("5. After a word is place, it will be scored and points allocated to that player");
                        System.out.println("6. A winner will be crowned once one of the players reaches 200 points");
                        System.out.println("7. The game will automatically stop after each player has taken 10 turn. Most points wins!");
                        System.out.println();
                        System.out.println("--------------------------------------------------------------------------------------------");
                        System.out.println("                                      Scoring System                                        ");
                        System.out.println();
                        System.out.println("Each player will start with 0 points");
                        System.out.println("When a word is played the points of each letter will be totalled and added to the score");
                        System.out.println("Each individual letter has its own amount of points based on difficulty to use");
                        System.out.println("--------------------------------------");
                        System.out.println("1 Point: A, E, I, O, U, L, N, S, T, R");
                        System.out.println("2 Point: D, G");
                        System.out.println("3 Point: B, C, M, P");
                        System.out.println("4 Point: F, H, V, W, Y");
                        System.out.println("5 Point: K");
                        System.out.println("8 Point: J, X");
                        System.out.println("10 Point: Q, Z");
                        System.out.println();
                        System.out.println("For Example, TRICKY = (1 + 1 + 1 + 3 + 5 + 4) = 15 Points");
                        System.out.println("--------------------------------------");
                        System.out.println();
                        System.out.println("Around the board there are multipliers: DL, DW, TL, TW");
                        System.out.println("DL - Double Letter, this will double the value of any letter placed on this tile");
                        System.out.println("DW - Double Word, this will double the value of the entire word running on this tile");
                        System.out.println("TL - Triple Letter, this will triple the value of any letter placed on this tile");
                        System.out.println("TW - Triple Word, this will triple the value of the entire word running on this tile");
                        System.out.println("--------------------------------------------------------------------------------------------");
                        System.out.println();
                        System.out.println("Type '/back' to return to menu");
                        String decision = backToMenu.nextLine();
                        while (!decision.equalsIgnoreCase("/back")){
                            System.err.println("Type /back to go back to menu");
                            decision = backToMenu.nextLine();
                        }
                        break;
                    }

                    break;
                case 5:
                    System.out.println("Thank you for playing Scribble, Now Exiting program");
                    System.exit(0);
                    break;
                default:
                    System.err.println("Invalid choice. Please enter 1-5 for a valid choice.");
                    break;
            }
        }   while (choice != 5);
    }
}