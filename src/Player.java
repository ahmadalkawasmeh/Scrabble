import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

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
public class Player implements Serializable
{
    private String name;
    private Integer score;
    private Tray tray;

    boolean isAIPlayer;


    /**
     * Constructor for objects of class Player.  Initializes the player's name,
     * and an initial score of 0.  Initializes an empty Tray for the player
     * that will hold their letters.  If the Player is an AI player, an AIHelper
     * will be initialized allowing the AI Player to make decisions on each turn.
     * Developed by: James Grieder
     *
     * @param name The name of the player.
     * @param isAI true if the Player is AI, false if the Player is a user
     */
    public Player(String name, boolean isAI)
    {
        this.name = name;
        score = 0;
        tray = new Tray();
        isAIPlayer = isAI;
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

    public Tray getTray() {return tray;}


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


    /**
     * Gets the letters from this Player's tray.
     *
     * @return A list of the letters in this Player's tray.
     */
    public ArrayList<String> getLetters() {
        return tray.getLetters();
    }

    /**
     * Fill this Player's bag with the initial letter amounts
     */
    public void fillBag(){
        tray.fillBag();
    }

    public LetterBag getLetterBag(){
        return tray.getLetterBag();
    }


    /**
     * Get this Player's score as a text String.
     *
     * @return The Player's Score represented in a String format.
     */
    public String stringScore() {
        return score.toString();
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
            if (!tray.checkLetterInTray(letter) || !(tray.checkInTrayFrequency(letter) >= Collections.frequency(lettersList, letter))) {
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

            if(tray.checkLetterInTray(letter)){
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

            if(tray.checkLetterInTray(letter)){
                tray.returnLetterToBag(letter);
            }
        }
        fillTray();
    }

    /**
     * Gets the number of letters in this Player's Tray.
     * The number will be between 0 and Tray.SIZE inclusively.
     *
     * @return The number of letters left in this Player's tray.
     */
    public int numberOfLettersLeftInTray() {
        return tray.remainingNumberOfLettersInTray();
    }

    /**
     * Returns whether the player is controlled by AI, or if the player is
     * controlled by a user playing the game.
     * @return True if the Player is an AIPlayer, false if the Player is a user
     */
    public boolean isAIPlayer() {
        return isAIPlayer;
    }


    /**
     * Gets an AI Player's next move using the AIHelper.
     *
     * @return A string representing an AI Player's next move
     */
    public String getNextAIMove() {
        return AIHelper.getNextAIMove(tray);
    }


    /**
     * Adds a letter to this tray
     *
     * Developed by Daniel
     *
     * @param letter  to be added
     */
    public void addLetter(Letters letter)
    {
        String remove = tray.getLetters().get(6);
        tray.removeLetter(remove);
        tray.addLetter(String.valueOf(Letters.__));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        System.out.println("here here here player");
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;
        System.out.println("here here here player");

        if (isAIPlayer != player.isAIPlayer) return false;
        System.out.println("isAIPlayer is good");
        if (!name.equals(player.name)) return false;
        System.out.println("name is good");
        if (!score.equals(player.score)) return false;
        System.out.println("score is good...tray is not");
        return Objects.equals(tray, player.tray);
    }


}
