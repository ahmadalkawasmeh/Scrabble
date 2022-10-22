import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Game {
    private Board board;
    private static List<Player> players;
    private Player currentPlayer;
    private Round currentRound;
    private Stack<Round> roundHistory;

    public Game(int numPlayers){
        if(numPlayers < 2 || numPlayers > 4){throw new InvalidParameterException("Invalid number of players.");}

        players = new ArrayList<Player>();
        roundHistory = new Stack<Round>();

        initializeBoard();
        initializePlayers(numPlayers);
        initializeLetterBag();

        while(playRound());
    }

    private void initializeBoard(){

    }

    private void initializeLetterBag(){

    }

    private void initializePlayers(int numPlayers){
        for(int i = 0; i < numPlayers; i++){

        }
    }

    public boolean playRound(){
        return true;
    }

    public void nextPlayer(){

    }

    public void getPlayerScores(){

    }

    public void output(){
        board.output();
    }
}
