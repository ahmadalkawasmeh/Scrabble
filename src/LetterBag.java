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
    public String drawRandomLetter() {

        String letter;
        Set<String> keySet = letterQuantities.keySet();
        List<String> keyList = new ArrayList<>(keySet);

        if (keyList.size() > 0) {
        int randIndex = new Random().nextInt(keyList.size());
        letter = keyList.get(randIndex);

        decreaseLetterQuantity(letter);

        return letter; }
        else {
            return "";
        }
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
        if (letterQuantities.get(letter) != null) {
            return letterQuantities.get(letter);
        }
        return 0;
    }


    /**
     * Returns the amount of possible letters remaining in this LetterBag.
     * NOTE: This returns the number of unique letters, not the total number of letters.
     * i.e. If the LetterBag contains A, A, B, this method returns 2.
     *
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


    /**
     * Gets a copy of the LetterBag's current contents.
     * Used to save the LetterBag for serialization.
     *
     * @return the contents of this LetterBag.
     */
    public HashMap<String, Integer> copyContents() {
        return this.letterQuantities;
    }


    /**
     * Updates the contents of this LetterBag based on letterBagContents.
     * Used to load the LetterBag (deserialization).
     *
     * @param letterBagContents the contents to load the LetterBag with.
     */
    public void loadContents(HashMap<String, Integer> letterBagContents) {
        this.letterQuantities.clear();
        this.letterQuantities.putAll(letterBagContents);
    }


    /**
     * Fills the LetterBag to its default contents.
     * Used for testing purposes.
     */
    public void fillBagForTesting() {
        for(Letters letter: Letters.values()){
            letterQuantities.put(letter.toString(), letter.getQuantity());
        }
    }


    /**
     * Gets the total number of remaining letters left from the LetterBag.
     * Under normal gameplay conditions this will return a value between 0 and 100.
     *
     * @return the integer value of letters left in this bag.
     */
    public int getTotalLetters() {
        int total = 0;
        for (Integer i : letterQuantities.values()) {
            total += i;
        }
        return total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LetterBag letterBag = (LetterBag) o;

        return Objects.equals(letterQuantities, letterBag.letterQuantities);
    }

    @Override
    public String toString() {
        return letterQuantities.toString();
    }
}
