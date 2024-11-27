/**
 * Final semester 1 project called scribble, essentially scrabble
 *
 * gameBoard class, this will display the board and handle new words being
 * placed onto the board.
 *
 * @author Archie Hamilton
 * @version 1.0
 */

public class GameBoard {
    /**
     * Fields to be used throughout the class
     */
    public char[][] board = new char[15][15];
    public String[][] multiplier = new String[15][15];

    /**
     * Place where my special tiles are in a method so i dont have to format
     * when making the board proper
     * Edit: come back to this, there has to be a better way
     */
    public void placeMultipliers(String[][] multiplier) {
        //Triple words
        multiplier[0][0] = "TW";
        multiplier[0][7] = "TW";
        multiplier[0][14] = "TW";
        multiplier[7][0] = "TW";
        multiplier[7][14] = "TW";
        multiplier[14][0] = "TW";
        multiplier[14][7] = "TW";
        multiplier[14][14] = "TW";

        //Double Words
        multiplier[1][1] = "DW";
        multiplier[1][13] = "DW";
        multiplier[2][2] = "DW";
        multiplier[2][12] = "DW";
        multiplier[3][3] = "DW";
        multiplier[3][11] = "DW";
        multiplier[4][4] = "DW";
        multiplier[4][10] = "DW";
        multiplier[5][5] = "DW";
        multiplier[5][9] = "DW";
        multiplier[6][6] = "DW";
        multiplier[6][8] = "DW";
        multiplier[8][6] = "DW";
        multiplier[8][8] = "DW";
        multiplier[9][5] = "DW";
        multiplier[9][9] = "DW";
        multiplier[10][4] = "DW";
        multiplier[10][10] = "DW";
        multiplier[11][3] = "DW";
        multiplier[11][11] = "DW";
        multiplier[12][2] = "DW";
        multiplier[12][12] = "DW";
        multiplier[13][1] = "DW";
        multiplier[13][13] = "DW";

        //Triple Letters
        multiplier[1][5] = "TL";
        multiplier[1][9] = "TL";
        multiplier[5][1] = "TL";
        multiplier[5][13] = "TL";
        multiplier[9][1] = "TL";
        multiplier[9][13] = "TL";
        multiplier[13][5] = "TL";
        multiplier[13][9] = "TL";

        //Double Letter
        multiplier[0][3] = "DL";
        multiplier[0][11] = "DL";
        multiplier[2][6] = "DL";
        multiplier[2][8] = "DL";
        multiplier[3][0] = "DL";
        multiplier[3][7] = "DL";
        multiplier[3][14] = "DL";
        multiplier[6][2] = "DL";
        multiplier[6][12] = "DL";
        multiplier[7][3] = "DL";
        multiplier[7][11] = "DL";
        multiplier[8][2] = "DL";
        multiplier[8][12] = "DL";
        multiplier[11][0] = "DL";
        multiplier[11][7] = "DL";
        multiplier[11][14] = "DL";
        multiplier[12][6] = "DL";
        multiplier[12][8] = "DL";
        multiplier[14][3] = "DL";
        multiplier[14][11] = "DL";
    }

    /**
     * Method to display the board with the multiplier tiles and - for nothing
     *
     * @peram(char[] board, String[][] multiplier)
     */
    public void displayBoard(char[][] board, String[][] multiplier) {
        int rows = board.length;
        int columns = board[0].length;

        //print numbers along the top of the board
        System.out.println();
        System.out.print("    ");
        for (int j = 0; j < columns; j++) {
            System.out.printf(" %2d ", j+1);
        }
        System.out.println();

        //print dashes to show top bounds
        System.out.print("   +");
        for (int j = 0; j < columns; j++) {
            System.out.print("----");
        }
        System.out.println("+");

        //numbers along the side, tiles and side bounds
        for (int i = 0; i < rows; i++) {
            System.out.printf("%2d |", i+1);
            for (int j = 0; j < columns; j++) {
                if(multiplier[i][j] != null) {
                    System.out.printf(" %2s ", multiplier[i][j]);
                } else if (board[i][j] != '-') {
                    System.out.printf(" %2s ", "." );
                } else {
                    System.out.print(".");
                }
            }
            System.out.println("|");
        }


        //bottom bounds
        System.out.print("   +");
        for (int j = 0; j < columns; j++) {
            System.out.print("----");
        }
        System.out.println("+");
    }
}
