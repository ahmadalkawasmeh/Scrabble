import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController implements ActionListener {

    private Game model;

    public GameController(Game model) {
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("PLACE")){
            model.placeWord();
        }
        if(e.getActionCommand().equals("PASS")){
            model.playPass();
        }
        if(e.getActionCommand().equals("RESET")){
            model.reset();
        }

    }
}
