import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BoardController implements ActionListener {

    private Game gameModel;
    private String[] coordinates;



    public BoardController(Game gameModel) {

        this.gameModel = gameModel;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        coordinates= e.getActionCommand().split(" ");
        ArrayList<Integer> numCoordinates = new ArrayList<>();
        numCoordinates.add(Integer.parseInt(coordinates[0]));
        numCoordinates.add(Integer.parseInt(coordinates[1]));
        gameModel.selectBoardValue(numCoordinates);
    }



}
