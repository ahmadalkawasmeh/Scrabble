import java.util.ArrayList;

/**
 * Player represents an individual player in the game of ScrabbleScrabble.
 * 
 * Each Player has a name, a score and a Tray to hold their letters.
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
     * Updates the score of this Player if they have placed a word on the board.
     *
     * @param wordScore The score of the word to be added to this Player's score.
     */
    public void updateScore(Integer wordScore)
    {
        this.score += wordScore;
    }
    
    
    /**
     * Returns the score of the player.
     *
     * @return The integer score of this Player.
     */
    public int getScore()
    {
        return score;
    }
    
    
    /**
     * Fills this Player's Tray to the maximum number of letters, if it is not already full.
     */
    public void fillTray()
    {
        tray.fill();
    }
    
    
    /**
     * Check if the letters in lettersList are currently in this Player's Tray.
     *
     * @param  lettersList  The letters that will be checked for.
     * @return true if all letters in lettersList are in the Player's Tray, 
     *         returns false otherwise.
     */
    public boolean checkInTray(ArrayList<String> lettersList)
    {
        return tray.checkInTray(lettersList);
    }
    
    
    /**
     * Remove all letters in lettersList from this Player's tray.  Each occurrence 
     * in lettersList will only be removed once if the Player has duplicate letters.
     *
     * @param lettersList The letters to remove from the Player's Tray.
     */


    public void removeLetters(ArrayList<String> lettersList)
    {
        tray.removeLetters(lettersList);
    }


    /*

    public static void main(String[] args) {
        Player player1 = new Player("Sam");
        System.out.println( player1.tray.toString());


    }

     */
}
