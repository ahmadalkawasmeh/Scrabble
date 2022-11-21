import java.util.EventObject;

/**
 * The interface for ScrabbleScrabbleView.  Any class that is providing a view for the game
 * of scrabblescrabble should implement this interface.
 */
public interface ScrabbleScrabbleView {

    public void update(GameEvent e);

    String getBlankState();
}
