import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a Player's letter tray.
 *
 * The tray contains letters that may be placed on the game board.  The tray has a
 * maximum capacity for the number of letters it holds.
 *
 * @author Ibtasam Rasool
 * @version 1.0
 */
public class Tray
{
    private static final int NUMLETTERS = 7; // The maximum number of letters a tray can hold

    private ArrayList<String> letters; // The letters in this tray
    private LetterBag letterBag; // The LetterBag to fill the tray from (shared by all Trays)


    /**
     * Initializes a Tray and fills it with letters.
     * Developed by: Ibtasam Rasool
     */
    public Tray()
    {
        letters = new ArrayList<>();
        letterBag = Game.getLetterBag();
        this.fill();
    }


    /**
     * Fills Tray with NUMLETTERS letters.
     * Developed by: Ibtasam Rasool
     */
    public void fill()
    {
        while(letters.size() < NUMLETTERS)
        {
            letters.add(letterBag.drawRandomLetter());
        }
    }


    /**
     * Checks if an individual letter is in this Tray.
     * Developed by: Ibtasam Rasool & James Grieder
     *
     * @param letter The letter to check for.
     * @return  true if letter is in this Tray, otherwise returns false.
     */
    public boolean checkInTray(String letter)
    {
        return letters.contains(letter);
    }


    /**
     * Gets the current frequency of letter in this tray.
     * Developed by: Ibtasam Rasool & James Grieder
     *
     * @param letter The letter to check the current quantity of.
     * @return The frequency of a letter in the tray
     */
    public int checkInTrayFrequency(String letter)
    {
        return Collections.frequency(letters, letter);
    }


    /**
     * Removes the specified letter from this tray.
     * Developed by: Ibtasam Rasool
     *
     * @param letter The letter to remove.
     */
    public void removeLetter(String letter) {
                this.letters.remove(letter);
    }

    /**
     * returns a letter back to the letter bag
     * @param letter to return to bag
     * developed by James & Ibtasam
     */
    public void returnLetterToBag(String letter){
        removeLetter(letter);
        letterBag.increaseLetterQuantity(letter);
    }


    /**
     * Returns a string representation of this Tray.
     * For example: "L M N O P Q R "
     * Developed by: Ibtasam Rasool
     *
     * @return String representation of Tray
     */
    public String toString()
    {
        StringBuffer stringBuffer = new StringBuffer();

        for(String letter : letters)
        {
            stringBuffer.append(letter);
            stringBuffer.append(" ");
        }
        return  stringBuffer.toString();
    }
}
