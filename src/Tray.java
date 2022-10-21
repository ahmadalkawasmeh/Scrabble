import java.util.ArrayList;

/**
 * Write a description of class Tray here.
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
        letterBag = new LetterBag();
        this.fill();
    }


    public void fill(){
        while(letters.size() < NUMLETTERS){

            letters.add(letterBag.drawRandomLetter());

        }
    }
    
    public boolean checkInTray(ArrayList<String> letters)
    {
        for(String letter: letters){
             if(! this.letters.equals(letter)){
                 return false;
             }
        }
        return true;
    }


    public void removeLetters(ArrayList<String> letters){

        for(String letter: letters){

            if(this.letters.equals(letter)){

                this.letters.remove(letter);
            }
        }
    }


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
