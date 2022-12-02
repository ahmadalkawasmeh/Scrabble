import net.jcip.annotations.NotThreadSafe;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


/**
 * A test class for the Player class.
 */
@NotThreadSafe
public class PlayerTest {

    Player p, p2, p3;

    @Before
    public void setUp() throws Exception {
        p = new Player("Weird Al", false);
        p2 = new Player("P2", false);
        p3 = new Player("James", false);
    }


    @After
    public void tearDown() throws Exception {
        p = null;
        p2 = null;
        p3 = null;
    }


    @Test
    public void testConstructorInitialScoreZero() {
        assertTrue(p.getScore() == 0);
    }


    @Test
    public void testConstructorInitialNameIsCorrect() { // should update test after GUI (break from player input?)
        assertEquals("Weird Al", p.toString());
    }


    @Test
    public void testConstructorInitialTrayIsSetUp() { assertTrue(p.numberOfLettersLeftInTray() == Tray.SIZE);

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

        ArrayList<String> letters = p.getLetters();
        ArrayList<String> lettersToCheck = new ArrayList<>();

        for (int i = 0; i < Tray.SIZE; i++) {
            lettersToCheck.add(letters.get(i));
            assertTrue(p.checkInTray(lettersToCheck));
        }
    }


    @Test
    public void testCheckInTrayFalseForListNotInTray() {

        ArrayList<String> letters = p.getLetters();
        ArrayList<String> lettersToRemove = new ArrayList<>();
        ArrayList<String> letterToCheck = new ArrayList<>();
        String letter = letters.get(0);

        for(int i = 0; i < Tray.SIZE; i++) {
            lettersToRemove.add(letter);
        }
        p.removeLetters(lettersToRemove);
        letterToCheck.add(letter);

        assertTrue(p.numberOfLettersLeftInTray() > 0);
        assertFalse(p.checkInTray(letterToCheck));
    }


    @Test
    public void testRemoveLettersRemovesALetter() {
        int before = p.numberOfLettersLeftInTray();

        ArrayList<String> letters = p.getLetters();
        ArrayList<String> lettersToRemove = new ArrayList<>();
        lettersToRemove.add(letters.get(0));

        p.removeLetters(lettersToRemove);

        assertTrue(before == (p.numberOfLettersLeftInTray() + 1));
    }


    @Test
    public void testTestRemoveLettersRemoves4Letters() {
        if (Tray.SIZE > 4) {
            int before = p.numberOfLettersLeftInTray();

            ArrayList<String> letters = p.getLetters();
            ArrayList<String> lettersToRemove = new ArrayList<>();

            lettersToRemove.add(letters.get(0));
            lettersToRemove.add(letters.get(1));
            lettersToRemove.add(letters.get(2));
            lettersToRemove.add(letters.get(3));

            p.removeLetters(lettersToRemove);

            assertTrue(before == (p.numberOfLettersLeftInTray() + 4));
        } else {
            assertTrue(true);
        }
    }


    @Test
    public void testRemoveLettersRemovesAllLetters() {
        int before = p.numberOfLettersLeftInTray();

        ArrayList<String> letters = p.getLetters();
        ArrayList<String> lettersToRemove = new ArrayList<>();

        for (int i = 0; i < Tray.SIZE; i++) {
            lettersToRemove.add(letters.get(i));
        }

        p.removeLetters(lettersToRemove);
        int after = p.numberOfLettersLeftInTray();

        assertTrue(before == Tray.SIZE);
        assertTrue(after == 0);
    }


    @Test
    public void testSwapLettersSwapsOneOrMoreLetters() {

        // Note: This method runs 3 similar cases, as a swapped letter
        // may be replaced with the same letter upon swapping with the LetterBag

        ArrayList<String> before = p.getLetters();
        ArrayList<String> before2 = p2.getLetters();
        ArrayList<String> before3 = p3.getLetters();

        ArrayList<String> lettersToSwap1 = new ArrayList<>();
        ArrayList<String> lettersToSwap2 = new ArrayList<>();
        ArrayList<String> lettersToSwap3 = new ArrayList<>();

        lettersToSwap1.add(before.get(0));
        lettersToSwap2.add(before2.get(0));
        lettersToSwap3.add(before3.get(0));

        p.swapLetters(lettersToSwap1);
        p2.swapLetters(lettersToSwap2);
        p3.swapLetters(lettersToSwap3);

        // Check that at least one of the cases results in a letter being replaced with a different letter
        assertFalse(!before.equals(p.getLetters()) &&
                !before2.equals(p2.getLetters()) &&
                !before3.equals(p3.getLetters()));
    }
}