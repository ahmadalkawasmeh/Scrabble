import org.junit.Test;

import static junit.framework.TestCase.*;


/**
 * A test class for the Board class, that tests the legality of Word placement on the Board.
 */
public class BoardTest {

    Board board, board2;


    @org.junit.Before
    public void setUp() {
        board = new Board();
        board2 = new Board();
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



    @Test
    public void testFirstWordCannotBePlacedOffCentreEvenOnASpecialSquare() {}




    @Test
    public void testSecondWordBuildNorth() {
        Word word1 = new Word("WORD", "8H"); // place WORD horizontally
        Word word2 = new Word("DOOR", "J5"); // place DOOR north of WORD reusing the using R of WORD
        board.addWordToBoard(word1);
        board.addWordToBoard(word2);

        assertTrue(board.checkWordOnBoard(word2));
    }


    @Test
    public void testSecondWordBuildEast() {
        Word word1 = new Word("WORD", "H8"); // Place WORD vertically
        Word word2 = new Word("ORDER", "9H"); // place ORDER east of DOOR reusing the O of WORD
        board.addWordToBoard(word1);
        board.addWordToBoard(word2);

        assertTrue(board.checkWordOnBoard(word2));
    }


    @Test
    public void testSecondWordBuildSouth() {
        Word word1 = new Word("WORD", "8H"); // place WORD horizontally
        Word word2 = new Word("ONTO", "I8"); // place ONTO south of WORD reusing the O in WORD
        board.addWordToBoard(word1);
        board.addWordToBoard(word2);

        assertTrue(board.checkWordOnBoard(word2));
    }


    @Test
    public void testSecondWordBuildWest() {
        Word word1 = new Word("WORD", "H8"); // Place WORD vertically
        Word word2 = new Word("FLOOR", "10D"); // place FLOOR west of DOOR reusing the R of WORD
        board.addWordToBoard(word1);
        board.addWordToBoard(word2);

        assertTrue(board.checkWordOnBoard(word2));
    }


    @Test
    public void testWordFailsIfPlacedBackwards() {
        Word word1 = new Word("SUCABA", "8H"); // Place the legal word ABACUS (horizontally) backwards
        board.addWordToBoard(word1);

        assertFalse(board.checkWordOnBoard(word1));
    }


    @Test
    public void testWordFailsIfPlacedUpwards() {
        Word word1 = new Word("SUCABA", "H8"); // Place the legal word ABACUS (vertically) upwards
        board.addWordToBoard(word1);

        assertFalse(board.checkWordOnBoard(word1));
    }


    @Test
    public void testSecondWordPlacedUsingFirstLetterOfFirstWord() {
        Word word1 = new Word("RARE", "8A");
        Word word2 = new Word("RAP", "C8");

        board2.addWordToBoard(word1);
        board2.addWordToBoard(word2);

        assertTrue(board2.checkWordOnBoard(word2));
    }


    @Test
    public void testSecondWordPlacedUsingLastLetterOfFirstWord() {
        Word word1 = new Word("POP", "2B");
        Word word2 = new Word("PANIC","D2");

        board.addWordToBoard(word1);
        board.addWordToBoard(word2);

        assertTrue(board.checkWordOnBoard(word2));
    }


    @Test
    public void testSecondWordPlacedUsingLetterFromMiddleOfFirstWord() {
        Word word1 = new Word("POP", "D6");
        Word word2 = new Word("ORANGE","7D");

        board.addWordToBoard(word1);
        board.addWordToBoard(word2);

        assertTrue(board.checkWordOnBoard(word2));
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
        Word word1 = new Word("FEE", "H8");
        Word word2 = new Word("S", "11H");

        board.addWordToBoard(word1);
        board.addWordToBoard(word2);

        assertTrue(board.checkWordOnBoard(word2));
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
        Word word1 = new Word("AIR", "8E");
        Word word2 = new Word("AIR", "G6");
        Word word3 = new Word("AIR", "6G");
        Word word4 = new Word("RAP", "I6");
        Word word5 = new Word("PLANE", "8I");

        board.addWordToBoard(word1);
        board.addWordToBoard(word2);
        board.addWordToBoard(word3);
        board.addWordToBoard(word4);
        board.addWordToBoard(word5);

        assertTrue(board.checkWordOnBoard(word5));
    }


    @Test
    public void testPlaceThirdWordUsingLettersFromTwoWordsOnBoard() {
        Word word1 = new Word("POP", "D6");
        Word word2 = new Word("POP","8D");
        Word word3 = new Word("POP", "6D");
        Word word4 = new Word("POP", "F6");

        board.addWordToBoard(word1);
        board.addWordToBoard(word2);
        board.addWordToBoard(word3);
        board.addWordToBoard(word4);

        assertTrue(board.checkWordOnBoard(word4));
    }


    @Test
    public void testPlaceWordSurroundedByLetters() {    //PEP was not a word so it initally failed !!!!!!!
        Word word1 = new Word("PEP", "D6");
        Word word2 = new Word("PEP","8D");
        Word word3 = new Word("PEP", "6D");
        Word word4 = new Word("PEP", "F6");
        Word word5 = new Word("EYE", "7D");

        board.addWordToBoard(word1);
        board.addWordToBoard(word2);
        board.addWordToBoard(word3);
        board.addWordToBoard(word4);
        board.addWordToBoard(word5);


        assertTrue(board.checkWordOnBoard(word5));
    }


    @Test
    public void testBoundsCanReuseLetterOnLeftSideOfBoard() {

        Word word1 = new Word("RARE", "8A");
        Word word2 = new Word("RAP", "A8");

        board.addWordToBoard(word1);
        board.addWordToBoard(word2);

        assertTrue(board.checkWordOnBoard(word2));
    }


    @Test
    public void testBoundsCanReuseLetterOnRightSideOfBoard() {
        Word word1 = new Word("FIRE", "O3");
        Word word2 = new Word("FIRE", "6L");

        board.addWordToBoard(word1);
        board.addWordToBoard(word2);

        assertTrue(board.checkWordOnBoard(word2));
    }


    @Test
    public void testBoundsCanPlaceWordOnTopRowOfBoard() {
        Word word1 = new Word("POP", "1A");

        board.addWordToBoard(word1);

        assertTrue(board.checkWordOnBoard(word1));
    }


    @Test
    public void testBoundsCanPlaceWordOnBottomRowOfBoard() {
        Word word1 = new Word("FIRE", "15F");

        board.addWordToBoard(word1);

        assertTrue(board.checkWordOnBoard(word1));
    }


    @Test
    public void testBoundsCanPlaceWordOnLeftSideOfBoard() {
        Word word1 = new Word("RARE", "8A");

        board.addWordToBoard(word1);

        assertTrue(board.checkWordOnBoard(word1));
    }


    @Test
    public void testBoundsCanPlaceWordOnRightSideOfBoard() {
        Word word1 = new Word("FIRE", "O3");

        board.addWordToBoard(word1);

        assertTrue(board.checkWordOnBoard(word1));
    }


    @Test
    public void testBoundsCanReuseLetterOnTopRowOfBoard() {
        Word word1 = new Word("POP", "1A");
        Word word2 = new Word("PANIC", "C1");

        board.addWordToBoard(word1);
        board.addWordToBoard(word2);

        assertTrue(board.checkWordOnBoard(word2));
    }


    @Test
    public void testBoundsCanReuseLetterOnBottomRowOfBoard() {
        Word word1 = new Word("FIRE", "15F");
        Word word2 = new Word("FIRE", "I12");

        board.addWordToBoard(word1);
        board.addWordToBoard(word2);

        assertTrue(board.checkWordOnBoard(word2));
    }
}