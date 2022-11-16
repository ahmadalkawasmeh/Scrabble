import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The Board for scrabblescrabble.  Represents a square grid of squares, where
 * each square is able to hold one letter.
 */
public class Board {
    enum scores{DL, TL, DW, TW}

    public static final int SIZE = 15; // The size of the Board (a grid of SIZE x SIZE)
    private String[][] usedSquares; // Squares that have letters placed on them
    public static Enum[][] specialSquares; // Squares with special scoring modifiers

    private HashMap<String, String> boardValues;

    /**
     * Initializes the scrabblescrabble game board, by setting up the board with empty values (no letters), an
     * empty array for used squares, and initializes the special squares in the game.
     */
    public Board(){
        boardValues = new HashMap<>();
        usedSquares = new String[SIZE][SIZE];
        specialSquares = new scores[SIZE][SIZE];

        this.initializeBoard();
        setSpecialSquaresStandard();
    }


    /**
     * Returns the current status of the game board as a String representation.
     * Developed by: Ibtasam Rasool
     *
     * @return a String representation of the game board.
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
     * Places a word on the board.  This updates the boardValues map with the
     * position of each letter.
     * Developed by: Ibtasam Rasool
     *
     * @param word The word to place on the board.
     */
    public void addWordToBoard(Word word){
        ArrayList<Integer> numPos = word.findWordPosition();
        String letters[] = word.getWord().split("");

        if(word.isHorizontal()) {
            for (int i = 0; i < word.length(); i++) {

                usedSquares[numPos.get(1) + i][numPos.get(0)] = letters[i];
            }
        } else{

            for (int i = 0; i < word.length(); i++) {

                usedSquares[numPos.get(0)][numPos.get(1) + i] = letters[i];
            }
        }
        this.boardValues.putAll(word.getLetterPositions());
    }


    /**
     * Initializes the set of special squares on the board for a custom mapping.
     * A special square is one that will have alternative scoring implementations
     * (double letter score, triple letter score, double word score, triple word
     * score).
     * Developed by: Daniel Kuchanski
     *
     * @param specialSquares the list of special squares
     */
    public void setSpecialSquares(ArrayList<String> specialSquares){
        for (String s : specialSquares){
            //Add special squares to specialSquares array. Parser?
        }
    }

    /**
     * --- NOT FOR MILESTONE 1 ---
     * Initializes the standard mapping of special squares on the board.
     * A special square is one that will have alternative scoring implementations
     * (double letter score, triple letter score, double word score, triple word
     * score).
     * Developed by: Daniel Kuchanski
     */
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
                                                                specialSquares[11][14] = scores.DL;
    }

    /**
     * Initializes board with empty values on each square (indicating
     * no letter has been placed on each square).
     * Developed by: Ibtasam Rasool
     */
    private void initializeBoard(){
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                usedSquares[i][j] = " ";
            }
        }
    }

    public boolean checkWordOnBoard(Word word){
        HashMap<String, String> letterPositions = word.getLetterPositions();
        ArrayList<Integer> coordinate =  Word.numCoordinate(letterPositions.keySet().iterator().next());

        if(word.isHorizontal()){
            return checkHorizontal(coordinate, true);
        }

        return checkVertical(coordinate, true);

    }

    private boolean checkVertical(ArrayList<Integer> coordinate, boolean secondScan) {
        boolean runCheck = true;
        int count = 0;
        String wordFormed = "";
        Dictionary dictionary = new Dictionary();

        //System.out.println(coordinate.get(0));
        //System.out.println(coordinate.get(1));


        while(runCheck){

            if(coordinate.get(0) + count < SIZE && !usedSquares[coordinate.get(0)][coordinate.get(1) + count].equals(" ")) {
                wordFormed = wordFormed + usedSquares[coordinate.get(0)][coordinate.get(1) + count];

                if((!usedSquares[coordinate.get(0) + 1][coordinate.get(1) + count].equals(" ") || !usedSquares[coordinate.get(0) - 1][coordinate.get(1) + count].equals(" ")) && secondScan) {
                    if(!checkHorizontal(new ArrayList<>(List.of(coordinate.get(0), coordinate.get(1) + count)), false)){
                        return false;
                    }
                }

                count += 1;
            }
            else {
                runCheck = false;
            }
        }

        System.out.println(wordFormed);
        runCheck = true;
        count  = 1;

        while (runCheck){
            if(coordinate.get(0) - count >= 0 && !usedSquares[coordinate.get(0)][coordinate.get(1) - count].equals(" ")) {
                wordFormed = usedSquares[coordinate.get(0)][coordinate.get(1) - count] + wordFormed;

                if((!usedSquares[coordinate.get(0) + 1][coordinate.get(1) - count].equals(" ") || !usedSquares[coordinate.get(0) - 1][coordinate.get(1) - count].equals(" ")) && secondScan) {
                    if(!checkHorizontal(new ArrayList<>(List.of(coordinate.get(0), coordinate.get(1) - count)), false)){
                        return false;
                    }
                }

                count += 1;
            }
            else {
                runCheck = false;
            }
        }
        System.out.println(wordFormed);
        //System.out.println(usedSquares[coordinate.get(0)][coordinate.get(1)]);

        return dictionary.lookupDictionary(wordFormed.toLowerCase());

    }



    private boolean checkHorizontal(ArrayList<Integer> coordinate, boolean secondScan){
        boolean runCheck = true;
        int count = 0;
        String wordFormed = "";
        Dictionary dictionary = new Dictionary();

            //System.out.println(coordinate.get(0));
            //System.out.println(coordinate.get(1));


            while(runCheck){

                if(coordinate.get(0) + count < SIZE && !usedSquares[coordinate.get(0) + count][coordinate.get(1)].equals(" ")) {
                    wordFormed = wordFormed + usedSquares[coordinate.get(0) + count][coordinate.get(1)];


                    if((!usedSquares[coordinate.get(0) + count][coordinate.get(1) - 1].equals(" ") || !usedSquares[coordinate.get(0) + count][coordinate.get(1) + 1].equals(" ")) && secondScan) {
                        if(!checkVertical(new ArrayList<>(List.of(coordinate.get(0) + count, coordinate.get(1))), false)){
                            return false;
                        }
                    }

                    count += 1;
                }
                else {
                    runCheck = false;
                }
            }

            System.out.println(wordFormed);
            runCheck = true;
            count  = 1;
            while (runCheck){
                if(coordinate.get(0) - count >= 0 && !usedSquares[coordinate.get(0) - count][coordinate.get(1)].equals(" ")) {
                    wordFormed = usedSquares[coordinate.get(0) - count][coordinate.get(1)] + wordFormed;



                    if((!usedSquares[coordinate.get(0) - count][coordinate.get(1) - 1].equals(" ") || !usedSquares[coordinate.get(0) - count][coordinate.get(1) + 1].equals(" ")) && secondScan) {
                        if(!checkVertical(new ArrayList<>(List.of(coordinate.get(0) - count, coordinate.get(1))), false)){
                            return false;
                        }
                    }



                    count += 1;
                }
                else {
                    runCheck = false;
                }
            }
            System.out.println(wordFormed);
            //System.out.println(usedSquares[coordinate.get(0)][coordinate.get(1)]);

            return dictionary.lookupDictionary(wordFormed.toLowerCase());

    }

    public String[][] getUsedSquares(){
        return usedSquares;
    }

    public Enum getSquareScore(int x, int y){
        return specialSquares[x][y];
    }
    public static void main(String[] args) {
        /*
        Word word1 = new Word("Slope", "G5");
        Word word2 = new Word("Stil", "6C");
        Board board = new Board();
        board.addWordToBoard(word1);
        board.addWordToBoard(word2);

        ArrayList<String> e = new ArrayList<>();
        e.add("slime");
        System.out.println( e.iterator().next());

        System.out.println(board);
        System.out.println(board.checkWordOnBoard(word2));

         */
        Word word1 = new Word("FEE", "H8");
        Word word2 = new Word("S", "11H");
        //Word word2 = new Word("DID", "9M");
        //Word word3 = new Word("ID", "9G");
        //Word word4 = new Word("DID", "H7");
        //Word word5 = new Word("FINITE", "8E");

        Board board = new Board();
        //board.addWordToBoard(word1);
        //board.addWordToBoard(word2);
        //board.addWordToBoard(word3);
        //board.addWordToBoard(word4);
        //board.addWordToBoard(word2);

        board.addWordToBoard(word1);
        board.addWordToBoard(word2);
        System.out.println(board.checkWordOnBoard(word2));
        System.out.println(board);

    }


}
