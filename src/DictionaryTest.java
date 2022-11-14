import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

public class DictionaryTest {

    Dictionary d;

    @Before
    public void setUp() throws Exception {
        d = new Dictionary();
    }


    @Test
    public void testDictionaryConstructor() {
        // Assert true that d is not null
    }


    @Test
    public void testDictionaryContainsALegalWord() {
        String word = "WORD";
        assertTrue(d.lookupDictionary(word));
    }



    @Test
    public void testDictionaryContainsALegalWord2() {
        // Assert that d contains "stringofalegalword"
    }


    @Test
    public void testDictionaryDoesNotContainAnIlegalWord() {
        // AssertFalse that d contains "stringofalegalword"
    }


    @Test
    public void testDictionaryDoesNotContainAnIlegalWord2() {
        // AssertFalse that d contains "stringofalegalword"
    }
}