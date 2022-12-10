import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

/**
 * The GameController is used in ScrabbleScrabble to control the list of possible commands.
 * These commands include PLACE WORD, SWAP, PASS, RESET, and GO (related to SWAP).
 */
public class GameController implements ActionListener, Serializable {

    public enum gameCommands{

        PLACE, PASS, RESET, SWAP, GO, AI

    }

    private Game model;

    /**
     * Constructor for class GameController.
     *
     * @param model The model to control.
     */
    public GameController(Game model) {
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals(gameCommands.PLACE.name())){
            model.placeWord();
        }
        if(e.getActionCommand().equals(gameCommands.PASS.name())){
            model.swapNoParameters(false);
        }
        if(e.getActionCommand().equals(gameCommands.RESET.name())){
            model.reset();
        }
        if(e.getActionCommand().equals(gameCommands.SWAP.name())){
            model.swapNoParameters(true);
        }
        if(e.getActionCommand().equals(gameCommands.GO.name())){
            model.swapNoParameters(false);
        }
        if(e.getActionCommand().equals(gameCommands.AI.name())){
            model.play("test");
        }
    }
}
