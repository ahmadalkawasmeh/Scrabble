import java.util.*;

/**
 * LetterBag class represents the bag of letters that a player draws from to fill their tray.
 *
 * @author Ibtasam Rasool
 * @version 1.0
 */
public class LetterBag {

    private HashMap<String, Integer> letterQuantities;


    /**
     * Initializes the LetterBag by mapping each letter to its default starting quantity.
     */
    public LetterBag(){
        letterQuantities = new HashMap<>();

        for(Letters letter: Letters.values()){
            letterQuantities.put(letter.toString(), letter.getQuantity());
        }
    }


    /**
     * Returns a String representation of a random letter being drawn from the bag.
     * The quantity of the letter is decremented by 1.
     *
     * @return a random letter from the LetterBag.
     */
    public String drawRandomLetter() {
        String letter;
        Set<String> keySet = letterQuantities.keySet();
        List<String> keyList = new ArrayList<>(keySet);

        int randIndex = new Random().nextInt(keyList.size());
        letter = keyList.get(randIndex);

        decreaseLetterQuantity(letter);

        return letter;
    }


    /**
     * Removes a specified letter from LetterBag once its quantity has reached 0.
     * Prevents the letter from being drawn from the LetterBag.
     *
     * @param letter The letter to remove from the LetterBag.
     */
    private void removeLetter(String letter){

        letterQuantities.remove(letter);

    }


    /**
     * @param letter
     */
    private void decreaseLetterQuantity(String letter)
    {
        if(letterQuantities.get(letter) == 1)
        {
            removeLetter(letter); // prevent letter from being drawn in the future
        } else {
            letterQuantities.put(letter, letterQuantities.get(letter) - 1); // decrement letter quantity
        }
    }


    /**
     * Returns the quantity of a letter.
     *
     * @param letter The letter to get the quantity of.
     * @return The quantity of letter in letter bag.
     */
    private int letterQuantity(String letter){
        return letterQuantities.get(letter);
    }


    /**
     * Returns the total amount of letters remaining in this LetterBag.
     *
     * @return the amount of letters remaining.
     */
    public int lettersLeft()
    {
        return letterQuantities.size();
    }
}
