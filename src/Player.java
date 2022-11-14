import java.util.ArrayList;
import java.util.Collections;

/**
 * Player represents an individual player in the game of scrabblescrabble.
 * 
 * Each Player has a name, a score and a Tray to hold their letters.  Player's
 * use text input to add words to the scrabblescrabble board and increase their
 * score.
 *
 * @author James Grieder
 * @version 1.0
 */
public class Player
{
    private String name;
    private Integer score;
    private Tray tray;


    /**
     * Constructor for objects of class Player.  Initializes the player's name, 
     * and an initial score of 0.  Initializes an empty Tray for the player 
     * that will hold their letters.
     * Developed by: James Grieder
     * 
     * @param name The name of the player.
     */
    public Player(String name)
    {
        this.name = name;
        score = 0;
        tray = new Tray();
    }


    /**
     * Returns a String representation of the name of this Player.
     * Developed by: James Grieder
     *
     * @return the name of this Player.
     */
    @Override
    public String toString() {
        return this.name;
    }


    /**
     * Returns a String representation of the letters in this Player's tray.
     * For example:  "L M N O P Q R "
     * Developed by: James Grieder
     *
     * @return the letters in this Player's tray.
     */
    public String stringTray() {
        return tray.toString();
    }


    public ArrayList<String> getLetters() {
        return tray.getLetters();
    }


    /**
     * Updates the score of this Player if they have placed a word on the board.
     * Developed by: James Grieder
     *
     * @param wordScore The score of the word to be added to this Player's score.
     */
    public void updateScore(Integer wordScore)
    {
        this.score += wordScore;
    }
    
    
    /**
     * Returns the score of the player.
     * Developed by: James Grieder
     *
     * @return The integer score of this Player.
     */
    public int getScore()
    {
        return score;
    }
    
    
    /**
     * Fills this Player's Tray to the maximum number of letters, if it is not already full.
     * Developed by: James Grieder
     */
    public void fillTray()
    {
        tray.fill();
    }
    
    
    /**
     * Check if the letters in lettersList are currently in this Player's Tray.
     * Developed by: James Grieder & Ibtasam Rasool
     *
     * @param  lettersList  The letters that will be checked for.
     * @return true if all letters in lettersList are in the Player's Tray, 
     *         returns false otherwise.
     */
    public boolean checkInTray(ArrayList<String> lettersList)
    {
        for(String letter: lettersList){
            if (!tray.checkInTray(letter) || !(tray.checkInTrayFrequency(letter) >= Collections.frequency(lettersList, letter))) { // need to add condition to check board for letters not in tray
                return false;
            }
        }
        return true;
    }
    
    
    /**
     * Remove all letters in lettersList from this Player's tray.  Each occurrence 
     * in lettersList will only be removed once if the Player has duplicate letters.
     * Developed by: James Grieder & Ibtasam Rasool
     *
     * @param lettersList The letters to remove from the Player's Tray.
     */
    public void removeLetters(ArrayList<String> lettersList)
    {
        for(String letter: lettersList){

            if(tray.checkInTray(letter)){
                tray.removeLetter(letter);
            }
        }
    }

    /**
     * swaps a given list of letters in tray with letters from letter bag
     * @param lettersList list of letters to swap
     */
    public void swapLetters(ArrayList<String> lettersList){
        for(String letter: lettersList){

            if(tray.checkInTray(letter)){
                tray.returnLetterToBag(letter);
            }
        }
        fillTray();
    }
}
