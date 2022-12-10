import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * AIHelper represents the logic for AI Players in scrabblescrabble.
 *
 * AIHelper provides methods that allows AI Players to search for a letter
 * on the game board, then search for a word in the Dictionary that uses
 * that letter and the letters remaining in their Tray.  AIHelper also
 * contains the logic for an AI Player to decide when to place a word, when to
 * swap letters, or when to pass their turn.
 */
public class AIHelper implements Serializable {

    private static Board board; // The board for this game
    private static String[][] squares; // the squares on the board


    /**
     * Constructor for class AIHelper.
     *
     * @param board The Board used in the currently running game.
     */
    public AIHelper(Board board) {
        this.board = board;
        squares = board.getUsedSquares();
    }

    /**
     * Updates the squares variable based on the current state of
     * letters on the Board.
     */
    public static void updateSquares() {
        squares = board.getUsedSquares();
    }


    /**
     * Searches for a possible letter on the board that an AI player can
     * build a word off of.  Returns a String coordinate that is equivalent
     * to what a user would input by text. i.e. 1A is used to place a word
     * horizontally starting at coordinate 1A.
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


    /**
     * Creates a coordinate string that would be equivalent to user input via the keyboard.
     *
     * For example, H8 or 8H represent the centre tile of the game board in horizontal and
     * vertical formats.
     *
     * @param i The row of the board
     * @param j The column of the board
     * @param horizontalFirst true if the word will be placed horizontally, false otherwise
     * @return The String representation of a coordinate on the game board
     */
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
        //System.out.println("S = " + s);
        return s;

    }


    /**
     * Gets the letter placed on the game board at the specified position.
     *
     * @param position The position on the board to check
     * @return A String representation of the letter placed at position
     */
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
        int y = Integer.parseInt(positionNumber) - 1;
        int x = Letters.getOrdinal("" + positionAlpha);

        return squares[x][y];
    }


    /**
     * The core decision-making logic for AI Players.
     * The AI Player will first try to place a word (if a word has already been placed
     * on the board), otherwise the Player will try to swap letters.  If the player
     * has no available letters to swap, they will pass their turn.
     *
     * Potential output Strings from this method include:
     *
     * SWAP ADJBC   // To swap the specified letters
     * WORD H8      // To place a word at a specific position
     * PASS         // To pass the turn to the next player
     *
     * @param tray The AI Player's Tray of letters
     * @return An input String that may be used to create a Move Object
     */
    public static String getNextAIMove(Tray tray) {
        // Place the first word on the board if the centre square is clear (always vertical)
        if (board.centreSquareIsClear()) {

            return tray.AIGenerateFirstWordOnBoard() + " " + Board.CENTRE_SQUARE;
        }

        // Otherwise place a word using a letter already on the board
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

        // If player has no tiles to swap, pass
        return "PASS";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AIHelper AIHelper = (AIHelper) o;

        if (board != AIHelper.board) return false;
        return Objects.equals(squares, AIHelper.squares);
    }

}
