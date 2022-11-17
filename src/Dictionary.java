import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Dictionary contains the list of legal words that can be played in the game.
 *
 * @author James Grieder
 * @version 1.0
 */
public class Dictionary
{
    private HashSet<String> legalWords; // The list of legal words that can be placed on the board
    private HashSet<String> AILegalWords; // A dictionary used to construct words for AI players



    /**
     * Initializes the Dictionary using an input file.
     * Reads each line of the input file, and adds that String to the
     * list of legal words.
     * Developed by: James Grieder
     */
    public Dictionary()
    {
        legalWords = new HashSet<>();
        AILegalWords = new HashSet<>();
        
        try {
        	Scanner scanner = new Scanner(new FileReader("legalWordsList.txt"));
			while (scanner.hasNextLine()) {

                String s = scanner.nextLine();

			    legalWords.add(s);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }


    /**
     * Looks up a word in the dictionary, to see if it is a legal word in the game.
     * Developed by: James Grieder
     *
     * @param  word  The word to look up in the dictionary.
     * @return true if the word is in legalWords, false otherwise.
     */
    public boolean lookupDictionary(String word)
    {
        return legalWords.contains(word);
    }


    /**
     * Generates a possible list of legal words found in the dictionary based on a player's tray.
     * @param tray The Player's Tray
     * @return The list of legal words found in the tray
     */
    public ArrayList<String> generateWords(Tray tray, String letter) {
        ArrayList<String> possibleWords = new ArrayList<>();
        for (String s : AILegalWords) {
            if (tray.checkWordInTray(s.substring(1)) && s.substring(0,0).equals(letter)) {
                possibleWords.add(s);
            }
        }
        return possibleWords;
    }

}
