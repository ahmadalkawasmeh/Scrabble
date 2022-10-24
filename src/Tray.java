import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Repersents the players letter tray.
 *
 * @author Ibtasam Rasool
 * @version 1.0
 */
public class Tray
{
    private static final int NUMLETTERS = 7;
    private ArrayList<String> letters;

    private LetterBag letterBag;




    /**
     * Constructor for objects of class Tray
     */
    public Tray()
    {
        letters = new ArrayList<>();
        letterBag = Game.getLetterBag();
        this.fill();
    }


    /**
     * Fills Tray to size of NUMLETTERS (in this case 7)
     */
    public void fill(){
        while(letters.size() < NUMLETTERS){

            letters.add(letterBag.drawRandomLetter());

        }
    }


    /**
     * Checks if given letter is in Tray
     * @param letter
     * @return  Boolean, True if word is in Tray False otherwise
     */
    public boolean checkInTray(String letter)
    {
        return letters.contains(letter);
    }


    /**
     * checks frequency of letter in tray
     * @param letter
     * @return Frequency of a letter in the tray
     */
    public int checkInTrayFrequency(String letter)
    {
        return Collections.frequency(letters, letter);
    }


    /**
     * Removes a given letter from the tray
     * @param letter
     */
    public void removeLetter(String letter) {
                this.letters.remove(letter);
    }


    /**
     * @return String repersentation of Tray
     */
    public String toString()
    {
        StringBuffer stringBuffer = new StringBuffer();

        for(String letter : letters){
            stringBuffer.append(letter);
            stringBuffer.append(" ");
        }

        return  stringBuffer.toString();
    }
}
