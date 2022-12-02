import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.AfterClass;
import org.junit.BeforeClass;


@RunWith(Suite.class)
@SuiteClasses({ BoardTest.class,
                DictionaryTest.class,
                GameTest.class,
                LetterBagTest.class,
                PlayerTest.class,
                TrayTest.class,
                WordScoreTest.class})

public class AllTests {

    @BeforeClass
    public static void setUpClass() {}

    @AfterClass public static void tearDownClass() {}

}


