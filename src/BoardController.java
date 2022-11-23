import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


/**
 * The BoardController is used to control the grid of buttons on the board in the
 * ScrabbleScrabbleFrame view class.  This only includes the buttons that letters
 * can be placed upon.
 */
public class BoardController implements ActionListener {

    private Game gameModel;
    private String[] coordinates;


    /**
     * Constructor for class BoardController.
     *
     * @param gameModel The model the GUI will be updated based on.
     */
    public BoardController(Game gameModel) {
        this.gameModel = gameModel;
    }


    @Override
    public void actionPerformed(ActionEvent e){
        coordinates = e.getActionCommand().split(" ");
        ArrayList<Integer> numCoordinates = new ArrayList<>();
        numCoordinates.add(Integer.parseInt(coordinates[0]));
        numCoordinates.add(Integer.parseInt(coordinates[1]));
        gameModel.selectBoardValue(numCoordinates);
    }
}
