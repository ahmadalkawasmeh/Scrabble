import java.util.ArrayList;
import java.util.EventObject;

public class GameEvent extends EventObject {

    private Game source;

    private Player currentPlayer;

    private String trayValues;

    private String[][] usedSquares;

    private String currentSelectedTrayValue;

    private ArrayList<Integer> currentSelectedBoardValue;

    private int trayNumPos;

    private boolean placeCurrentBuildingWord;

    private ArrayList<Integer> startingWordPos;

    private int lengthOfWordBeingBuilt;

    /**
     * Constructs a prototypical Event.
     *
     * @param source                    the object on which the Event initially occurred
     * @param currentPlayer
     * @param trayValues
     * @param usedSquares
     * @param currentSelectedTrayValue
     * @param currentSelectedBoardValue
     * @param trayNumPos
     * @param placeCurrentBuildingWord
     * @param startingWordPos
     * @param lengthOfWordBeingBuilt
     * @throws IllegalArgumentException if source is null
     */
    public GameEvent(Object source, Player currentPlayer, String trayValues, String[][] usedSquares, String currentSelectedTrayValue, ArrayList<Integer> currentSelectedBoardValue, int trayNumPos, boolean placeCurrentBuildingWord, ArrayList<Integer> startingWordPos, int lengthOfWordBeingBuilt) {

        super(source);
        this.currentPlayer = currentPlayer;
        this.trayValues = trayValues;
        this.usedSquares = usedSquares;
        this.currentSelectedTrayValue = currentSelectedTrayValue;
        this.currentSelectedBoardValue = currentSelectedBoardValue;
        this.trayNumPos = trayNumPos;
        this.placeCurrentBuildingWord = placeCurrentBuildingWord;
        this.startingWordPos = startingWordPos;
        this.lengthOfWordBeingBuilt = lengthOfWordBeingBuilt;
    }

    public String[][] getUsedSquares() {
        return usedSquares;
    }

    public String getTrayValues() {
        return trayValues;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    public String getCurrentSelectedTrayValue() {
        return currentSelectedTrayValue;
    }

    public ArrayList<Integer> getCurrentSelectedBoardValue(){
        return currentSelectedBoardValue;
    }

    public int getTrayNumPos() {
        return trayNumPos;
    }

    public boolean isPlaceCurrentBuildingWord() {
        return placeCurrentBuildingWord;
    }

    public ArrayList<Integer> getStartingWordPos() {
        return startingWordPos;
    }

    public int getLengthOfWordBeingBuilt() {
        return lengthOfWordBeingBuilt;
    }
}
