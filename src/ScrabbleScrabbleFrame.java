import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

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
    private JButton placeWordButton, swapButton, passButton, goButton;
    private JLabel gameStatusMessage;

    private static final Color DOUBLE_WORD = new Color(234, 113, 227);
    private static final Color TRIPLE_WORD = new Color(229, 7, 7);
    private static final Color DOUBLE_LETTER = new Color(43, 160, 186);
    private static final Color TRIPLE_LETTER = new Color(1, 29, 208);
    private static final Color PLAIN = new Color(228, 201, 128);

    public ScrabbleScrabbleFrame() {
        super("ScrabbleScrabble Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLayout(new BorderLayout());

        Game gameModel = new Game(2);
        BoardController boardController = new BoardController(gameModel);
        TrayController trayController = new TrayController(gameModel);
        gameModel.addView(this);

        pane = this.getContentPane();

        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        JMenuItem newGame = new JMenuItem("New Game");
        fileMenu.add(newGame);
        JMenuItem saveGame = new JMenuItem("Save Game");
        fileMenu.add(saveGame);
        JMenuItem loadGame = new JMenuItem("Load Game");
        fileMenu.add(loadGame);
        JMenuItem quitGame = new JMenuItem("Quit Game");
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
                l = new JLabel("Player name: ");
                l.setFont(new Font(Font.SERIF, Font.BOLD, 20));
                playerNameListPanel.add(l);
            } else {
                l = new JLabel("score");
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
                b.setBackground(DOUBLE_WORD);
                //setSquareScore(gameModel.getSquareSpecialty(i,j),b);


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
        placeWordButton.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        placeWordButton.setMaximumSize(new Dimension(200, 50));
        placeWordButton.setBackground(new Color(92, 206, 128));
        playerInputPanel.add(placeWordButton);

        swapButton = new JButton("Swap");
        swapButton.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        swapButton.setMaximumSize(new Dimension(200, 50));
        swapButton.setBackground(new Color(92, 206, 128));
        playerInputPanel.add(swapButton);

        passButton = new JButton("Pass");
        passButton.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        passButton.setMaximumSize(new Dimension(200, 50));
        passButton.setBackground(new Color(92, 206, 128));
        playerInputPanel.add(passButton);

        goButton = new JButton("GO");
        goButton.setFont(new Font(Font.SERIF, Font.PLAIN, 50));
        goButton.setBackground(new Color(67, 143, 95));
        playerInputPanel.add(goButton);
        goButton.setMaximumSize(new Dimension(200, 175));


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
        gameModel.play();
    }


    public static void main(String[] args) {

        ScrabbleScrabbleFrame f = new ScrabbleScrabbleFrame();

    }

    @Override
    public void update(GameEvent e) {

        if(!e.getCurrentSelectedTrayValue().equals(" ") && (e.getCurrentSelectedTrayValue() != null) ){
            gameBoardButtons[e.getCurrentSelectedBoardValue().get(0)][e.getCurrentSelectedBoardValue().get(1)].setText(e.getCurrentSelectedTrayValue());

        }
        else{
            updateTray(e.getTrayValues());
            updateBoard(e.getUsedSquares());
        }

    }

    @Override
    public void initialization() {

    }

    private void updateTray(String trayValues){
        String[] tray =  trayValues.split(" ");
        for(int i = 0; i < 7; i++){
            letterTrayButtons[i].setText(tray[i]);
        }

    }

    private void updateBoard(String[][] boardValues){

        for(int i = 0; i < Board.SIZE; i++){
            for(int j = 0; j < Board.SIZE; j++) {

                gameBoardButtons[j][i].setText(boardValues[i][j]);
            }

        }

    }

    private void setSquareScore(Board.scores score, JButton b){
        if (score == null) return;
        b.setBackground(
                switch (score){
                    case DL -> DOUBLE_LETTER;
                    case TL -> TRIPLE_LETTER;
                    case DW -> DOUBLE_WORD;
                    case TW -> TRIPLE_WORD;
                });

    }



}
