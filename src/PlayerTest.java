import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.*;


public class PlayerTest {


    Player p, p2, p3;
    ArrayList<String> before, after, expected, actual, temp, temp2;


    @Before
    public void setUp() throws Exception {
        p = new Player("Weird Al");
        p2 = new Player("P2");
        p3 = new Player("James");

        // used to compare the player's tray before and after invoking methods
        before = new ArrayList<>();
        after = new ArrayList<>();
        expected = new ArrayList<>();
        actual = new ArrayList<>();
        temp = new ArrayList<>();
    }

    @Test
    public void testConstructorInitialScoreZero() {
        assertTrue(p.getScore() == 0);
    }


    @Test
    public void testConstructorInitialNameIsCorrect() { // should update test after GUI (break from player input?)
        assertTrue(p.toString().equals("Weird Al"));
    }


    @Test
    public void testCanUpdateScoreByPositiveNumber() {
        assertTrue(p.getScore() == 0);
        p.updateScore(25);
        assertTrue(p.getScore() == 25);
        p.updateScore(1);
        assertTrue(p.getScore() == 26);
        p.updateScore(7);
        assertTrue(p.getScore() == 33);
    }


    @Test
    public void testCannotUpdateScoreByNegativeNumber() { // Actual fail --> need to update logic
        assertTrue(p.getScore() == 0);
        p.updateScore(-4);
        assertFalse(p.getScore() >= 0);
    }


    @Test
    public void testCheckInTrayTrueForListInTray() {
        assertFalse(true);
    }


    @Test
    public void testCheckInTrayFalseForListNotInTray() {
        assertFalse(true);
    }


    @Test
    public void testRemoveLettersRemovesALetter() {
        actual = p.getLetters();
        expected = p.getLetters();

        String letter = p.getLetters().get(0); // get the first letter of the player's tray
        temp.add(letter);   // put the letter into an array list
        p.removeLetters(temp); //remove that letter from the player's tray

        expected.remove(1); // remove the first index using ArrayList methods

        assert(p.getLetters().size() < Tray.SIZE);
        assertTrue(expected.equals(actual));
    }


    @Test
    public void testRemoveLettersRemovesAllLetters() {
        actual = p.getLetters();

        String letter;
        for (int i = 0; i < Tray.SIZE; i++) {
            letter = p.getLetters().get(i);
            temp.add(letter);
        }

        p.removeLetters(temp); //remove that letter from the player's tray

        assert(p.getLetters().size() == 0);
    }


    @Test
    public void testSwapLettersDoesNotSwapLettersNotInTray() {
        assertFalse(true);
    }


    @Test
    public void testSwapLettersSwapsOneOrMoreLetters() {
        before.addAll(p.getLetters()); // create a copy of the player's tray

        p.swapLetters(before); // attempt to swap all letters

        after.addAll(p.getLetters());

        assertFalse(before.equals(after));
    }
}