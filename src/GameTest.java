import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class GameTest {

    File file;
    Game game1, game2;

    @Before
    public void setUp() throws Exception {
        game1 = new Game(2, 1, fileName); // Initialize a game with 1 AI and 1 user player
        game1.initializeGamePlay();
        game1.play(game1.currentPlayer.getNextAIMove());
        game1.play(game1.currentPlayer.getNextAIMove());
        game1.play(game1.currentPlayer.getNextAIMove());

        file = new File("savedGame.txt");
    }

    @Test
    public void testSerialize() throws IOException, ClassNotFoundException {
        game1.saveGame(file);
        game2 = Game.importGameFile(file);
        game2.loadGame();

        assertTrue(game1.equals(game2));
    }
}