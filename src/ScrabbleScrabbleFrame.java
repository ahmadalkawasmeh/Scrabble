import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.util.ArrayList;

/**
 * ScrabbleScrabbleFrame is a GUI view for the game of ScrabbleScrabble.
 * This view is a part of the MVC pattern. This view displays the game title,
 * the game grid, a status message, the player's letter tray, a scoreboard,
 * and provides a panel for the user to input commands by selecting buttons
 * (place word, pass, skip, go).
 */
public class ScrabbleScrabbleFrame extends JFrame implements ScrabbleScrabbleView {

    private Container pane;
    private JButton[][] gameBoardButtons;
    private JButton[] letterTrayButtons;
    private JLabel[] playerNamesAndScores; // indices 0, 2, 4, 6 are player names, indices 1, 3, 5, 7 are player scores
    private JLabel currentPlayerLabel;
    private JButton placeWordButton, swapButton, passButton, goButton, resetButton, playAIMoveButton;
    private JLabel gameStatusMessage;
    private Game gameModel;


    /**
     * Constructor for ScrabbleScrabbleFrame.
     * Initializes all GUI elements.
     */
    public ScrabbleScrabbleFrame() {
        super("ScrabbleScrabble Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLayout(new BorderLayout());


        // Prompt the user for the total number of user and AI players
        String[] optionsForNumberOfPlayers = {"2", "3", "4"};
        Object input = JOptionPane.showInputDialog(this,"Select the total number of players (user and AI):",
                "Player Selection", JOptionPane.QUESTION_MESSAGE,null, optionsForNumberOfPlayers,"2");
        int numPlayers = Integer.parseInt((String)input);

        // Prompt the user for the number of AI players
        String[] optionsForNumberOfAIPlayers = {"0", "1", "2", "3"};
        input = JOptionPane.showInputDialog(this,"Select the number of AI players:",
                "AI Selection", JOptionPane.QUESTION_MESSAGE,null, optionsForNumberOfAIPlayers,"1");
        int numAIPlayers = Integer.parseInt((String)input);

        Game gameModel = new Game(numPlayers, numAIPlayers);
        BoardController boardController = new BoardController(gameModel);
        TrayController trayController = new TrayController(gameModel);
        GameController gameController = new GameController(gameModel);
        MenuController menuController = new MenuController(gameModel, this);
        gameModel.addView(this);

        pane = this.getContentPane();

        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        JMenuItem newGame = new JMenuItem("New Game");
        newGame.addActionListener(menuController);
        newGame.setActionCommand("NEW");
        fileMenu.add(newGame);
        JMenuItem saveGame = new JMenuItem("Save Game");
        saveGame.addActionListener(menuController);
        saveGame.setActionCommand("SAVE");
        fileMenu.add(saveGame);
        JMenuItem loadGame = new JMenuItem("Load Game");
        loadGame.addActionListener(menuController);
        loadGame.setActionCommand("LOAD");
        fileMenu.add(loadGame);
        JMenuItem quitGame = new JMenuItem("Quit Game");
        quitGame.addActionListener(menuController);
        quitGame.setActionCommand("QUIT");
        fileMenu.add(quitGame);

        JMenu gameHistoryOptions = new JMenu("Game History Options");
        menuBar.add(gameHistoryOptions);

        JMenuItem undoMenuItem = new JMenuItem("Undo");
        gameHistoryOptions.add(undoMenuItem);
        JMenuItem redoMenuItem = new JMenuItem("Redo");
        gameHistoryOptions.add(redoMenuItem);

        JMenu helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);

        JMenuItem viewGameInstructions = new JMenuItem("View Game Instructions");
        helpMenu.add(viewGameInstructions);
        JMenuItem viewReadme = new JMenuItem("View readme");
        helpMenu.add(viewReadme);

        JPanel logoPanel = new JPanel();
        JPanel scoreBoardPanel = new JPanel();
        JPanel centrePanel = new JPanel();
        JPanel gameBoardPanel = new JPanel();
        JPanel playerInputPanel = new JPanel();
        JPanel letterTrayPanel = new JPanel();

        pane.add(logoPanel, BorderLayout.NORTH);
        pane.add(scoreBoardPanel, BorderLayout.WEST);
        pane.add(centrePanel, BorderLayout.CENTER);
        pane.add(playerInputPanel, BorderLayout.EAST);

        centrePanel.setLayout(new BorderLayout());
        centrePanel.add(gameBoardPanel, BorderLayout.NORTH);
        centrePanel.add(letterTrayPanel, BorderLayout.SOUTH);

        // logoPanel

        logoPanel.setBorder(new BevelBorder(BevelBorder.RAISED, Color.BLACK, Color.DARK_GRAY));
        logoPanel.setBackground(new Color(162, 129, 40));
        JLabel logo = new JLabel("ScrabbleScrabble");
        logoPanel.add(logo);
        logo.setFont(new Font(Font.SERIF, Font.BOLD, 36));

        // scoreBoardPanel

        scoreBoardPanel.setLayout(new BorderLayout());

        JLabel scoreBoardLabel = new JLabel("Scoreboard");
        scoreBoardLabel.setFont(new Font(Font.SERIF, Font.BOLD, 22));
        scoreBoardPanel.add(scoreBoardLabel, BorderLayout.NORTH);

        JPanel playerScoresPanel = new JPanel();
        playerScoresPanel.setLayout(new GridLayout(1, 2));

        JPanel playerNameListPanel = new JPanel();
        playerNameListPanel.setLayout(new BoxLayout(playerNameListPanel, BoxLayout.Y_AXIS));
        JPanel playerScoreListPanel = new JPanel();
        playerScoreListPanel.setLayout(new BoxLayout(playerScoreListPanel, BoxLayout.Y_AXIS));

        playerNamesAndScores = new JLabel[8];
        for (int i = 0; i < 8; i++) {
            JLabel l;
            if (i % 2 == 0) {
                l = new JLabel(""); // label for player name
                l.setFont(new Font(Font.SERIF, Font.BOLD, 20));
                playerNameListPanel.add(l);
            } else {
                l = new JLabel(""); // label for player score
                l.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
                playerScoreListPanel.add(l);
            }
            playerNamesAndScores[i] = l;
        }
        scoreBoardPanel.add(playerScoresPanel, BorderLayout.CENTER);

        playerScoresPanel.add(playerNameListPanel);
        playerScoresPanel.add(playerScoreListPanel);

        scoreBoardPanel.setBackground(new Color(168,186,169));
        playerInputPanel.setBackground(new Color(168,186,169));
        playerScoresPanel.setBackground(new Color(168,186,169));
        playerNameListPanel.setBackground(new Color(168,186,169));
        playerScoreListPanel.setBackground(new Color(168,186,169));

        playerNameListPanel.setVisible(true);
        playerScoreListPanel.setVisible(true);

        scoreBoardPanel.setVisible(true);

        // gameBoardPanel

        gameBoardPanel.setLayout(new GridLayout(Board.SIZE, Board.SIZE));
        gameBoardPanel.setBackground(new Color(168,186,169));
        gameBoardButtons = new JButton[Board.SIZE][Board.SIZE]; // should add variable
        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {

                JButton b = new JButton(" ");
                gameBoardButtons[i][j] = b;
                b.setActionCommand(i + " " + j);
                b.addActionListener(boardController);
                gameBoardPanel.add(b);

                // remove click mechanism (colour changing)
                b.setBackground(new Color(228, 201, 128));
                b.setFont(new Font(Font.SERIF, Font.BOLD, 20));
            }
        }
        gameBoardButtons[7][7].setBackground(new Color(121, 80, 48));


        JPanel gameStatusPane = new JPanel();
        gameStatusMessage = new JLabel("Welcome to ScrabbleScrabble");
        gameStatusMessage.setFont(new Font(Font.SERIF, Font.PLAIN, 28));
        gameStatusMessage.setHorizontalAlignment(SwingConstants.CENTER);

        gameStatusPane.setLayout(new GridLayout(1,1));
        gameStatusPane.setBackground(new Color(162, 129, 40));
        gameStatusPane.setBorder(new BevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.DARK_GRAY));

        centrePanel.add(gameStatusPane, BorderLayout.CENTER);
        gameStatusPane.add(gameStatusMessage);


        // playerInputPanel

        playerInputPanel.setLayout(new BoxLayout(playerInputPanel, BoxLayout.Y_AXIS));

        currentPlayerLabel = new JLabel("Current Player: N/A");
        currentPlayerLabel.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        playerInputPanel.add(currentPlayerLabel);

        placeWordButton = new JButton("Place Word");
        placeWordButton.addActionListener(gameController);
        placeWordButton.setActionCommand("PLACE");
        placeWordButton.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        placeWordButton.setMaximumSize(new Dimension(200, 50));
        placeWordButton.setBackground(new Color(92, 206, 128));
        playerInputPanel.add(placeWordButton);

        swapButton = new JButton("Swap");
        swapButton.addActionListener(gameController);
        swapButton.setActionCommand("SWAP");
        swapButton.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        swapButton.setMaximumSize(new Dimension(200, 50));
        swapButton.setBackground(new Color(92, 206, 128));
        playerInputPanel.add(swapButton);

        passButton = new JButton("Pass");
        passButton.addActionListener(gameController);
        passButton.setActionCommand("PASS");
        passButton.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        passButton.setMaximumSize(new Dimension(200, 50));
        passButton.setBackground(new Color(92, 206, 128));
        playerInputPanel.add(passButton);

        resetButton = new JButton("Reset");
        resetButton.addActionListener(gameController);
        resetButton.setActionCommand("RESET");
        resetButton.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        resetButton.setMaximumSize(new Dimension(200, 50));
        resetButton.setBackground(new Color(92, 206, 128));
        playerInputPanel.add(resetButton);


        goButton = new JButton("GO");
        goButton.addActionListener(gameController);
        goButton.setActionCommand("GO");
        goButton.setFont(new Font(Font.SERIF, Font.PLAIN, 50));
        goButton.setBackground(new Color(67, 143, 95));
        playerInputPanel.add(goButton);
        goButton.setMaximumSize(new Dimension(200, 175));

        playAIMoveButton = new JButton("Play AI Move");
        playAIMoveButton.addActionListener(gameController);
        playAIMoveButton.setActionCommand("AI");
        playAIMoveButton.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        playAIMoveButton.setMaximumSize(new Dimension(200, 50));
        playAIMoveButton.setBackground(new Color(92, 206, 128));
        playAIMoveButton.setEnabled(false);
        playerInputPanel.add(playAIMoveButton);


        // letterTrayPanel

        letterTrayPanel.setLayout(new GridLayout(1, 7));
        letterTrayPanel.setBackground(new Color(168,186,169));
        letterTrayPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.GRAY, Color.GRAY));

        letterTrayButtons = new JButton[7];
        for (int i = 0; i < 7; i++) {
            JButton b = new JButton();
            letterTrayButtons[i] = b;
            b.setActionCommand(String.valueOf(i));

            b.setText(" ");
            b.setFont(new Font(Font.SERIF, Font.BOLD, 36));
            b.setBackground(new Color(228, 201, 128));
            letterTrayPanel.add(b);
            b.addActionListener(trayController);
        }
        gameBoardPanel.setVisible(true);
        letterTrayPanel.setVisible(true);
        centrePanel.setVisible(true);

        this.setVisible(true);
        gameModel.intializeGamePlay();
    }


    public String blankPrompt(){
        String letters[] = new String[26];
        for(int i = 0; i < 26; i++){
            char c = (char) (i+65);
            letters[i] = Character.toString(c);
        }

        Object input = (String) JOptionPane.showInputDialog(this,"Select a letter",
                "Input", JOptionPane.QUESTION_MESSAGE,null,letters,"a");

        if(input instanceof String || !((int) input == JOptionPane.CLOSED_OPTION || (int) input == JOptionPane.CANCEL_OPTION)){
            return (String) input;
        }
        return null;

    }


    @Override
    public void update(GameEvent e) {
        if(e.getCurrentSelectedTrayValue().equals("__") && (e.getCurrentSelectedBoardValue() != null)){
            String input = blankPrompt();
            if (input != null){
                gameBoardButtons[e.getCurrentSelectedBoardValue().get(0)][e.getCurrentSelectedBoardValue().get(1)].setText(input);
            }
        }
        else if(!e.getCurrentSelectedTrayValue().equals(" ") && (e.getCurrentSelectedBoardValue() != null) ){
            gameBoardButtons[e.getCurrentSelectedBoardValue().get(0)][e.getCurrentSelectedBoardValue().get(1)].setText(e.getCurrentSelectedTrayValue());
            letterTrayButtons[e.getTrayNumPos()].setEnabled(false);
        }
        else {
            updateTray(e.getTrayValues(), e.getCurrentPlayerIsAI());
            updateBoard(e.getUsedSquares());
            updateCurrentPlayer(e.getCurrentPlayer().toString());
            updateScoreBoard(e.getPlayerList());
            updateGameButtons(e.getCurrentPlayer(), e.getSwapState());
        }
    }


    /**
     * Provides the letter the player selected the blank to be.
     */
    @Override
    public String getBlankState() {
        return blankPrompt();
    }


    /**
     * Updates the current player's tray.
     *
     * @param trayValues The values of this player's tray.
     * @param currentPlayerIsAI true if the current player is AI, false otherwise.
     */
    private void updateTray(String trayValues, boolean currentPlayerIsAI){
        String[] tray =  trayValues.split(" ");
        for(int i = 0; i < 7; i++){
            letterTrayButtons[i].setText(tray[i]);
            if (currentPlayerIsAI) {
                letterTrayButtons[i].setEnabled(false);
            } else {
                letterTrayButtons[i].setEnabled(true);
            }
        }
    }

    /**
     * Updates the game board when a letter is placed.
     *
     * @param boardValues The values of the board grid.
     */
    private void updateBoard(String[][] boardValues){

        for(int i = 0; i < Board.SIZE; i++){
            for(int j = 0; j < Board.SIZE; j++) {

                gameBoardButtons[j][i].setText(boardValues[i][j]);
                if(!gameBoardButtons[j][i].getText().equals(" ")){
                    gameBoardButtons[j][i].setEnabled(false);
                }
                if(gameBoardButtons[j][i].isEnabled()) {

                    gameBoardButtons[j][i].setText(" ");
                }
            }

        }

    }


    /**
     * Updates the currentPlayer label to the right of the game board.
     * @param currentPlayer the player whose turn it is.
     */
    private void updateCurrentPlayer(String currentPlayer) {

        currentPlayerLabel.setText(currentPlayer);
    }


    /**
     * Updates the player scores on the ScoreBoard to the left of the game board.
     * @param namesAndScores the list of players and scores
     */
    private void updateScoreBoard(ArrayList<Player> namesAndScores) {
        for(int i = 0; i < (namesAndScores.size()); i++) {
            Player p = namesAndScores.get(i);
            playerNamesAndScores[(i * 2)].setText(p.toString() + ":   ");
            playerNamesAndScores[(i * 2) + 1].setText(p.stringScore());
        }
    }

    /**
     * Enables and disables the buttons to the right of the game board,
     * depending on what the current Player's input should be.  For example,
     * an AIPlayer will have access to the Play AI Move buttton, and a user
     * player will have access to the SWAP button.
     *
     * @param currentPlayer The current Player
     * @param swapState true if the current Player is a user, and is swapping letters, false otherwise.
     */
    private void updateGameButtons(Player currentPlayer, boolean swapState) {
        if (swapState) {
            playAIMoveButton.setEnabled(false);
            placeWordButton.setEnabled(false);
            swapButton.setEnabled(false);
            passButton.setEnabled(false);
            resetButton.setEnabled(false);
            goButton.setEnabled(true);
       } else {
            if (currentPlayer.isAIPlayer) {
                playAIMoveButton.setEnabled(true);
                placeWordButton.setEnabled(false);
                swapButton.setEnabled(false);
                passButton.setEnabled(false);
                resetButton.setEnabled(false);
                goButton.setEnabled(false);
            }
            else {
                playAIMoveButton.setEnabled(false);
                placeWordButton.setEnabled(true);
                swapButton.setEnabled(true);
                passButton.setEnabled(true);
                resetButton.setEnabled(true);
                goButton.setEnabled(true);
            }
        }
    }


    /**
     * Quits the game and closes this view.
     */
    public void quitView() {
        this.dispose();
    }


    public static void main(String[] args) {

        ScrabbleScrabbleFrame f = new ScrabbleScrabbleFrame();

    }

}
