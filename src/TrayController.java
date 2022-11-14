import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TrayController implements ActionListener {

    private Game gameModel;
    private String[] trayButtonInfo;


    private  JButton button;

    public TrayController(Game gameModel){
        this.gameModel = gameModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        button = (JButton) e.getSource();
        gameModel.selectTrayValue(button.getText().toUpperCase(), Integer.parseInt(e.getActionCommand()));
    }
}
