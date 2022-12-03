import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * The TrayController controls the current player's letter tray.
 */
public class TrayController implements ActionListener, Serializable {

    private Game gameModel;

    private  JButton button;

    /**
     * Constructor for class LetterTray.
     *
     * @param gameModel the Model for this controller.
     */
    public TrayController(Game gameModel){
        this.gameModel = gameModel;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        button = (JButton) e.getSource();
        gameModel.selectTrayValue(button.getText().toUpperCase(), Integer.parseInt(e.getActionCommand()));
    }
}
