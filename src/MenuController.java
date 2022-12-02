import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

/**
 * The MenuController is used in ScrabbleScrabble to control the menuBar
 * located above the game.  The menuBar consists of several JMenuItems
 * located in different JMenus (File, Game History Options, and Help).
 */
public class MenuController implements ActionListener, Serializable {

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

        model.resetTurn();
        if(e.getActionCommand().equals("SAVE")){
            String fileName = JOptionPane.showInputDialog("Please input a file name to export to");
            try {
                model.saveGame(new File(fileName));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }



        if(e.getActionCommand().equals("LOAD")) {
            String fileName = JOptionPane.showInputDialog("Please input a file name to import from");
            try {
                Game g = Game.importGameFile(new File(fileName));
                g.loadGame();
                model = g;
                model.addView(view);
                model.updateViews();

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }


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
