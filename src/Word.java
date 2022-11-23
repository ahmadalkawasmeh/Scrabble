import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A Word represents a word to be placed on the game board.
 * Words are only created when they are legal (found in the dictionary), and possible
 * to construct from letters in a Player's tray and on the game board.  Each word has a
 * String value and a String position.
 *
 * @author Ibtasam Rasool
 * @version 1.0
 */
public class Word {

    private String word; // The string representation of this Word
    private String position; // The position of the first character of this Word

    private HashMap<String, String> letterPositions; // The set of all character positions of this Word


    /**
     * Constructor for objects of class Word.
     * Developed by: Ibtasam Rasool
     *
     * @param word The word represented by this object.
     * @param position The position of this Word.
     */
    public Word(String word, String position)
    {
        letterPositions = new HashMap<>();
        this.word = word;
        this.position =  position;
        if(hasValidBounds()){this.addLettersToMap();}
    }


    /**
     * Adds a letter to the hashmap representing the positions used by a word
     * NOTE: all values are represented with Alphabet then numeric value (i.e. A5)
     * even if the value was entered in as 5A this maintains consistency in map.
     * Developed by: Ibtasam Rasool
     */
    public void addLettersToMap()
    {
        Integer changingCoordinate;
        ArrayList<Integer> numPos = findWordPosition();
        String letters[] = word.split("");

        if(isHorizontal()) {
            for (int i = 0; i < length(); i++) {

                     changingCoordinate = numPos.get(1) + i;
                     letterPositions.put(Letters.values()[changingCoordinate].toString() + numPos.get(0), letters[i]);
            }
        } else {
            for (int i = 0; i < length(); i++)
            {
                changingCoordinate = numPos.get(1) + i;
                letterPositions.put(Letters.values()[numPos.get(0)].toString() + changingCoordinate, letters[i]);
            }
        }
    }


    /**
     * Returns the direction of this word as a boolean, with the horizontal
     * direction being true.
     * Developed by: Ibtasam Rasool
     *
     * @return true if this Word is horizontal, false otherwise (vertical).
     */
    public boolean isHorizontal()
    {
        return Character.isDigit(position.charAt(0));
    }


    /**
     * Finds the  position of this Word on the Board.
     * Developed by: Ibtasam Rasool
     *
     * @return A list containing x and y positions of this Word on the board
     */
    public ArrayList<Integer> findWordPosition(){
        int x = 0;
        int y = 0;
        ArrayList<Integer> numPos = new ArrayList<>();
        ArrayList<String>  coordinates = new ArrayList<>(List.of(position.split("")));

        if(isHorizontal()){
            y = Letters.valueOf(coordinates.get(coordinates.size() - 1)).ordinal();
            coordinates.remove(coordinates.size() - 1);

            x = Integer.parseInt(String.join("", coordinates)) - 1;
        }

        else {
            x = Letters.valueOf(coordinates.get(0)).ordinal();
            coordinates.remove(0);

            y = Integer.parseInt(String.join("", coordinates)) - 1;
        }

        numPos.add(x);
        numPos.add(y);
        return (numPos);
    }


    /**
     * Gets the String representation of the letters in this Word.
     * Developed by: Ibtasam Rasool
     *
     * @return The letters in this Word.
     */
    public String getWord()
    {
        return word;
    }


    /**
     * Gets the number of letters in this Word (the length).
     * Developed by: Ibtasam Rasool
     *
     * @return The length of this Word.
     */
    public int length()
    {
        return word.length();
    }


    /**
     * Calculates the wordScore of this Word.
     * Developed by: Ibtasam Rasool
     *
     * @return the wordScore of this Word.
     */
    public int wordScore(){
        int score = 0;
        String[] wordString = getWord().split("");
        for(String letter: wordString){
            score += Letters.valueOf(letter).getLetterScore();
        }
        return score;
    }


    /**
     * Gets the positions of the letters in this Word.
     * Developed by: Ibtasam Rasool
     *
     * @return A map containing this word's letter positions on the game Board.
     */
    public HashMap<String, String> getLetterPositions()
    {
        return letterPositions;
    }


    /**
     * checks if word falls within board bounds
     * Developed by: Ibtasam Rasool
     *
     * @return boolean representing if word fits
     */
    public boolean hasValidBounds()
    {
        return  (this.findWordPosition().get(1) + word.length() <= Board.SIZE);
    }


    /**
     *
     * @param coordinate
     * @return
     */
    public static ArrayList<Integer> numCoordinate(String coordinate){
        StringBuilder sb = new StringBuilder(coordinate);
        ArrayList<Integer> numericCoordinate = new ArrayList<>();
        numericCoordinate.add(Letters.valueOf(Character.toString(coordinate.charAt(0))).ordinal());
        sb.deleteCharAt(0);
        coordinate = sb.toString();
        numericCoordinate.add(Integer.parseInt(coordinate));

        return numericCoordinate;
    }
}
