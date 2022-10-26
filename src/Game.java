import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Game {
    private Board board;
    public static List<Player> players;
    private Player currentPlayer;
    private Round currentRound;
    private static LetterBag letterBag = new LetterBag(); // the shared LetterBag that Players draw letters from
    private Stack<Round> roundHistory; // will be used for undo/redo in the future
    private Dictionary dictionary;
    private Parser parser = new Parser();
    private boolean finished;
    private int zeroScoreTurns = 0; // Track for a game-ending condition


    /**
     * Initializes all aspects and then starts the game.
     * Initializes the Players, a history of rounds, the dictionary and the
     * board.  Then begins the first turn of the game.
     *
     * @param numPlayers The number of Players in this game.
     */
    public Game(int numPlayers){
        if(numPlayers < 2 || numPlayers > 4){throw new InvalidParameterException("Invalid number of players.");}

        players = new ArrayList<Player>();
        roundHistory = new Stack<Round>();
        dictionary = new Dictionary();

        initializeBoard();
        initializePlayers(numPlayers);

        //initializeLetterBag();

        //while(playRound()); //? fix later

        finished = false;
        play();
    }


    /**
     * Runs the game until the game has finished.  The game starts
     * with the first player's turn, then switches players at the
     * end of each turn.
     */
    public void play(){
        currentPlayer = players.get(0);

        while(! finished){

            this.output();
            Move move = parser.getInput();
            processMove(move);

            nextPlayer();
        }
    }


    /**
     * Carries out the desired command that the user has input.
     * Developed by: James Grieder
     *
     * @param move The command that the user has input.
     */
    public void processMove(Move move) {

        if (move.getSecondCommandWord() == null) { // Single word commands
            if (move.getFirstCommandWord().equals("QUIT")) {
                quit();
            } else if (move.getFirstCommandWord().equals("PASS")) {
                zeroScoreTurns += 1;
            }
        }
        // Beginning of multiple word commands
        else if (move.getFirstCommandWord().equals("SWAP"))
        {swap(move.getSecondCommandWord());}

         else { // Place a word on the board
            processWord(move);
        }
    }


    /**
     * Converts a user input Move to a Word object.
     * Developed by: James Grieder
     *
     * @param move The command that the user has input.
     * @return the Word that will be placed on the board.
     */
    public Word processWord(Move move) {

        Word word = new Word(move.getFirstCommandWord(), move.getSecondCommandWord());

        if (this.checkWord(word) /*&& checkLegalWordPosition(word)*/) // removed for now
        {
            zeroScoreTurns = 0; // Reset counter if a word is placed

            board.addWordToBoard(word);

            currentPlayer.updateScore(word.wordScore());  //updates score
            currentPlayer.removeLetters( new ArrayList<>( List.of(word.getWord().split("")))); //removes used letters from the players tray
            currentPlayer.fillTray();  //fills player tray
        }
        return word;
    }


    /**
     * Quits the game.
     * Developed by: James Grieder
     */
    public void quit() {
        finished = true;
    }


    /**
     * Puts letters from the current player's tray back to the LetterBag,
     * and draws new letters to the player's tray.
     * Developed by: James Grieder
     *
     * @param letters The letters that will be put back in the LetterBag
     */
    public void swap(String letters) {

        ArrayList<String> swapList = new ArrayList<>(); // convert letters to an ArrayList format
        for (int i = 0; i < letters.length(); i++)
        {
            swapList.add("" + letters.charAt(i));
        }
        currentPlayer.removeLetters(swapList); // remove the specified letters
        currentPlayer.fillTray(); // refill the player's tray
    }


    /**
     * Checks if word is found in the Player's Tray.
     * @param word The word to check for in the Player's Tray.
     * @return true if the word is present in the Player's Tray, false otherwise.
     */
    private boolean checkWord(Word word) {
        ArrayList<String> letters = new ArrayList<>(List.of(word.getWord().split("")));

        return currentPlayer.checkInTray(letters) && dictionary.lookupDictionary(word.getWord().toLowerCase());
    }


    /*                              LOGIC NEEDED FOR CHECKING POSITION HAVENT ACTUALLY IMPLEMENTED IT YET
    private boolean checkLegalWordPosition(Word word){

        if(word.isHorizontal()){

            return  (word.findWordPosition().get(0) + word.length() <= 15);

        }

        return  (word.findWordPosition().get(1) + word.length() <= 15);

    }
    */


    /**
     * Gets the LetterBag shared by all Player's in the game.
     *
     * @return The game's LetterBag.
     */
    public static LetterBag getLetterBag()
    {
        return letterBag;
    }


    /**
     * Initializes the game Board.
     */
    private void initializeBoard(){
        board = new Board();
    }


    /**
     * Initializes the Players of the Game.
     *
     * @param numPlayers
     */
    private void initializePlayers(int numPlayers){
        for(int i = 0; i < numPlayers; i++){

            Player player = new Player("Player " + (i + 1));

            players.add(player);

        }
    }


    /**
     * Checks the ending game conditions.  The game ends if the LetterBag is empty
     * and the current Player has no letters to place, or if Players have passed
     * their turns 6 consecutive times without scoring any points.
     *
     * @return true if the game will continue, false otherwise.
     */
    public boolean playRound(){
        nextPlayer();
        currentRound = new Round(currentPlayer);
        if((letterBag.lettersLeft() == 0 && currentRound.emptyTray()) || zeroScoreTurns == 6){
            return false;
        }
        return true;
    }


    /**
     * Updates the current Player for the next round.
     */
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


    /**
     * Prints the scores of all Player's to the console.
     */
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
     * Prints the current game status to the console, including the board,
     * player scores, and the current player's tray.
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
