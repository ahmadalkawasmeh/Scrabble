import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class UndoRedoTest {

    File file;
    Game game_LoadIn, game_PlayMove;



    @Before
    public void setUp() throws Exception {
        game_LoadIn = new Game(2, 0, null);
        game_PlayMove = new Game(2, 0, null);

        file = new File("savedGame.txt");
    }


    @After
    public void tearDown(){
        if(game_PlayMove != null){
            game_PlayMove.quit();
        }
        if(game_LoadIn != null){
            game_LoadIn.quit();
        }

    }


    @Test
    public void testUndo() throws Exception {
        game_PlayMove.initializeGamePlay();
        game_PlayMove.play("TEST H7");

        game_PlayMove.saveGame(file);
        game_PlayMove.play("WATER 5H");

        game_PlayMove.undo();
        game_LoadIn = Game.importGameFile(file);
        game_LoadIn.loadGame();

        assertTrue(game_PlayMove.getBoard().equals(game_LoadIn.getBoard()));

    }

    @Test
    public void testRedo() throws Exception {
        game_PlayMove.initializeGamePlay();
        game_PlayMove.play("TEST H7");

        game_PlayMove.play("WATER 5H");
        game_PlayMove.saveGame(file);

        game_PlayMove.undo();
        game_PlayMove.redo();
        game_LoadIn = Game.importGameFile(file);
        game_LoadIn.loadGame();

        assertTrue(game_PlayMove.getBoard().equals(game_LoadIn.getBoard()));

    }




}