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

        /** Add colour to the multipliers to make board easier to tell between these and placed words*/
        final String Reset = "\033[0m";
        final String TW = "\033[31m";
        final String DW = "\033[34m";
        final String TL = "\033[32m";
        final String DL = "\033[33m";

        System.out.println();
        System.out.print("    ");
        for (int j = 0; j < columns; j++) {
            System.out.printf(" %2d ", j + 1);
        }
        System.out.println();
        System.out.print("   +");
        for (int j = 0; j < columns; j++) {
            System.out.print("----");
        }
        System.out.println("+");
        for (int i = 0; i < rows; i++) {
            System.out.printf("%2d |", i + 1);
            for (int j = 0; j < columns; j++) {
                if (Character.isLetter(board[i][j])) {
                    System.out.printf(" %2s ", board[i][j]);
                } else if (multiplier[i][j] != null) {
                    String color = Reset;
                    switch (multiplier[i][j]) {
                        case "TW":
                            color = TW;
                            break;
                        case "DW":
                            color = DW;
                            break;
                        case "TL":
                            color = TL;
                            break;
                        case "DL":
                            color = DL;
                            break;
                    }
                    System.out.printf(" %s%2s%s ", color, multiplier[i][j], Reset);
                } else {
                    System.out.printf(" %2s ", ".");
                }
            }
            System.out.println("|");
        }
        System.out.print("   +");
        for (int j = 0; j < columns; j++) {
            System.out.print("----");
        }
        System.out.println("+");
    }

    /**
     * Method to actually verify and place the word onto the board
     */
    public boolean placeWord(String word, int[] startCoordinates, char direction) {
        int row = startCoordinates[0];
        int column = startCoordinates[1];
        boolean connectsToExisting = false;

        /** Check the word can fit on the board*/
        if (direction == 'H' && (column + word.length() > 15)) {
            System.err.println("Word does not fit horizontally.");
            return false;
        } else if (direction == 'V' && (row + word.length() > 15)) {
            System.err.println("Word does not fit vertically.");
            return false;
        }

        /** Dont let invalid letters place*/
        for (int i = 0; i < word.length(); i++) {
            int rowNum = row + (direction == 'V' ? i : 0);
            int colNum = column + (direction == 'H' ? i : 0);

            char existingLetter = board[rowNum][colNum];
            if (Character.isLetter(existingLetter)) {
                if (existingLetter != word.charAt(i)) {
                    System.err.println("Letter at (" + (rowNum + 1) + ", " + (colNum + 1) + ") do NOT match.");
                    return false;
                } else {
                    connectsToExisting = true;
                }
            }
        }

        /** Make sure words connect and first word connects to the centre*/
        boolean isFirstWord = true;
        for (char[] rows : board) {
            for (char tile : rows) {
                if (Character.isLetter(tile)) {
                    isFirstWord = false;
                    break;
                }
            }
            if (!isFirstWord) break;
        }

        if (isFirstWord) {
            boolean onCentre = false;

            for (int i = 0; i < word.length(); i++) {
                int rowNum = row + (direction == 'V' ? i : 0);
                int colNum = column + (direction == 'H' ? i : 0);

                if (rowNum == 7 && colNum == 7) {
                    onCentre = true;
                    break;
                }
            }

            if (!onCentre) {
                System.err.println("The first word must connect to the centre square (8,8).");
                return false;
            }
        } else if (!connectsToExisting) {
            System.err.println("The word must connect to an existing word.");
            return false;
        }

        /** display words on the board*/
        for (int i = 0; i < word.length(); i++) {
            int rowNum = row + (direction == 'V' ? i : 0);
            int colNum = column + (direction == 'H' ? i : 0);

            board[rowNum][colNum] = word.charAt(i);
        }

        return true;
    }

}
