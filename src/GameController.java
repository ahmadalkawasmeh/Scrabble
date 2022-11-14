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
            System.out.println("PLACE HIT");
            model.placeWord();
        }
        if(e.getActionCommand().equals("PASS")){
            //System.out.println("PASS HIT");
            model.playPass();
        }
        if(e.getActionCommand().equals("RESET")){
            System.out.println("RESET HIT");
            model.reset();
        }
        if(e.getActionCommand().equals("SWAP")){
            //System.out.println("SWAP HIT");
            model.swapNoParameters(true);
        }
        if(e.getActionCommand().equals("GO")){
            //System.out.println("GO HIT");
            model.swapNoParameters(false);
        }
        if(e.getActionCommand().equals("PASS")){
            System.out.println("PASS HIT");
            model.playPass();
        }

    }
}
