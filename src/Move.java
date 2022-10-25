/**
 * Move represents an action taken by the Players of the Game.
 * A Move is created from user input via the keyboard, and
 * broken down into parts to be executed in the Game.
 *
 * @author James Grieder
 * @version 1.0
 */
public class Move {

    private String firstWord;
    private String secondWord;


    /**
     * Constructs a Move object based on input from the user.
     * Developed by: James Grieder
     *
     * @param first The first word of the command.
     * @param second The second word of the command.
     */
    public Move(String first, String second) {
        this.firstWord = first;
        this.secondWord = second;
    }


    /**
     * Returns the first part of the Move.  The first word will
     * either be a command, such as QUIT or SWAP, or it will be
     * a String representation of a word that will be placed on
     * the board (if legal).
     * Developed by: James Grieder
     *
     * @return The first word of the Move.
     */
    public String getFirstCommandWord() {
        return firstWord;
    }


    /**
     * Returns the second part of the Move.  The second part will
     * either be null (for single word commands), or it will be a
     * qualifier for the command (such as letters to swap, or the
     * coordinates to place a word).
     * Developed by: James Grieder
     *
     * @return The second word of the Move.
     */
    public String getSecondCommandWord() {
        return secondWord;
    }
}
