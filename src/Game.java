import java.util.List;
import java.util.Stack;

public class Game {
    private Board board;
    private static List<Player> players;
    private Player currentPlayer;
    private Stack<Round> roundHistory;

    public Game(int numPlayers){
        initializeBoard();
        initializePlayers();
        initializeLetterBag();

        while(playRound());

    }

    private void initializeBoard(){

    }

    private void initializeLetterBag(){

    }

    private void initializePlayers(){

    }

    public boolean playRound(){
        return true;
    }

    public void nextPlayer(){

    }

    public void getPlayerScores(){

    }
}
