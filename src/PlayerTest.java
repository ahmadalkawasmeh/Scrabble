import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class PlayerTest {

    Player p;

    @Before
    public void setUp() throws Exception {
        p = new Player("Weird Al");
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
    public void testConstructorInitialTrayIsSetUp() {
        assertFalse(true);
    }


    @Test
    public void testCanUpdateScoreByPositiveNumber() {
        assertFalse(true);
    }


    @Test
    public void testCannotUpdateScoreByNegativeNumber() {
        assertFalse(true);
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
        assertFalse(true);
    }


    @Test
    public void testRemoveLettersDoesNotRemoveALetterNotInTray() {
        assertFalse(true);
    }


    @Test
    public void testTestRemoveLettersRemoves4Letters() {
        assertFalse(true);
    }


    @Test
    public void testRemoveLettersRemovesAllLetters() {
        assertFalse(true);
    }


    @Test
    public void testSwapLettersSwapsALetter() {
        assertFalse(true);
    }


    @Test
    public void testSwapLettersDoesNotSwapLettersNotInTray() {
        assertFalse(true);
    }


    @Test
    public void testSwapLettersSwapMultipleLetters() {
        assertFalse(true);
    }


    @Test
    public void testSwapLettersCanSwapAllLetters() {
        assertFalse(true);
    }

}