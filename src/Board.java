import java.util.ArrayList;
import java.util.HashMap;

public class Board {
    enum scores{DL, TL, DW, TW}

    private final int SIZE = 15;
    private String[][] usedSquares;
    public Enum[][] specialSquares;

    private HashMap<String, String> boardValues;

    public Board(){
        boardValues = new HashMap<>();
        usedSquares = new String[SIZE][SIZE];
        specialSquares = new scores[SIZE][SIZE];

        initializeBoard();
        setSpecialSquaresStandard();


    }


    /**
     * @return String repersentation of the board
     * Developed by Ibtasam Rasool
     */
    public String toString(){

        String gridString = "";
        String letters =        "    A   B   C   D   E   F   G   H   I   J   K   L   M   N   O";
        String linePartition =  "  _____________________________________________________________";

        gridString += letters + "\n";
        for (int i = 0; i < SIZE ; i++){
            gridString =  gridString + linePartition + "\n";

            if(i < 9){gridString += i + 1 + " ";}
            else{ gridString += i + 1;}
            for (int j = 0; j < SIZE; j++){
                gridString += "| " + usedSquares[j][i] + " ";
            }
            gridString += "|\n";
        }
        gridString += linePartition + "\n";
        gridString += letters;
        return gridString;


    }

    /**
     * adds a word to the board and to the boardValues map
     * @param word
     * developed by Ibtasam Rasool
     */
    public void addWordToBoard(Word word){
        ArrayList<Integer> numPos = word.findWordPosition();
        String letters[] = word.getWord().split("");

        if(word.isHorizontal()) {
            for (int i = 0; i < word.length(); i++) {

                usedSquares[numPos.get(1) + i][numPos.get(0)] = letters[i];

            }
        }

        else{

            for (int i = 0; i < word.length(); i++) {

                usedSquares[numPos.get(0)][numPos.get(1) + i] = letters[i];

            }
        }

        this.boardValues.putAll(word.getLetterPositions());

    }




    public void setSpecialSquares(ArrayList<String> specialSquares){
        for (String s : specialSquares){
            //Add special squares to specialSquares array. Parser?
        }
    }
    public void setSpecialSquaresStandard(){
        specialSquares[0][0] = specialSquares[0][7] = specialSquares[0][14] = specialSquares[7][0] =
                specialSquares[7][7] = specialSquares[7][14] = specialSquares[14][0] =
                specialSquares[14][7] = specialSquares[14][14] = scores.TW;

        specialSquares[5][1] = specialSquares[9][1] = specialSquares[1][5] = specialSquares[5][5] =
                specialSquares[9][5] = specialSquares[13][5] = specialSquares[1][9] = specialSquares[5][9] =
                        specialSquares[9][9] = specialSquares[13][9] = specialSquares[5][13] =
                                specialSquares[9][13] = scores.TL;

        specialSquares[1][1] = specialSquares[13][1] = specialSquares[2][2] = specialSquares[12][2] =
                specialSquares[3][3] = specialSquares[10][3] = specialSquares[4][4] = specialSquares[9][4] =
                        specialSquares[4][10] = specialSquares[10][10] = specialSquares[3][11] =
                                specialSquares[11][11] = specialSquares[2][12] = specialSquares[12][12] =
                                        specialSquares[1][13] = specialSquares[13][13] = scores.DW;

        specialSquares[3][0] = specialSquares[11][0] = specialSquares[6][2] = specialSquares[8][2] =
                specialSquares[0][3] = specialSquares[7][3] = specialSquares[14][3] = specialSquares[2][6] =
                        specialSquares[6][6] = specialSquares[8][6] = specialSquares[12][6] =
                                specialSquares[3][7] = specialSquares[11][7] = specialSquares[2][8] =
                                        specialSquares[6][8] = specialSquares[8][8] = specialSquares[12][8]
                                                = specialSquares[0][11] = specialSquares[7][11] =
                                                specialSquares[13][11] = specialSquares[6][12] =
                                                        specialSquares[8][12] = specialSquares[3][14] =
                                                                specialSquares[11][14] = scores.DW;
    }

    /**
     * Initializes board with empty values
     */
    private void initializeBoard(){

        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                usedSquares[i][j] = " ";
            }

        }

    }

    public static void main(String[] args) {
        Board board = new Board();

       // Word word = new Word("CAT", "G6");
       // Word word1 = new Word( "TREE","3H" );
       // board.addWordToBoard(word);
       // board.addWordToBoard(word1);

        System.out.print(board);

    }
}
