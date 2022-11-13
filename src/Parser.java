import java.util.Scanner;

/**
 * This parser reads user input from the keyboard and translates it to a
 * command in the ScrabbleScrabble game.
 *
 * An example of a command to place a word would be "CAT H8", where CAT
 * is the word to be placed on the board, and H8 is the position of the
 * first letter.  The letters in the word to be placed on the board must
 * be found in the player's tray, or must be found on the board in the
 * needed positions to spell a legal word
 *
 * @author James Grieder
 * @version 1.0
 */
public class Parser {

    private Scanner scanner;


    /**
     * Initializes the scrabblescrabble parser.  The parser takes keyboard
     * input from the user.
     * Developed by: James Grieder
     */
    public Parser() {
        this.scanner = new Scanner(System.in);
    }


    /**
     * Accepts a line of text input from the user, and creates a
     * Word object by separating the input into two Strings (the
     * word, and the starting position).
     * Developed by: James Grieder
     *
     * @return a Word determined by the user's input.
     */
    public Move getInput2() {
        String inputText;
        String command = null;
        String location = null;

        System.out.println("Please enter a command:    [WORD LOCATION]    [PASS]    [SWAP AB...YZ]    [QUIT]    ");
        System.out.print(">>> "); // Prompt user for text input

        inputText = scanner.nextLine();

        Scanner tokenizer = new Scanner(inputText);
        if(tokenizer.hasNext()) {
            command = tokenizer.next();      // Get the first word of the input (the command)
            command = command.toUpperCase();
            if(tokenizer.hasNext()) {
                location = tokenizer.next();      // Get the second word of the input (coordinates for the board)
                location = location.toUpperCase();
            }
        }

        Move move = new Move(command, location);

        return move;
    }

    public Move getInput(String input) {
        //System.out.println(input);

        String inputText[] = input.split(" ");

        String command = inputText[0]; // Get the first word of the input (the command)
        //System.out.println(command);
        String location = inputText[1]; // Get the second word of the input (coordinates for the board)

        command = command.toUpperCase();
        location = location.toUpperCase();

        Move move = new Move(command, location);

        return move;
    }
}
