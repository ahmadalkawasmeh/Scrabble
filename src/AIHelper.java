import java.util.ArrayList;

public class AIHelper {

    private static Board board;
    private static String[][] squares;




    public AIHelper(Board board) {
        this.board = board;
        squares = board.getUsedSquares();
    }

    public static void updateSquares() {
        squares = board.getUsedSquares();
    }


    /**
     * Searches for a possible letter on the board that an AI player can build a word off of.
     * Returns a String coordinate that is equivalent to what a user would input by text. i.e. 1A
     * is used to place a word horizontally starting at coordinate 1A.
     *
     * @return the String coordinate of the word.
     */
    public static String getPossibleWordPosition(int i, int j) {
        updateSquares();
        boolean positionFound = false;

        for (int x = 0; x < Board.SIZE; x++) {
            for (int y = 0; y < Board.SIZE; y++) {
                if (!positionFound) { // if we haven't found a position yet

                    // find letter
                    if (!squares[x][y].equals(" ")) {

                        // check for 3 empty spaces to the right of the letter
                        if (x + 3 < Board.SIZE) {
                            if (squares[x + 1][y].equals(" ") && squares[x + 2][y].equals(" ") && squares[x + 3][y].equals(" ")) {
                                if (x == 0 || (x > 0 && squares[x - 1][y].equals(" "))) {
                                    // return coordinate of letter
                                    return getCoordinateString(x, y, true);
                                }
                            }
                        }
                        // if less than 3 empty spaces, then check for 3 empty spaces down from the letter
                        if (y + 3 < Board.SIZE) {
                            if (squares[x][y + 1].equals(" ") && squares[x][y + 2].equals(" ") && squares[x][y + 3].equals(" ")) {
                                if (y == 0 || (y > 0 && squares[x][y - 1].equals(" "))) {

                                    // return coordinate of letter
                                    return getCoordinateString(y, x, false);
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }


    private static String getCoordinateString(int i, int j, boolean horizontalFirst) {
        String s = "";
        i++;
        j++;

        if (horizontalFirst) {
            s += i;
            s += String.valueOf((char) (j + 64));
        }
        else {
            s += String.valueOf((char) (j + 64));
            s += i;
        }
        System.out.println("S = " + s);
        return s;

    }


    public static String getLetterFromPosition(String position) {
        updateSquares();
        String positionAlpha = "";
        String positionNumber = "";

        for (int i = 0; i < position.length(); i++) {
            if(Character.isDigit(position.charAt(i))) {
                positionNumber += position.charAt(i);
            } else {
                positionAlpha += position.charAt(i);
            }
        }

        System.out.println("positionNumber = " + positionNumber);
        System.out.println("positionAlpha = " + positionAlpha);

        int y = Integer.parseInt(positionNumber) - 1;
        System.out.println("here is y --> " + y);

        int x = Letters.getOrdinal("" + positionAlpha);

        System.out.println("here is x --> " + x);

        System.out.println(squares[x][y]);


        return squares[x][y];
    }


    public String getNextAIMove(Tray tray) {

        // Pass if a word has not been placed on the board yet
        if (board.centreSquareIsClear()) {
            return "SWAP " + tray.AIgetLettersToSwap();
        }

        boolean wordPlaced = false;

        int x = 0;
        int y = 0;
        while (!wordPlaced && x < Board.SIZE && y < Board.SIZE) {

            // Find a position with space to add 2+ tiles right or down
            String wordPosition = getPossibleWordPosition(x, y);

            // Get that letter
            String letter = board.getLetterFromPosition(wordPosition);

            // Generate words based on player tray + letter
            ArrayList<String> possibleWords = Game.dictionary.generateWords(tray, letter);

            // Select a word to place
            for (String possibleWord : possibleWords) {
                return possibleWord.toUpperCase() + " " + wordPosition;
            }
            x += 1; // If failed, try placing somewhere else
            y += 1;
        }

        // If placing a word was unsuccessful after 3 attempts, swap some letters instead
        if (tray.remainingNumberOfLettersInTray() > 0) {
            return "SWAP " + tray.AIgetLettersToSwap();
        }

        return "PASS"; // If player has no tiles to swap, pass
    }



}
