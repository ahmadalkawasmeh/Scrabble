import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Game {
    private Board board;
    public static List<Player> players;
    private Player currentPlayer;
    private Round currentRound;
    private static LetterBag letterBag = new LetterBag();    //since its static we can just initialize it here and be done with it makes testing easier too
    private Stack<Round> roundHistory;
    private int passCount = 0;

    public Game(int numPlayers){
        if(numPlayers < 2 || numPlayers > 4){throw new InvalidParameterException("Invalid number of players.");}

        players = new ArrayList<Player>();
        roundHistory = new Stack<Round>();

        initializeBoard();
        initializePlayers(numPlayers);
        //initializeLetterBag();

        while(playRound());
    }

    public static LetterBag getLetterBag() {
        return letterBag;

    }

    private void initializeBoard(){

    }

    /*
    private void initializeLetterBag(){
        letterBag = new LetterBag();
    }
    */
    private void initializePlayers(int numPlayers){
        for(int i = 0; i < numPlayers; i++){

        }
    }

    public boolean playRound(){
        nextPlayer();
        currentRound = new Round(currentPlayer);
        if((letterBag.lettersLeft() == 0 && currentRound.emptyTray()) || passCount == 6){
            return false;
        }
        return true;
    }

    public void nextPlayer(){

    }

    public void getPlayerScores(){

    }

    public void output(){
        board.toString();
    }
}
