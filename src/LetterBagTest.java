import static org.junit.Assert.*;
import org.junit.*;

/**
 * A test class for the LetterBag class.
 */
public class LetterBagTest {

    private LetterBag bag;

    @Before
    public void setUp() throws Exception {

        bag = new LetterBag();
        if (bag == null) throw new Exception("Uninitialized LetterBag");


    }

    //Deliverable 3
//    @Test
//    public void lettersLeft() {
//
//    }

    @Test
    public void initialLettersLeft(){
        assertEquals(27,bag.lettersLeft());
    }


    @Test
    public void LetterQuantity(){
        assertEquals(9, bag.letterQuantity("A"));
    }

    @Test
    public void drawRandomLetter(){
        LetterBag bag2 = new LetterBag();
        String s = bag.drawRandomLetter();

        assertNotEquals(bag.letterQuantity(s),bag2.letterQuantity(s));
    }

    @Test
    public void increaseLetterQuantity(){
        bag.increaseLetterQuantity("A");
        assertEquals(10, bag.letterQuantity("A"));
    }

    @Test
    public void decreaseLetterQuantityBy1(){
        assertEquals(9, bag.letterQuantity("A"));
        bag.decreaseLetterQuantity("A");
        assertEquals(8, bag.letterQuantity("A"));
    }

    @Test
    public void decreaseLetterQuantityUntilNoneLeft(){
        assertEquals(2, bag.letterQuantity("B"));
        bag.decreaseLetterQuantity("B");
        assertEquals(1, bag.letterQuantity("B"));
        bag.decreaseLetterQuantity("B");
        assertEquals(0, bag.letterQuantity("B"));
    }
}