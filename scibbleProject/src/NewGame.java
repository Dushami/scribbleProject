/**
 * Final semester 1 project called scribble, essentially scrabble
 *
 * NewGame class, this will call upon everything needed for a new game,
 * a fresh board, player types and names, dictionary choice and some more general
 * rule options
 *
 * @author Archie Hamilton
 * @version 1.0
 */

import java.util.Scanner;

public class NewGame {
    /**
     * Fields to be used throughout this class
     */
    private int opponentType;
    private int numberPlayers;
    private String playerName1;
    private String playerName2;
    private String playerName3;
    private String playerName4;

    /**
     * Default constructor, initializes all names to "Player" followed by their number
     */
    public NewGame() {
        playerName1 = "Player 1";
        playerName2 = "Player 2";
        playerName3 = "Player 3";
        playerName4 = "Player 4";
    }

    /**
     * Secondary constructor, initializes player names to the input from PlayGame class
     */
    public NewGame(String namePLayer1, String namePLayer2, String namePLayer3, String namePLayer4) {
        playerName1 = namePLayer1;
        playerName2 = namePLayer2;
        playerName3 = namePLayer3;
        playerName4 = namePLayer4;
    }

    /**
     * Method to have user choose how many players or to play against computer
     */

    public int getOpponentChoice(){
        Scanner oppType = new Scanner(System.in);
        System.out.println("-----------------------------");
        System.out.println("         Set Up Game         ");
        System.out.println("------------------------------");
        System.out.println(
                        "Select your Opponents: \n" +
                        "1. Computer \n" +
                        "2. Other Players \n" +
                        "3. BACK"
        );
        opponentType = oppType.nextInt();
        return opponentType;
    }

    /**
     * Method to ask users how many players and set game to that number
     *
     * @return number of players
     */
    public int getNumberPlayers(){
        Scanner oppNumber = new Scanner(System.in);
        do {
            System.out.println("----------------------------");
            System.out.println("  How many players ? (2-4)  ");
            numberPlayers = oppNumber.nextInt();
            if (numberPlayers < 2 || numberPlayers > 4) {
                System.err.println("Invalid number of players");
                System.out.println("Please enter a number between 2 and 4");
            }
        } while (numberPlayers < 2 || numberPlayers > 4);
        return numberPlayers;
    }

    public void PlayerNames(int numberPlayers) {
        Scanner playerNames = new Scanner(System.in);
        switch (numberPlayers) {
            case 2:
                System.out.println("Please enter the name of Player 1: ");
                playerName1 = playerNames.nextLine();
                System.out.println("Please enter the name of Player 2: ");
                playerName2 = playerNames.nextLine();
                break;
            case 3:
                System.out.println("Please enter the name of Player 1: ");
                playerName1 = playerNames.nextLine();
                System.out.println("Please enter the name of Player 2: ");
                playerName2 = playerNames.nextLine();
                System.out.println("Please enter the name of Player 3: ");
                playerName3 = playerNames.nextLine();
                break;
            case 4:
                System.out.println("Please enter the name of Player 1: ");
                playerName1 = playerNames.nextLine();
                System.out.println("Please enter the name of Player 2: ");
                playerName2 = playerNames.nextLine();
                System.out.println("Please enter the name of Player 3: ");
                playerName3 = playerNames.nextLine();
                System.out.println("Please enter the name of Player 4: ");
                playerName4 = playerNames.nextLine();
        }
    }


    /**DELETE ME I HAVE NO FUNCTION OTHER THAN TESTING**/
    public void displayGameSettings(){
        System.out.println("----------------------------");
        System.out.println("         Settings           ");
        System.out.println("----------------------------");
        System.out.println(opponentType);
        System.out.println("Players: " + numberPlayers);
        System.out.println(playerName1);
        System.out.println(playerName2);
        System.out.println(playerName3);
        System.out.println(playerName4);
    }
}
