import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Wrods that are played, words have a string value as well as a position value.
 *
 * @author Ibtasam Rasool
 * @version 1.0
 */

public class Word {

    String word;
    String position;

    HashMap<String, String> letterPositions;

    public Word(String word, String position){
        letterPositions = new HashMap<>();
        this.word = word;
        this.position =  position;
        this.addLettersToMap();

    }


    /**
     * Adds a letter to the hashmap repersenting the positions used by a word
     * NOTE: all values are repersented with Alphabet then numeric value (i.e. A5) even if the value was entered in as 5A this maintains consistency in map.
     */
    public void addLettersToMap(){
        Integer changingCoordinate;
        ArrayList<Integer> numPos = findWordPosition();
        String letters[] = word.split("");

        if(isHorizontal()) {
            for (int i = 0; i < length(); i++) {

                     changingCoordinate = numPos.get(1) + i;
                     letterPositions.put(Letters.values()[changingCoordinate].toString() + numPos.get(0), letters[i]);
            }

        }

        else{
            for (int i = 0; i < length(); i++) {

                changingCoordinate = numPos.get(1) + i;
                letterPositions.put(Letters.values()[numPos.get(0)].toString() + changingCoordinate, letters[i]);

            }


        }

    }


    /**
     * @return Boolean value repersenting whether a word is horizontal.
     */
    public boolean isHorizontal(){

        return Character.isDigit(position.charAt(0));

    }

    /**
     * finds position of word on the baard.
     * @return returns list containing x and y positions of word on board       Slightly overwritten will clean up later
     */
    public ArrayList<Integer> findWordPosition(){
        int x, y;
        ArrayList<Integer> numPos = new ArrayList<>();
        String coordinates[] =  position.split("");

        if (isHorizontal()){

            if(coordinates.length > 2){
                x = Integer.parseInt(coordinates[0]) * 10;
                x += Integer.parseInt(coordinates[1]) - 1;
                System.out.println(x);
                y = Letters.valueOf(coordinates[2]).ordinal();

                numPos.add(x);
                numPos.add(y);
                return (numPos);
            }
            x = Integer.parseInt(coordinates[0]) - 1; // - 1 added for proper index
            y = Letters.valueOf(coordinates[1]).ordinal();

            numPos.add(x);
            numPos.add(y);

            return(numPos);

        }

        if(coordinates.length > 2){
            y = Integer.parseInt(coordinates[1]) * 10;
            y += Integer.parseInt(coordinates[2]) - 1;
            x = Letters.valueOf(coordinates[0]).ordinal();

            numPos.add(x);
            numPos.add(y);
            return (numPos);
        }

        y = Integer.parseInt(coordinates[1]) - 1;
        x = Letters.valueOf(coordinates[0]).ordinal();

        numPos.add(x);
        numPos.add(y);

        return(numPos);


    }

    /**
     * @return word
     */
    public String getWord(){

        return  word;
    }

    /**
     * @return length of word
     */
    public int length(){

        return word.length();

    }

    /**
     * @return calculates word score
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
     * @return a map containing a words letter locations on the board
     */
    public HashMap<String, String> getLetterPositions(){

        return letterPositions;
    }

    /*
    public static void main(String[] args) {
        Word word = new Word("HELLO", "5G");


    }
    */

}
