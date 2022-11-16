import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack; // for milestone 3

/**
 * Game represents the game of scrabblescrabble.  The game simulates a mock version of the Scrabble board game that
 * alternates between 2 players.  The game accepts user input via the keyboard.  The game is currently incomplete.
 * Players can add words to the board, pass their turn, swap their pieces with the letterbag, and quit the game.
 * Developed by Ibtasam & James & Daniel
 */
public class Game {

    private ArrayList<ScrabbleScrabbleView> views;
    private Board board;
    public static ArrayList<Player> players;
    private Player currentPlayer;
    // private Round currentRound; // will be used in the future
    private static LetterBag letterBag = new LetterBag(); // the shared LetterBag that Players draw letters from
    //private Stack<Round> roundHistory; // will be used for undo/redo in the future
    public static Dictionary dictionary;
    private Parser parser = new Parser();
    private boolean finished;
    private int zeroScoreTurns = 0; // Track for a game-ending condition

    private String currentSelectedTrayValue;

    private ArrayList<Integer> currentSelectedBoardValue;

    private int trayNumPos;

    private boolean placeCurrentBuildingWord;

    private ArrayList<Integer> startingWordPos;

    private String currentWord;

    private int lengthOfWordBeingBuilt;

    private String input;

    private boolean resetBoard = true;

    private boolean swapState;

    private String lettersToSwap;


    /**
     * Initializes all aspects and then starts the game.
     * Initializes the Players, a history of rounds, the dictionary and the
     * board.  Then begins the first turn of the game.
     * Developed by Daniel
     *
     * @param numPlayers The number of Players in this game.
     */
    public Game(int numPlayers){
        if(numPlayers < 2 || numPlayers > 4){throw new InvalidParameterException("Invalid number of players.");}

        players = new ArrayList<Player>();
        views = new ArrayList<>();
        // roundHistory = new Stack<Round>(); // for the future
        dictionary = new Dictionary();

        initializeBoard();
        initializePlayers(numPlayers);

        finished = false;

        lettersToSwap = "";
    }


    /**
     * Runs the game until the game has finished.  The game starts
     * with the first player's turn, then switches players at the
     * end of each turn.
     * Developed by Ibtasam
     */
    public void intializeGamePlay(){
        currentPlayer = players.get(0);
        resetViewValues();
        updateViews();
    }


    /**
     * Transforms input from the user into move object to be played.
     * Processes the move in the model, and then notifies the views.
     *
     * @param moveToPlay The string describing the move that the
     *                   current player will play.
     */
    public void play(String moveToPlay){

            this.output();

            Move move;
            if (currentPlayer.isAIPlayer()) {
                move = currentPlayer.getNextMove();
            } else { move = parser.getInput(moveToPlay); }
            processMove(move);
            nextPlayer();
            resetViewValues();
            updateViews();

            this.output();
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
                nextPlayer();
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

        if (this.checkWord(word))
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
        ArrayList<String> swapList = new ArrayList<>(List.of(letters.split(""))); // convert letters to an ArrayList format
        currentPlayer.swapLetters(swapList);
    }


    /**
     * Checks if word is found in the Player's Tray.
     * Developed by Ibtasam
     *
     * @param word The word to check for in the Player's Tray.
     * @return true if the word is present in the Player's Tray, false otherwise.
     */
    private boolean checkWord(Word word) {
        ArrayList<String> letters = new ArrayList<>(List.of(word.getWord().split("")));

        return currentPlayer.checkInTray(letters) && dictionary.lookupDictionary(word.getWord().toLowerCase()) && word.hasValidBounds();
    }


    /**
     * Gets the LetterBag shared by all Player's in the game.
     * Developed by Daniel
     *
     * @return The game's LetterBag.
     */
    public static LetterBag getLetterBag()
    {
        return letterBag;
    }


    /**
     * Initializes the game Board.
     * Developed by Daniel
     */
    private void initializeBoard(){
        board = new Board();
    }


    /**
     * Initializes the Players of the Game.
     * Developed by Daniel
     *
     * @param numPlayers the number of Players in the game.
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
    /* public boolean playRound(){
        nextPlayer();
        currentRound = new Round(currentPlayer);
        if((letterBag.lettersLeft() == 0 && currentRound.emptyTray()) || zeroScoreTurns == 6){
            return false;
        }
        return true;
    } */


    /**
     * Updates the current Player for the next round.
     * Developed by Daniel
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
     * Developed by Daniel
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
     * Developed by Daniel and Ibtasam
     */
    public void output(){
        System.out.println(board);
        getPlayerScores();
        System.out.println(currentPlayer.toString() +"'s Turn \n" + currentPlayer.toString() +"'s Tray: {  "+ currentPlayer.stringTray() +" }");
    }


    /**
     * Adds view to the list of views that this Game (the model) manages.
     * @param view The view to add.
     */
    public void addView(ScrabbleScrabbleView view){
        views.add(view);
    }


    /**
     * Notifies the views of updates to this game model.
     */
    public void updateViews(){
        for(ScrabbleScrabbleView view: views){
            view.update(new GameEvent(this, currentPlayer, players, currentPlayer.stringTray(), board.getUsedSquares(), currentSelectedTrayValue, currentSelectedBoardValue, trayNumPos, placeCurrentBuildingWord, startingWordPos, lengthOfWordBeingBuilt));
        }
    }

    /**
     * Updates the ongoing move based on the selected tray button.
     * For example, if the player is swapping letters, the list of letters to swap is updated.
     * Or, if the player is putting words on the board, the letter and position are updated.
     * @param trayValue the Letter selected in the tray (i.e. "A")
     * @param buttonNum the position of the selected letter in the Tray (i.e. position 3)
     */
    public void selectTrayValue(String trayValue, int buttonNum){
        System.out.print(swapState + "");
        if (swapState) {
            lettersToSwap += trayValue;

        } else {
            currentSelectedTrayValue = trayValue;
            trayNumPos = buttonNum;
        }
    }


    /**
     * Places a single letter on the board.  This is a temporary placement until the word is checked.
     *
     * @param boardValue The letter to place on the board.
     */
    public void selectBoardValue(ArrayList<Integer> boardValue){
        if(!currentSelectedTrayValue.equals(" ")){
            currentWord += currentSelectedTrayValue;
            currentSelectedBoardValue = boardValue;
            if(startingWordPos == null){
                startingWordPos = currentSelectedBoardValue;
            }
            updateViews();
        }
    }


    /**
     * Places a word on the board.
     */
    public void placeWord (){
        String wordCoordinate;
        Integer y = startingWordPos.get(0) + 1;
        if(!currentSelectedTrayValue.equals(" ")){
            if(dictionary.lookupDictionary(currentWord.toLowerCase())){
                if (startingWordPos.get(0) == currentSelectedBoardValue.get(0)){
                    //System.out.println(currentWord +" "+(y + Letters.values()[startingWordPos.get(1)].toString()));
                     play( currentWord +" "+(y + Letters.values()[startingWordPos.get(1)].toString()));
                }
                else if(startingWordPos.get(1) == currentSelectedBoardValue.get(1)){

                     play(currentWord +" "+Letters.values()[startingWordPos.get(1)].toString() + y);
                }
            }
        }
    }


    /**
     * Resets values that the views will require between turns.
     */
    public void resetViewValues(){
        placeCurrentBuildingWord = false;
        currentSelectedTrayValue = " ";
        currentSelectedBoardValue = null;
        startingWordPos = null;
        currentWord = "";
        lengthOfWordBeingBuilt = 0;
        lettersToSwap = "";
        swapState = false;
    }


    /**
     * Implements the pass command.
     */
    public void playPass() {
        updateViews();
    }


    /**
     * Implements the reset command, resets the current word being constructed.
     */
    public void reset() {
        resetViewValues();
        updateViews();
    }


    /**
     * Implements the swap command.  Allows the user to add multiple letters to a swap command.
     *
     * @param toSwap The state of the player swapping.  True if the player is beginning a swap,
     *               false if the player is ending a swap.
     */
    public void swapNoParameters(boolean toSwap) {
        swapState = toSwap;

        if (!swapState) {
            this.play("SWAP " + lettersToSwap);
        }
        updateViews();
    }
}
