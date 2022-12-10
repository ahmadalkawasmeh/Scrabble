import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * A test class for the Word class that tests the scoring of Words.
 */
public class WordScoreTest {

    Board board, board2;


    @org.junit.Before
    public void setUp() {
        board = new Board();
        board2 = new Board();
    }

    @Test
    public void testLetterScoresOfAlphabet1() {
        Word word1 = new Word("THE", "8E");
        board.addWordToBoard(word1);

        assertEquals(6, word1.wordScore());
    }

    @Test
    public void testLetterScoresOfAlphabet2() {
        Word word1 = new Word("QUICK", "8E");
        board.addWordToBoard(word1);

        assertEquals(20, word1.wordScore());
    }

    @Test
    public void testLetterScoresOfAlphabet3() {
        Word word1 = new Word("BROWN", "8E");
        board.addWordToBoard(word1);

        assertEquals(10, word1.wordScore());
    }

    @Test
    public void testLetterScoresOfAlphabet4() {
        Word word1 = new Word("FOX", "8E");
        board.addWordToBoard(word1);

        assertEquals(13, word1.wordScore());
    }

    @Test
    public void testLetterScoresOfAlphabet5() {
        Word word1 = new Word("JUMPS", "8E");
        board.addWordToBoard(word1);

        assertEquals(16, word1.wordScore());
    }

    @Test
    public void testLetterScoresOfAlphabet6() {
        Word word1 = new Word("OVER", "8E");
        board.addWordToBoard(word1);

        assertEquals(7, word1.wordScore());
    }

    @Test
    public void testLetterScoresOfAlphabet7() {
        Word word1 = new Word("LAZY", "8E");
        board.addWordToBoard(word1);

        assertEquals(16, word1.wordScore());
    }

    @Test
    public void testLetterScoresOfAlphabet8() {
        Word word1 = new Word("DOG", "8E");
        board.addWordToBoard(word1);

        assertEquals(5, word1.wordScore());
    }


    @Test
    public void testDoubleLetterScore() {
        Word word1 = new Word("CAT", "H6");
        Word word2 = new Word("CAR", "6H"); // double letter at R
        board.addWordToBoard(word1);
        board.addWordToBoard(word2);
        System.out.println(board.toString());

        assertEquals(6, word2.wordScore());
    }

    @Test
    public void testTripleLetterScore() {
        Word word1 = new Word("THANK", "8H");// Triple letter at K
        board.addWordToBoard(word1);

        assertEquals(22, word1.wordScore());
    }

    @Test
    public void testDoubleWordScore() {
        Word word1 = new Word("THANK", "8H");
        Word word2 = new Word("NICE", "K8"); // double word at E
        board.addWordToBoard(word1);
        board.addWordToBoard(word2);

        assertEquals(12, word2.wordScore());
    }

    @Test
    public void testTripleWordScore() {
        Word word1 = new Word("THANK", "8H");
        Word word2 = new Word("NICE", "K8");
        Word word3 = new Word("EAGLE", "11K");
        Word word4 = new Word("LIVE", "O8"); // Triple word at L
        board.addWordToBoard(word1);
        board.addWordToBoard(word2);
        board.addWordToBoard(word3);
        board.addWordToBoard(word4);

        assertEquals(21, word4.wordScore());
    }

    @Test
    public void testTwoDifferentLettersScoreMultipliers() {
        Word word1 = new Word("THANKS", "8H");
        Word word2 = new Word("LIST", "M6"); // triple letter at I and T
        board.addWordToBoard(word1);
        board.addWordToBoard(word2);

        assertEquals(8, word2.wordScore());
    }

    @Test
    public void testTwoWordScoreAndOneLetterScoreMultipliers() {
        Word word1 = new Word("ORANGE", "8H");
        Word word2 = new Word("BIN", "7M");
        Word word3 = new Word("AIRPLANE", "O1"); // triple word at first A, and E, and triple letter at P
        board.addWordToBoard(word1);
        board.addWordToBoard(word2);
        board.addWordToBoard(word3);

        assertEquals(48, word3.wordScore());
    }

    @Test
    public void testOneWordScoreAndTwoLetterScoreMultipliers() {
        Word word1 = new Word("LIGHTER", "8H");
        Word word2 = new Word("LETTERS", "M3"); // double word at L, triple letter at 2nd E, and S
        board.addWordToBoard(word1);
        board.addWordToBoard(word2);

        assertEquals(22, word2.wordScore());
    }

    /*
    @Test
    public void testBlankScoreIs0OnRegularSquare() {

    }

    @Test
    public void testBlankScoreIs0OnDoubleLetterScore() {

    }

    @Test
    public void testBlankScoreIs0OnTripleLetterScore() {

    }

    @Test
    public void testBlankScoreIs0OnDoubleWordScore() {

    }

    @Test
    public void testBlankScoreIs0OnTripleWordScore() {

    }
     */


    @Test
    public void test50PointsForPlacingEntireTrayOnFirstWord() {
        Word word1 = new Word("LABELED", "8E");
        board.addWordToBoard(word1);

        assertEquals(60, word1.wordScore());
    }


    @Test
    public void test50PointsForPlacingEntireTrayNotOnFirstWord() {
        Word word1 = new Word("LIGHT", "H8");
        Word word2 = new Word("LIMITING", "10A"); // double letter at 1st and 3rd I
        board.addWordToBoard(word1);
        board.addWordToBoard(word2);

        assertEquals(63, word2.wordScore());
    }

}
