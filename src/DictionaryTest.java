/**
 * DictionaryTest tests the Dictionary class through the following tests
 * Developed by: James Grieder, Ahmad Alkawasmeh
 */


import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.*;

public class DictionaryTest {

    Dictionary d;

    @Before
    public void setUp() throws Exception {
        d = new Dictionary();
    }


    @Test
    public void testDictionaryConstructor() {

        assertTrue(d != null);
    }


    @Test
    public void testDictionaryContainsALegalWord1() {
        // Assert that d contains "StringOfALegalWord"

        String word1 = "car";
        assertTrue(d.lookupDictionary(word1));
    }


    @Test
    public void testDictionaryContainsALegalWord2() {
        // Assert that d contains "StringOfALegalWord"

        String word2 = "ace";
        assertTrue(d.lookupDictionary(word2));
    }


    @Test
    public void testDictionaryDoesNotContainAnIllegalWord1() {
        // AssertFalse that d contains "StringOfAIllegalWord"

        String word1 = "absx";
        assertFalse(d.lookupDictionary(word1));
    }


    @Test
    public void testDictionaryDoesNotContainAnIllegalWord2() {
        // AssertFalse that d contains "StringOfAIllegalWord"

        String word2 = "milde";
        assertFalse(d.lookupDictionary(word2));
    }
}