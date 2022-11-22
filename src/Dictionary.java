import java.util.ArrayList;
import java.util.Arrays;
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

                if (s.length() <= Tray.SIZE) {
                    legalWords.add(s);
                }
                if (s.length() <= Tray.SIZE - 2 && s.length() > 1) {
                    AILegalWords.add(s);
                }
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
     * Generates a possible list of legal words found in the dictionary based on an AI player's tray.
     * @param tray The AI Player's tray representing the possible letters
     * @param letter The currently selected letter on the board to build a word off of
     * @return The list of legal words found in the tray
     */
    public ArrayList<String> generateWords(Tray tray, String letter) {
        ArrayList<String> possibleWords = new ArrayList<>();

        for (String s : AILegalWords) {

            String[] strSplit = s.split("");

            String original = s;
            ArrayList<String> currentWord = new ArrayList<>(Arrays.asList(strSplit));

            String firstLetter = currentWord.get(0);
            firstLetter = firstLetter.toUpperCase();
            currentWord.remove(0);

            if (firstLetter.equals(letter)) {
                boolean lettersPresent = true;

                for (int i = 0; i < currentWord.size(); i++) {
                    if (!tray.checkLetterInTray(currentWord.get(i).toUpperCase())) {
                        lettersPresent = false;
                    }
                }

                if (lettersPresent) {
                    System.out.println(s);
                    possibleWords.add(s);
                }
            }
        }
        return possibleWords;
    }

}
