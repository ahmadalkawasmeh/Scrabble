import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

/**
 * A test class for the Tray class.
 */
public class TrayTest {

    Tray t;

    @Before
    public void setUp() throws Exception {
        t = new Tray();
    }


    @Test
    public void testTrayConstructor() {
        assertNotNull(t);
        assertEquals(Tray.SIZE,t.getLetters().size());
    }


    @Test
    public void testRemoveLetterRemoves1LetterAtATimeUntilEmpty() {
        Tray testTray = t;
        int i = Tray.SIZE-1;
        for(String letter : testTray.getLetters()){
            t.removeLetter(letter);
            assertEquals(i,t.getLetters().size());
            i -= 1;
        }
    }


    @Test
    public void testRemoveLetter() {
        Tray testTray = t;
        String letter = t.getLetters().get(0);
        int freq = t.checkInTrayFrequency(letter);

        t.removeLetter(letter);
        assertTrue(t.checkInTrayFrequency(letter) < freq);
    }


    @Test
    public void testReturnLetterToBag() {
        Tray testTray = t;
        String letter = t.getLetters().get(0);
        int bagFreq = t.getLetterBag().letterQuantity(letter);

        t.returnLetterToBag(letter);
        assertTrue(t.getLetterBag().letterQuantity(letter) > bagFreq);
    }

    @Test
    public void testFillFillsAnEmptyTray() {
        Tray testTray = t;
        for(String letter : testTray.getLetters()){
            t.removeLetter(letter);
        }
        t.fill();
        assertEquals(Tray.SIZE,t.getLetters().size());
    }
}