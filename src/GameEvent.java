import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

public class GameEvent extends EventObject {

    private Game source;

    private Player currentPlayer;
    private final boolean currentPlayerIsAI;
    private String trayValues;
    private String[][] usedSquares;
    private String currentSelectedTrayValue;
    private ArrayList<Integer> currentSelectedBoardValue;
    private int trayNumPos;
    private boolean placeCurrentBuildingWord;
    private ArrayList<Integer> startingWordPos;
    private int lengthOfWordBeingBuilt;
    private boolean swapState;
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
    public GameEvent(Object source, Player currentPlayer, ArrayList<Player> playerList, String trayValues,
                     String[][] usedSquares, String currentSelectedTrayValue,
                     ArrayList<Integer> currentSelectedBoardValue,  int trayNumPos, boolean placeCurrentBuildingWord,
                     ArrayList<Integer> startingWordPos, int lengthOfWordBeingBuilt, boolean swapState) {
        
        super(source);
        this.currentPlayer = currentPlayer;
        this.currentPlayerIsAI = currentPlayer.isAIPlayer();
        this.trayValues = trayValues;
        this.usedSquares = usedSquares;
        this.currentSelectedTrayValue = currentSelectedTrayValue;
        this.currentSelectedBoardValue = currentSelectedBoardValue;
        this.trayNumPos = trayNumPos;
        this.placeCurrentBuildingWord = placeCurrentBuildingWord;
        this.startingWordPos = startingWordPos;
        this.lengthOfWordBeingBuilt = lengthOfWordBeingBuilt;
        this.playerList = playerList;
        this.swapState = swapState;
    }

    /**
     * @return The squares on the board.
     */
    public String[][] getUsedSquares() {
        return usedSquares;
    }

    /**
     * @return The letters in the current Player's tray
     */
    public String getTrayValues() {
        return trayValues;
    }

    /**
     * @return The Player whose turn it is
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * @return The list of Players currently playing the game
     */
    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    /**
     * @return The letter that has been selected from the Player's tray
     */
    public String getCurrentSelectedTrayValue() {
        return currentSelectedTrayValue;
    }

    /**
     * @return
     */
    public ArrayList<Integer> getCurrentSelectedBoardValue(){
        return currentSelectedBoardValue;
    }

    /**
     * @return true if the current player has initiated a swapState to swap some or all of their letters
     */
    public boolean getSwapState() {
        return swapState;
    }

    /**
     * @return The numerical index of the currently selected tray value
     */
    public int getTrayNumPos() {
        return trayNumPos;
    }

    /**
     * @return true if the current Player is an AI Player, false otherwise
     */
    public boolean getCurrentPlayerIsAI() {
        return currentPlayerIsAI;
    }
}
