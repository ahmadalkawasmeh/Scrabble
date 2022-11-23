import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Game represents the game of scrabblescrabble.  The game simulates a mock version of the Scrabble board game that
 * alternates between 2 players.  The game accepts user input via the keyboard.  The game is currently incomplete.
 * Players can add words to the board, pass their turn, swap their pieces with the letterbag, and quit the game.
 * Developed by Ibtasam & James & Daniel
 */
public class Game {

    private ArrayList<ScrabbleScrabbleView> views;
    private static Board board;
    public static ArrayList<Player> players;
    private Player currentPlayer;
    private static LetterBag letterBag = new LetterBag(); // the shared LetterBag that Players draw letters from
    // private Round currentRound;
    // will be used in the future//private Stack<Round> roundHistory; // will be used for undo/redo in the future
    public static Dictionary dictionary;
    private Parser parser = new Parser();
    private boolean finished;
    private int zeroScoreTurns = 0; // Track for a game-ending condition
    private String currentSelectedTrayValue;
    private ArrayList<Integer> currentSelectedBoardValue;
    private int trayNumPos;
    private boolean placeCurrentBuildingWord;
    private ArrayList<Integer> startingWordPos;
    private ArrayList<Integer> endingWordPos;
    private String currentWord;
    private int lengthOfWordBeingBuilt;
    private String input;
    private boolean resetBoard = true;
    private boolean swapState;
    private String lettersToSwap;
    private HashMap<Integer, String> coordinatesOfWordToPlace;
    private boolean blankPlaced;
    private AIHelper AI;


    /**
     * Initializes all aspects and then starts the game.
     * Initializes the Players, a history of rounds, the dictionary and the
     * board.  Then begins the first turn of the game.
     * Developed by Daniel
     *
     * @param numPlayers The number of Players in this game.
     */
    public Game(int numPlayers, int numAIPlayers){
        if(numPlayers < 2 || numPlayers > 4){throw new InvalidParameterException("Invalid number of players.");}

        players = new ArrayList<Player>();
        views = new ArrayList<>();
        // roundHistory = new Stack<Round>(); // for the future
        dictionary = new Dictionary();
        initializeBoard();
        AI = new AIHelper(board);
        initializePlayers(numPlayers, numAIPlayers);

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
        coordinatesOfWordToPlace = new HashMap<>();
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
                String nextMove = currentPlayer.getNextAIMove();
                move = parser.getInput(nextMove);
                System.out.println("move AI is playing: " + move);
            } else { move = parser.getInput(moveToPlay); }
            if (currentPlayer.isAIPlayer()) { System.out.println("\n\n" + move.toString()); }
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

             if (currentPlayer.isAIPlayer()) {
                 processWordForAI(move);
             } else {
                 processWord(move);
             }
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

        System.out.println("get word: " + word.getWord());
        System.out.println("get word: " + word.getLetterPositions());
        System.out.println("Check word: " + checkWord(word));


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


    private Word processWordForAI(Move move) {

        Word word = new Word(move.getFirstCommandWord(), move.getSecondCommandWord());

        zeroScoreTurns = 0; // Reset counter if a word is placed

        board.addWordToBoard(word);

        currentPlayer.updateScore(word.wordScore());  //updates score
        currentPlayer.removeLetters( new ArrayList<>( List.of(word.getWord().split("")))); //removes used letters from the players tray
        currentPlayer.fillTray();  //fills player tray

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

        return /* currentPlayer.checkInTray(letters) &&*/ dictionary.lookupDictionary(word.getWord().toLowerCase()) && word.hasValidBounds();
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
     * Players will either be user players or AI players.  The total number of
     * players may be between 2 and 4.  The total number of AI players may
     * be between 0 and the total number of players - 1.
     *
     * Developed by Daniel (Milestone 1) and updated by James (Milestone 3)
     *
     * @param numPlayers the total number of desired Players (both user and AI)
     * @param numAIPlayers the desired number of AI players
     */
    private void initializePlayers(int numPlayers, int numAIPlayers){

        // Set to 2 total players if the user has input an invalid number of players
        if (numPlayers < 2 || numPlayers > 4) {
            numPlayers = 2;
        }

        // At least one player must be a user
        if (numAIPlayers >= numPlayers) {
            numAIPlayers = numPlayers - 1;
        }

        int numUserPlayers = numPlayers - numAIPlayers;

        // Initialize user players
        for (int i = 0; i < numUserPlayers; i++) {
            Player player = new Player("Player " + (i + 1), false);
            players.add(player);
        }

        // Initialize AI players
        for (int i = 0; i < numAIPlayers; i++) {
            Player player = new Player("Player " + (i + 1 + numUserPlayers) + " (AI)", true);
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
            view.update(new GameEvent(this, currentPlayer, players, currentPlayer.stringTray(),
                    board.getUsedSquares(), currentSelectedTrayValue, currentSelectedBoardValue,
                    trayNumPos, placeCurrentBuildingWord, startingWordPos, lengthOfWordBeingBuilt, swapState));
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
        if(currentSelectedTrayValue.equals("__")){
            currentSelectedTrayValue = fetchBlankState();
        }
        if(!currentSelectedTrayValue.equals(" ")){
            //currentWord += currentSelectedTrayValue; //address this
            currentSelectedBoardValue = boardValue;
            if(startingWordPos == null){
                startingWordPos = currentSelectedBoardValue;
                endingWordPos = currentSelectedBoardValue;
            }

            coordinatesOfWordToPlace.put((currentSelectedBoardValue.get(0) * 10 + currentSelectedBoardValue.get(1)), currentSelectedTrayValue);

            if(startingWordPos.get(0) > currentSelectedBoardValue.get(0) || startingWordPos.get(1) > currentSelectedBoardValue.get(1)){
                startingWordPos = currentSelectedBoardValue;
            }

            if( endingWordPos.get(0) < currentSelectedBoardValue.get(0) || endingWordPos.get(1) < currentSelectedBoardValue.get(1)){
                endingWordPos = currentSelectedBoardValue;
            }

            updateViews();
        }
    }


    private String fetchBlankState(){
        String letter = "";
        for(ScrabbleScrabbleView view: views){
            letter = view.getBlankState();
            if(letter != null)
                return letter;
        }
        return letter;
    }


    /**
     * Places a word on the board.
     */
    public void placeWord (){
        Word wordtoPlay;
        Word wordToRemove;

        Integer y = startingWordPos.get(0) + 1;
        ArrayList<String> removableAndPlayableWordList = board.formWordUsingBoardValues(startingWordPos, endingWordPos, coordinatesOfWordToPlace);
        String currentWord = removableAndPlayableWordList.get(0);

        String wordToRemoveString = removableAndPlayableWordList.get(1);

        if(!currentSelectedTrayValue.equals(" ")){



            if (startingWordPos.get(0) ==  endingWordPos.get(0)){

                wordtoPlay = new Word(currentWord, (y + Letters.values()[startingWordPos.get(1)].toString()));
                wordToRemove = new Word(wordToRemoveString, (y + Letters.values()[startingWordPos.get(1)].toString()));

                board.addWordToBoard(wordtoPlay);

                if(board.checkWordOnBoard(wordtoPlay) && board.isWordConnectedToCenter(wordtoPlay)){

                    board.removeLettersFromBoard(wordToRemove);
                    play( currentWord +" "+(y + Letters.values()[startingWordPos.get(1)].toString()));

                }else{ board.removeLettersFromBoard(wordToRemove);}


            }

            else if(startingWordPos.get(1) == endingWordPos.get(1)){
                wordtoPlay = new Word(currentWord, Letters.values()[startingWordPos.get(1)].toString() + y);
                wordToRemove = new Word(wordToRemoveString, Letters.values()[startingWordPos.get(1)].toString() + y);

                board.addWordToBoard(wordtoPlay);

                if(board.checkWordOnBoard(wordtoPlay) && board.isWordConnectedToCenter(wordtoPlay)){

                    board.removeLettersFromBoard(wordToRemove);
                    play(currentWord +" "+Letters.values()[startingWordPos.get(1)].toString() + y);

                }else{board.removeLettersFromBoard(wordToRemove);}


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
        endingWordPos = null;
        if (coordinatesOfWordToPlace != null) {
            coordinatesOfWordToPlace.clear();
        }
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


    /**
     * Gets the board for the current Game.
     *
     * @return The board being used for this Game.
     */
    public static Board getBoard() {
        return board;
    }

    public Board.scores getSquareSpecialty(int x, int y){
        return (Board.scores) board.getSquareScore(x,y);
    }


    public void addBlankToTray(){
        currentPlayer.addLetter(Letters.__);
    }


    public void playBlank(String letter,ArrayList<Integer> coordinates){
        ArrayList<String> blank = new ArrayList<String>();
        blank.add("");
        if(currentPlayer.checkInTray(blank)){
            currentSelectedTrayValue = letter;
            currentSelectedBoardValue = coordinates;
            selectBoardValue(coordinates);
        }
    }
}
