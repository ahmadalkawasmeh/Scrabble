import org.junit.Test;

import static junit.framework.TestCase.*;

public class WordTest {

    Board board;

    @org.junit.Before
    public void setUp() {
        board = new Board();
    }

    @Test
    public void testWordPlacedIfLegal() {
        Word word1 = new Word("WORD", "H8");
        board.addWordToBoard(word1);

        assertTrue(board.checkWordOnBoard(word1));
    }

    @Test
    public void testWordNotPlacedIfIllegal() {
        Word word1 = new Word("ABCDEF", "H8");
        board.addWordToBoard(word1);

        assertFalse(board.checkWordOnBoard(word1));
    }

    @Test
    public void testWordNotPlacedIfIllegalEvenIfActualWord() {
        Word word1 = new Word("ABACUS", "H8");
        board.addWordToBoard(word1);

        assertFalse(board.checkWordOnBoard(word1));
    }

    @Test
    public void testFirstWordMustBePlacedOnCentre() {
        Word word1 = new Word("WORD", "H8");
        board.addWordToBoard(word1);

        assertTrue(board.checkWordOnBoard(word1));
    }

    @Test
    public void testFirstWordCannotBePlacedOffCentre() {
        Word word1 = new Word("WORD", "1A");
        board.addWordToBoard(word1);

        assertFalse(board.checkWordOnBoard(word1)); // THIS FAIL IS A REAL FAIL --> Need to fix game logic
    }

    /* MILESTONE 3
    @Test
    public void testFirstWordCannotBePlacedOffCentreEvenOnASpecialSquare() {}
     */

    @Test
    public void testCannotPlaceDiagonalWord() {
        assertFalse(true); // not sure if this test is needed - will wait until GUI is linked then decide
    }

    @Test
    public void testSecondWordBuildNorth() {
        Word word1 = new Word("WORD", "8H");
        Word word2 = new Word("DOOR", "J5");
        board.addWordToBoard(word1);
        board.addWordToBoard(word2);

        assertTrue(board.checkWordOnBoard(word2));
    }

    @Test
    public void testSecondWordBuildEast() {
        assertFalse(true);
    }

    @Test
    public void testSecondWordBuildSouth() {
        assertFalse(true);
    }

    @Test
    public void testSecondWordBuildWest() {
        assertFalse(true);
    }

    @Test
    public void testWordFailsIfPlacedBackwards() {
        assertFalse(true); // may not need test --> wait for GUI
    }

    @Test
    public void testWordFailsIfPlacedUpwards() {
        assertFalse(true); // may not need test --> wait for GUI
    }

    @Test
    public void testSecondWordPlacedUsingFirstLetterOfFirstWord() {
        assertFalse(true);
    }

    @Test
    public void testSecondWordPlacedUsingLastLetterOfFirstWord() {
        assertFalse(true);
    }

    @Test
    public void testSecondWordPlacedUsingLetterFromMiddleOfFirstWord() {
        assertFalse(true);
    }

    @Test
    public void testPlaceSecondPartOfCompoundWordPlacedIfFullWordInDictionary() {
        Word word1 = new Word("AIR", "8H");
        Word word2 = new Word("PLANE", "8K");
        board.addWordToBoard(word1);
        board.addWordToBoard(word2);

        assertTrue(board.checkWordOnBoard(word2));
    }

    @Test
    public void testWordNotPlacedIfWordPlacedEndToEndAndNotInDictionary() {
        Word word1 = new Word("PLANE", "8H");
        Word word2 = new Word("AIR", "8M");
        board.addWordToBoard(word1);
        board.addWordToBoard(word2);

        assertFalse(board.checkWordOnBoard(word2));
    }

    @Test
    public void testLegalWordPlacedIfAddLetterSToEndOfWord() {
        //Word word1 = new Word("BIRD", "H8");
        //Word word2 = new Word("S", "H12");

        //Word word1 = new Word("FEE", "H8");
        // word2 = new Word("S", "H11");

        Word word1 = new Word("FEE", "H8");
        Word word2 = new Word("S", "11H");

        board.addWordToBoard(word1);
        board.addWordToBoard(word2);

        assertTrue(board.checkWordOnBoard(word2)); // THIS FAIL IS A REAL FAIL --> Need to fix game logic
    }

    @Test
    public void testIllegalWordNotPlacedIfRandomLetterAddedToEndOfWord() {
        Word word1 = new Word("BINARY", "8H");
        Word word2 = new Word("Q", "M8");

        board.addWordToBoard(word1);
        board.addWordToBoard(word2);

        assertFalse(board.checkWordOnBoard(word2));
    }

    @Test
    public void testPlaceWordIfSpaceBetween() {
        assertFalse(true);
    }

    @Test
    public void testPlaceThirdWordUsingLettersFromTwoWordsOnBoard() {
        assertFalse(true);
    }

    @Test
    public void testPlaceWordSurroundedByLettersConfigurationA() {
        assertFalse(true);
    }

    @Test
    public void testPlaceWordSurroundedByLettersConfigurationB() {
        assertFalse(true);
    }
}