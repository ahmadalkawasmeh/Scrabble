import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static java.lang.Math.round;

/**
 * Represents a Player's letter tray.
 *
 * The tray contains letters that may be placed on the game board.  The tray has a
 * maximum capacity for the number of letters it holds.
 *
 * @author Ibtasam Rasool
 * @version 1.0
 */
public class Tray implements Serializable
{
    public static final int SIZE = 7; // The maximum number of letters a tray can hold
    public static final int SWAP_CONSTANT = 4;

    private ArrayList<String> letters; // The letters in this tray

    transient private LetterBag letterBag; // The LetterBag to fill the tray from (shared by all Trays)


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

    public void setLetterBag(LetterBag letterBag){
        this.letterBag = letterBag;
    }

    /**
     * Initializes a Tray and fills it with the specified trayValues.
     * Used for testing purposes to force-set a tray to desired values.
     * @param trayValues The values to set the tray to
     */
    public Tray(String trayValues)
    {
        String tray[] = trayValues.split(" ", Tray.SIZE);
        letters = new ArrayList<>(List.of(tray));
    }

    public void fillBag(){
        letterBag.fillBagForTesting();
    }


    /**
     * Fills Tray with NUMLETTERS letters.
     * Developed by: Ibtasam Rasool
     */
    public void fill()
    {
        while(letters.size() < SIZE)
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
    public boolean checkLetterInTray(String letter)
    {
        return letters.contains(letter);
    }


    /**
     * Checks if an individual letter is in this Tray.
     * Developed by: Ibtasam Rasool & James Grieder
     *
     * @param letter The letter to check for.
     * @return  true if letter is in this Tray, otherwise returns false.
     */
    public boolean checkWordInTray(String letter)
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


    /**
     * Gets the list of letters in this tray.
     *
     * @return The list of letters in this tray.
     */
    public ArrayList<String> getLetters() {
        return letters;
    }


    /**
     * Gets the letterBag this Tray draws letters from
     * @return The LetterBag of letters.
     */
    public LetterBag getLetterBag() {
        return letterBag;
    }


    /**
     * Selects a series of letters from this Tray for an AI Player to swap.
     * The AI will swap letters that are greater than or equal to a specified
     * swap constant, to reduce the number of rare letters in the tray.  If there
     * are no letters greater than or equal to the swap constant in point value,
     * the method will return a String to swap all letters in the tray.
     *
     * @return
     */
    public String AIgetLettersToSwap() {
        String lettersToSwap = "";

        // Swap any letters that are greater than or equal to the SWAP_CONSTANT in point value
        for (String letter: letters) {
            if (Letters.valueOf(letter).getLetterScore() >= SWAP_CONSTANT) {
                lettersToSwap += letter;
            }
        }

        // If there are less than 2 letters selected for swapping above, then swap all letters in the tray
        if (lettersToSwap.length() < 2) {

            lettersToSwap = ""; // clear lettersToSwap and add all letters
            for (String letter: letters) {
                lettersToSwap += letter;
            }
        }

        return lettersToSwap;
    }

    /**
     * Generates a word from an AIPlayer's tray that can be played
     * when there are no letters currently on the board.
     *
     * @return A word to be played on the board.
     */
    public String AIGenerateFirstWordOnBoard() {
        ArrayList<String> possibleWords = Dictionary.generateFirstWord(this);

        for (int i = 0; i < possibleWords.size(); i++) {
            if (possibleWords.get(i).length() >= 3) {
                return possibleWords.get(i); // If possible, return a word of length 3 or more
            }
        }
        return possibleWords.get(0); // otherwise return the first possible word
    }


    /**
     * Gets the number of letters left in this Tray.
     * Developed by James
     *
     * @return The number of letters in the Tray.
     */
    public int remainingNumberOfLettersInTray() {
        return letters.size();
    }


    /**
     * Adds a letter to this player’s tray by swapping with the last letter
     *
     * Developed by Daniel
     *
     * @param letter to be added
     */
    public void addLetter(String letter){
        letters.add(letter);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tray tray = (Tray) o;

        return Objects.equals(letters, tray.letters);
    }



}
