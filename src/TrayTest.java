import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

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
        t.fillBag();
    }


    @Test
    public void testTrayConstructor() {
        assertNotNull(t);
        assertEquals(Tray.SIZE,t.getLetters().size());
    }


    @Test
    public void testRemoveLetterRemoves1LetterAtATimeUntilEmpty() {
        String letters = "";
        Tray testTray = new Tray();
        for(String letter : testTray.getLetters()){
            letters += letter;
        }
        int expectedSize = Tray.SIZE;
        for(int i = 0; i < letters.length(); i ++) {
            testTray.removeLetter("" + letters.charAt(i));
            expectedSize--;
            assertEquals(expectedSize, testTray.getLetters().size());
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
    public void testRemoveBlank() {
        Tray testTray = new Tray("A B C __ D E F");
        String blank = "__";
        assertEquals(1, testTray.checkInTrayFrequency(blank));
        System.out.println("before: " + testTray);

        testTray.removeLetter("__");
        System.out.println("after: " + testTray);

        assertEquals(0, testTray.checkInTrayFrequency(blank));
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
        ArrayList<String> letters = new ArrayList<>();
        Tray testTray = t;
        for(int i = 0; i < testTray.getLetters().size();i++){
            letters.add(i,testTray.getLetters().get(i));
        }
        for(int i = 0; i < letters.size(); i ++) {
            t.removeLetter("" + letters.get(i));
        }

        assertEquals(0,t.getLetters().size());
        t.fill();
        assertEquals(Tray.SIZE,t.getLetters().size());
    }
}