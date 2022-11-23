import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The MenuController is used in ScrabbleScrabble to control the menuBar
 * located above the game.  The menuBar consists of several JMenuItems
 * located in different JMenus (File, Game History Options, and Help).
 */
public class MenuController implements ActionListener {

    private ScrabbleScrabbleView view;
    private Game model;

    /**
     * Constructor for class MenuController.
     *
     * @param model The model to control.
     */
    public MenuController(Game model, ScrabbleScrabbleView view) {
        this.model = model;
        this.view = view;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("NEW")){
            view.quitView();
            ScrabbleScrabbleFrame f = new ScrabbleScrabbleFrame();
        }

        /* FOR MILESTONE 4
        if(e.getActionCommand().equals("SAVE")){}
        if(e.getActionCommand().equals("LOAD")){}
         */


        if(e.getActionCommand().equals("QUIT")){
            view.quitView();
            System.exit(0);
        }

        /* FOR MILESTONE 4
        if(e.getActionCommand().equals("UNDO")){}
        if(e.getActionCommand().equals("REDO")){}
         */
    }
}
