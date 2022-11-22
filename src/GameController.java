import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The GameController is used in ScrabbleScrabble to control the list of possible commands.
 * These commands include PLACE WORD, SWAP, PASS, RESET, and GO (related to SWAP).
 */
public class GameController implements ActionListener {

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
        if(e.getActionCommand().equals("PLACE")){
            model.placeWord();
        }
        if(e.getActionCommand().equals("PASS")){
            model.swapNoParameters(false);
        }
        if(e.getActionCommand().equals("RESET")){
            model.reset();
        }
        if(e.getActionCommand().equals("SWAP")){
            model.swapNoParameters(true);
        }
        if(e.getActionCommand().equals("GO")){
            model.swapNoParameters(false);
        }
        if(e.getActionCommand().equals("AI")){
            model.play("test");
        }
    }
}
