/**
 * Final semester 1 project called scribble, essentially scrabble
 *
 * PlayGame class, where main method is, it will play the game calling
 * all logic from other classes
 *
 * @author archie hamilton
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