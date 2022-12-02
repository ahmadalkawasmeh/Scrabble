import java.io.Serializable;
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
     * Developed by: Ibtasam Rasool
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
     * Developed by: Ibtasam Rasool
     *
     * @return a random letter from the LetterBag.
     */
    public String drawRandomLetter() { // TODO need to fix IllegalArgumentException

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
     * Developed by: Ibtasam Rasool
     *
     * @param letter The letter to remove from the LetterBag.
     */
    private void removeLetter(String letter){

        letterQuantities.remove(letter);

    }


    /**
     * Decrements the quantity of a letter in LetterBag by 1.
     * Developed by: Ibtasam Rasool
     *
     * @param letter The letter to decrement.
     */
    public void decreaseLetterQuantity(String letter)
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
     * Developed by: Ibtasam Rasool
     *
     * @param letter The letter to get the quantity of.
     * @return The quantity of letter in letter bag.
     */
    public int letterQuantity(String letter){
            return letterQuantities.get(letter); // TODO need to fix the nullPointerException here.
    }


    /**
     * Returns the total amount of letters remaining in this LetterBag.
     * Made Public for testing in Deliverable 2
     * Developed by: Ibtasam Rasool
     *
     * @return the amount of letters remaining.
     */
    public int lettersLeft()
    {
        return letterQuantities.size();
    }

    /**
     * increments the quantity of a letter in letterbag by 1 if letter does not exist adds it in letterbag with quantity 1
     * Developed by James and Ibtasam
     * @param letter to increment
     */
    public void increaseLetterQuantity(String letter) {
        if(letterQuantities.containsKey(letter))
        {
            letterQuantities.put(letter, letterQuantities.get(letter) + 1); // increases letters quantity

        } else {
            letterQuantities.put(letter, 1);
        }
    }

    public HashMap<String, Integer> getContents() {
        return this.letterQuantities;
    }

    public void updateContents(HashMap<String, Integer> letterBagContents) {
        this.letterQuantities.clear();
        this.letterQuantities.putAll(letterBagContents);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LetterBag letterBag = (LetterBag) o;

        return Objects.equals(letterQuantities, letterBag.letterQuantities);
    }

    @Override
    public int hashCode() {
        return letterQuantities != null ? letterQuantities.hashCode() : 0;
    }
}
