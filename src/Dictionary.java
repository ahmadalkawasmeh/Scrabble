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

    /**
     * Initializes the Dictionary using an input file.
     * Reads the line of each input file, and adds that String to the
     * list of legal words.
     */
    public Dictionary()
    {
        legalWords = new HashSet<>();
        
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
     *
     * @param  word  The word to look up in the dictionary.
     * @return true if the word is in legalWords, false otherwise.
     */
    public boolean lookupDictionary(String word)
    {
        return legalWords.contains(word);
    }

    public static void main(String[] args) {

    }
}
