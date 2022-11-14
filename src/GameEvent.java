import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

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


    private ArrayList<Player> playerList;

    /**
     * Constructs a prototypical Event.
     *
     * @param source                    the object on which the Event initially occurred
     * @param currentPlayer             the player that initiated the event
     * @param trayValues                the tray values of the player
     * @param usedSquares               the squares used
     * @param currentSelectedTrayValue  the currently selected letter from the player tray
     * @param currentSelectedBoardValue the currently selected board coordinate
     * @param trayNumPos                the position in the player tray of the current letter selected
     * @param placeCurrentBuildingWord  the status of building a word or not
     * @param startingWordPos           the starting position of the word to be placed
     * @param lengthOfWordBeingBuilt    the length of the word being built
     * @throws IllegalArgumentException if source is null
     */
    public GameEvent(Object source, Player currentPlayer, ArrayList<Player> playerList, String trayValues, String[][] usedSquares, String currentSelectedTrayValue, ArrayList<Integer> currentSelectedBoardValue, int trayNumPos, boolean placeCurrentBuildingWord, ArrayList<Integer> startingWordPos, int lengthOfWordBeingBuilt) {
        
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
        this.playerList = playerList;
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

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }
    public String getCurrentSelectedTrayValue() {
        return currentSelectedTrayValue;
    }

    public ArrayList<Integer> getCurrentSelectedBoardValue(){
        return currentSelectedBoardValue;
    }


}
