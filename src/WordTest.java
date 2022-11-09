import org.junit.Test;

public class WordTest {

    Game m;

    @org.junit.Before
    public void setUp() {
        m = new Game(2);
    }

    @Test
    public void testWordPlacedIfLegal() {

    }

    @Test
    public void testWordNotPlacedIfIllegal() {

    }

    @Test
    public void testWordNotPlacedIfIllegalEvenIfActualWord() {

    }

    @Test
    public void testFirstWordMustBePlacedOnCentre() {

    }

    @Test
    public void testFirstWordCannotBePlacedOffCentre() {

    }

    /* MILESTONE 3
    @Test
    public void testFirstWordCannotBePlacedOffCentre() {}
     */

    @Test
    public void testCannotPlaceDiagonalWord() {

    }

    @Test
    public void testSecondWordBuildNorth() {

    }

    @Test
    public void testSecondWordBuildEast() {

    }

    @Test
    public void testSecondWordBuildSouth() {

    }

    @Test
    public void testSecondWordBuildWest() {

    }

    @Test
    public void testWordFailsIfPlacedBackwards() {

    }

    @Test
    public void testWordFailsIfPlacedUpwards() {

    }

    @Test
    public void testSecondWordPlacedUsingFirstLetterOfFirstWord() {

    }

    @Test
    public void testSecondWordPlacedUsingLastLetterOfFirstWord() {

    }

    @Test
    public void testSecondWordPlacedUsingLetterFromMiddleOfFirstWord() {

    }

    @Test
    public void testPlaceSecondPartOfCompoundWordPlacedIfFullWordInDictionary() {

    }

    @Test
    public void testWordNotPlacedIfWordPlacedEndToEndAndNotInDictionary() {

    }

    @Test
    public void testLegalWordPlacedIfAddLetterSToEndOfWord() {

    }

    @Test
    public void testIllegalWordNotPlacedIfRandomLetterAddedToEndOfWord() {

    }

    @Test
    public void testPlaceWordIfSpaceBetween() {

    }

    @Test
    public void testPlaceThirdWordUsingLettersFromTwoWordsOnBoard() {

    }

    @Test
    public void testPlaceWordSurroundedByLettersConfigurationA() {

    }

    @Test
    public void testPlaceWordSurroundedByLettersConfigurationB() {

    }
}