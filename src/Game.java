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

    private Dictionary dictionary;

    private Parser parser = new Parser();

    public Game(int numPlayers){
        if(numPlayers < 2 || numPlayers > 4){throw new InvalidParameterException("Invalid number of players.");}

        players = new ArrayList<Player>();
        roundHistory = new Stack<Round>();
        dictionary = new Dictionary();

        initializeBoard();
        initializePlayers(numPlayers);

        //initializeLetterBag();

        //while(playRound()); //? fix later

        play();
    }

    /**
     * Runs game with while loop
     */
    public void play(){
        currentPlayer = players.get(0);


        boolean finished = false;

        while(! finished){

            this.output();

            Word word = parser.getInput();

            if(this.checkWord(word) /*&& checkLegalWordPosition(word)*/){   //removed for now

                board.addWordToBoard(word);

                currentPlayer.updateScore(word.wordScore());                                //updates score
                currentPlayer.removeLetters( new ArrayList<>( List.of(word.getWord().split(""))));  //removes used letters
                currentPlayer.fillTray();                                                   //fills player tray

            }
            nextPlayer();


        }



    }

    /**
     * checks if tray can form word
     * @param word
     * @return  boolean of if word is present in tray
     */
    private boolean checkWord(Word word) {
        ArrayList<String> letters = new ArrayList<>(List.of(word.getWord().split("")));

        return currentPlayer.checkInTray(letters); //&& dictionary.lookupDictionary(word.getWord());         Dictionary look up is not working

    }


    /*                              LOGIC NEEDED FOR CHECKING POSITION HAVENT ACTUALLY IMPLEMENTED IT YET
    private boolean checkLegalWordPosition(Word word){

        if(word.isHorizontal()){

            return  (word.findWordPosition().get(0) + word.length() <= 15);

        }

        return  (word.findWordPosition().get(1) + word.length() <= 15);

    }
    */


    public static LetterBag getLetterBag() {
        return letterBag;

    }

    private void initializeBoard(){
        board = new Board();
    }

    /*
    private void initializeLetterBag(){
        letterBag = new LetterBag();
    }
    */
    private void initializePlayers(int numPlayers){
        for(int i = 0; i < numPlayers; i++){

            Player player = new Player("Player " + (i + 1));

            players.add(player);

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
        for (int i = 0; i < players.size(); i++){
            if(i == players.size() - 1){
                currentPlayer = players.get(0);
                return;
            }
            if(players.get(i).toString().equals(currentPlayer.toString())){
                currentPlayer = players.get(i+1);
                return;
            }
        }
    }

    public void getPlayerScores(){
        System.out.println("--------------");
        System.out.println("Player scores:");
        System.out.println("");
        for (Player p : players){
            System.out.println(p.toString() + ": " + p.getScore());
        }
        System.out.println("--------------");
    }

    /**
     * Prints in console the board, player, and player tray
     */
    public void output(){
        System.out.println(board);
        getPlayerScores();
        System.out.println(currentPlayer.toString() +"'s Turn \n" + currentPlayer.toString() +"'s Tray: {  "+ currentPlayer.stringTray() +" }");
    }

    public static void main(String[] args) {
        Game game =  new Game(2);


    }



}
