import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * This parser reads user input from the keyboard and translates it to a command in
 * the ScrabbleScrabble game.
 *
 * Further explanation will go here (input syntax and what happens with end result).
 *
 * @author James Grieder
 * @version 1.0
 */
public class Parser {

    private Scanner scanner;

    public Parser() {
        this.scanner = new Scanner(System.in);
    }


    public Word getInput() {
        String inputText;
        String command = null;
        String location = null;

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

        Word word = new Word(command, location);    //Just for testing purposes

        return word;


    }

    public static void main(String[] args) {

        Parser parse = new Parser();
        parse.getInput();

    }


}
