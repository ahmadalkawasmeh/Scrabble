import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

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
        //
    }


    @Test
    public void testRemoveLetter() {
        //
    }


    @Test
    public void testReturnLetterToBag() {
        //
    }


    @Test
    public void testCheckInTrayForLetterInTray() {
        //
    }


    @Test
    public void testCheckInTrayForLetterNotInTray() {
        //
    }


    @Test
    public void testFillFillsOneLetter() {
        //
    }


    @Test
    public void testFillFillsMultipleLetters() {
        //
    }


    @Test
    public void testFillFillsAnEmptyTray() {
        //
    }
}