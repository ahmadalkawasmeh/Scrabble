import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

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

    /*
    @Test
    public void testDoubleLetterScore() {
        Word word1 = new Word("DOG", "8E");
        board.addWordToBoard(word1);

        assertEquals(5, word1.wordScore());
    }

    @Test
    public void testTripleLetterScore() {
        Word word1 = new Word("DOG", "8E");
        board.addWordToBoard(word1);

        assertEquals(5, word1.wordScore());
    }

    @Test
    public void testDoubleWordScore() {
        Word word1 = new Word("DOG", "8E");
        board.addWordToBoard(word1);

        assertEquals(5, word1.wordScore());
    }

    @Test
    public void testTripleWordScore() {
        Word word1 = new Word("DOG", "8E");
        board.addWordToBoard(word1);

        assertEquals(5, word1.wordScore());
    }

    @Test
    public void testTwoDifferentLettersScoreMultipliers() {
        Word word1 = new Word("DOG", "8E");
        board.addWordToBoard(word1);

        assertEquals(5, word1.wordScore());
    }

    @Test
    public void testTwoDifferentWordScoreMultipliers() {
        Word word1 = new Word("DOG", "8E");
        board.addWordToBoard(word1);

        assertEquals(5, word1.wordScore());
    }

    @Test
    public void testWordScoreWithLetterScoreMultipliers() {
        Word word1 = new Word("DOG", "8E");
        board.addWordToBoard(word1);

        assertEquals(5, word1.wordScore());
    }

    @Test
    public void testBlankScoreIs0() {
        Word word1 = new Word("A", "8E"); ///// make this blank once blank tiles are implemented
        board.addWordToBoard(word1);

        assertEquals(5, word1.wordScore());
    }

     */


    @Test
    public void test50PointsForPlacingEntireTrayOnFirstWord() {
        Word word1 = new Word("LABELED", "8E");
        board.addWordToBoard(word1);

        assertEquals(60, word1.wordScore());
    }

    /*
    @Test
    public void test50PointsForPlacingEntireTrayNotOnFirstWord() { // need to wait for special squares
        Word word1 = new Word("LABELED", "8E");
        board.addWordToBoard(word1);

        assertEquals(60, word1.wordScore());
    }

     */




}
