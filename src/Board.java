import java.util.*;


/**
 * The Board for scrabblescrabble.  Represents a square grid of squares, where
 * each square is able to hold one letter.
 */
public class Board {

    enum scores{DL, TL, DW, TW}


    public static final int SIZE = 15; // The size of the Board (a grid of SIZE x SIZE)
    private String[][] usedSquares; // Squares that have letters placed on them
    public Enum[][] specialSquares; // Squares with special scoring modifiers
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
        // setSpecialSquaresStandard(); // use the standard configuration of special squares (For milestone 3)
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

    public void removeLettersFromBoard(Word word){
        ArrayList<Integer> numPos = word.findWordPosition();

        if(word.isHorizontal()) {
            for (int i = 0; i < word.length(); i++) {

                if(!word.getLetterPositions().get((Letters.values()[numPos.get(1) + i].toString() + numPos.get(0)) ).equals(" ")) {
                    usedSquares[numPos.get(1) + i][numPos.get(0)] = " ";
                }
            }
        } else{

            for (int i = 0; i < word.length(); i++) {

                if(!word.getLetterPositions().get((Letters.values()[numPos.get(0)].toString() + (numPos.get(1) + i)) ).equals(" ")){
                    usedSquares[numPos.get(0)][numPos.get(1) + i] = " ";
                }

            }
        }
        for(String key: word.getLetterPositions().keySet()){
            if(!(word.getLetterPositions().get(key) == " ")) {
                this.boardValues.remove(key);
            }
        }

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
     /* public void setSpecialSquaresStandard(){
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
    } */


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


    /**
     * Checks if the word being placed on the board has conflicts with the words around it.  A conflict means
     * that the word creates illegal words (not found in the dictionary) as a result of placing this word.
     *
     * @param word The Word to check
     * @return true if the word is placed in a location with no conflicts, otherwise returns false
     */
    public boolean checkWordOnBoard(Word word){
        HashMap<String, String> letterPositions = word.getLetterPositions();
        ArrayList<Integer> coordinate =  Word.numCoordinate(letterPositions.keySet().iterator().next());

        if(word.isHorizontal()){
            return checkHorizontal(coordinate, true);
        }
        return checkVertical(coordinate, true);
    }


    /**
     * Checks the list of coordinates for word conflicts in the vertical direction.
     *
     * @param coordinate The coordinate to check.
     * @param secondScan
     * @return true if the coordinates have no conflicts in the vertical direction, false otherwise.
     */
    private boolean checkVertical(ArrayList<Integer> coordinate, boolean secondScan) {
        boolean runCheck = true;
        int count = 0;
        String wordFormed = "";
        Dictionary dictionary = new Dictionary();

        while(runCheck){

            if(coordinate.get(1) + count < SIZE && !usedSquares[coordinate.get(0)][coordinate.get(1) + count].equals(" ")) { //remove -1

                wordFormed = wordFormed + usedSquares[coordinate.get(0)][coordinate.get(1) + count];


                if(coordinate.get(0) + 1 > SIZE - 1) {

                    if ((!usedSquares[coordinate.get(0) - 1][coordinate.get(1) + count].equals(" ")) && secondScan) {

                        if (!checkHorizontal(new ArrayList<>(List.of(coordinate.get(0), coordinate.get(1) + count)), false)) {

                            return false;

                        }

                    }
                }


                else if(coordinate.get(0) - 1 < 0) {

                    if (!(usedSquares[coordinate.get(0) + 1][coordinate.get(1) + count].equals(" ")) && secondScan) {

                        if (!checkHorizontal(new ArrayList<>(List.of(coordinate.get(0), coordinate.get(1) + count)), false)) {

                            return false;

                        }

                    }
                }


                else if ((!usedSquares[coordinate.get(0) + 1][coordinate.get(1) + count].equals(" ") || !usedSquares[coordinate.get(0) - 1][coordinate.get(1) + count].equals(" ")) && secondScan) {

                    if (!checkHorizontal(new ArrayList<>(List.of(coordinate.get(0), coordinate.get(1) + count)), false)) {

                        return false;

                    }

                }

                count += 1;
            }
            else {
                runCheck = false;
            }
        }

        //System.out.println(wordFormed);
        runCheck = true;
        count  = 1;

        while (runCheck){
            if(coordinate.get(1) - count >= 0 && !usedSquares[coordinate.get(0)][coordinate.get(1) - count].equals(" ")) { // should it be get (0) ?

                wordFormed = usedSquares[coordinate.get(0)][coordinate.get(1) - count] + wordFormed;


                if(coordinate.get(0) + 1 > SIZE - 1) {

                    if ((!usedSquares[coordinate.get(0) - 1][coordinate.get(1) - count].equals(" ")) && secondScan) {
                        if (!checkHorizontal(new ArrayList<>(List.of(coordinate.get(0), coordinate.get(1) - count)), false)) {
                            return false;
                        }
                    }

                }



                else if(coordinate.get(0) - 1 < 0) {

                    if ((!usedSquares[coordinate.get(0) + 1][coordinate.get(1) - count].equals(" ")) && secondScan) {
                        if (!checkHorizontal(new ArrayList<>(List.of(coordinate.get(0), coordinate.get(1) - count)), false)) {
                            return false;
                        }
                    }

                }


                else if ((!usedSquares[coordinate.get(0) + 1][coordinate.get(1) - count].equals(" ") || !usedSquares[coordinate.get(0) - 1][coordinate.get(1) - count].equals(" ")) && secondScan) {
                    if (!checkHorizontal(new ArrayList<>(List.of(coordinate.get(0), coordinate.get(1) - count)), false)) {
                        return false;
                    }
                }


            count += 1;
            }
            else {
                runCheck = false;
            }
        }
        //System.out.println(wordFormed);
        //System.out.println(usedSquares[coordinate.get(0)][coordinate.get(1)]);

        return dictionary.lookupDictionary(wordFormed.toLowerCase());
    }


    /**Checks the list of coordinates for word conflicts in the horizontal direction.
     * @param coordinate The coordinate to check.
     * @param secondScan
     * @return true if the coordinates have no conflicts in the horizontal direction, false otherwise.
     **/
    private boolean checkHorizontal(ArrayList<Integer> coordinate, boolean secondScan){
        boolean runCheck = true;
        int count = 0;
        String wordFormed = "";
        Dictionary dictionary = new Dictionary();

            while(runCheck){

                if(coordinate.get(0) + count < SIZE && !usedSquares[coordinate.get(0) + count][coordinate.get(1)].equals(" ")) {    //FIX SIZE ERROR //check for -1 too not just size -1
                    wordFormed = wordFormed + usedSquares[coordinate.get(0) + count][coordinate.get(1)];


                    if(coordinate.get(1) - 1 < 0) {
                        if ((!usedSquares[coordinate.get(0) + count][coordinate.get(1) + 1].equals(" ")) && secondScan) {
                            if (!checkVertical(new ArrayList<>(List.of(coordinate.get(0) + count, coordinate.get(1))), false)) {
                                return false;
                            }
                        }
                    }


                    else if(coordinate.get(1) + 1 > SIZE - 1) {
                        if ((!usedSquares[coordinate.get(0) + count][coordinate.get(1) - 1].equals(" ")) && secondScan) {
                            if (!checkVertical(new ArrayList<>(List.of(coordinate.get(0) + count, coordinate.get(1))), false)) {
                                return false;
                            }
                        }
                    }

                    else if ((!usedSquares[coordinate.get(0) + count][coordinate.get(1) - 1].equals(" ") || !usedSquares[coordinate.get(0) + count][coordinate.get(1) + 1].equals(" ")) && secondScan) {
                        if (!checkVertical(new ArrayList<>(List.of(coordinate.get(0) + count, coordinate.get(1))), false)) {
                            return false;
                        }
                    }

                    count += 1;
                }
                else {
                    runCheck = false;
                }
            }


            //System.out.println(wordFormed);
            runCheck = true;
            count  = 1;
            while (runCheck){
                if(coordinate.get(0) - count >= 0 && !usedSquares[coordinate.get(0) - count][coordinate.get(1)].equals(" ")) {
                    wordFormed = usedSquares[coordinate.get(0) - count][coordinate.get(1)] + wordFormed;


                    if(coordinate.get(1) - 1 < 0) {
                        if((!usedSquares[coordinate.get(0) - count][coordinate.get(1) + 1].equals(" ")) && secondScan) {
                            if(!checkVertical(new ArrayList<>(List.of(coordinate.get(0) - count, coordinate.get(1))), false)){
                                return false;
                            }
                        }

                    }

                    else if(coordinate.get(1) + 1 > SIZE - 1) {
                        if((!usedSquares[coordinate.get(0) - count][coordinate.get(1) - 1].equals(" ")) && secondScan) {
                            if(!checkVertical(new ArrayList<>(List.of(coordinate.get(0) - count, coordinate.get(1))), false)){
                                return false;
                            }
                        }

                    }


                    else if((!usedSquares[coordinate.get(0) - count][coordinate.get(1) - 1].equals(" ") || !usedSquares[coordinate.get(0) - count][coordinate.get(1) + 1].equals(" ")) && secondScan) {
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
            //System.out.println(wordFormed);

            return dictionary.lookupDictionary(wordFormed.toLowerCase());
    }


    /**
     * Gets the list of used squares on the board.
     *
     * @return The coordinate list of squares that have had letters placed.
     * */
    public String[][] getUsedSquares(){
        return usedSquares;
    }


    public ArrayList<String> formWordUsingBoardValues(ArrayList<Integer> startingWordPos, ArrayList<Integer> endingWordPos, HashMap<Integer, String> coordinatesOfWordToPlace) {
        int startY  = startingWordPos.get(0);
        int startX = startingWordPos.get(1) ;
        int endY  = endingWordPos.get(0) ;
        int endX = endingWordPos.get(1) ;

        String wordToBuild = "";
        String wordWithEmptySpots = "";


        if(startY == endY){
            for(int i = 0; i < (endX - startX) + 1; i++){

                if(usedSquares[startY][startX + i].equals(" ") && coordinatesOfWordToPlace.containsKey(startY * 10 + (startX + i)))

                {
                    wordToBuild += coordinatesOfWordToPlace.get(startY * 10 + (startX + i));
                    wordWithEmptySpots += coordinatesOfWordToPlace.get(startY * 10 + (startX + i));
                }

                else
                {
                    wordToBuild += usedSquares[startX + i][startY];
                    wordWithEmptySpots += " ";
                }

            }
        }

        else if(startX == endX){

            for(int i = 0; i < (endY - startY) + 1; i++){

                if(usedSquares[startY + i][startX].equals(" ") && coordinatesOfWordToPlace.containsKey( ((startY + i) * 10) + startX))

                {
                    wordToBuild += coordinatesOfWordToPlace.get((startY + i) * 10 + startX);
                    wordWithEmptySpots += coordinatesOfWordToPlace.get((startY + i) * 10 + startX);
                }

                else
                {

                    wordToBuild += usedSquares[startX][startY + i];
                    wordWithEmptySpots += " ";
                }

            }
        }

        ArrayList returnList = new ArrayList<String>();
        returnList.add(wordToBuild);


        returnList.add(wordWithEmptySpots);
        return returnList;

    }


    public boolean isWordConnectedToCenter(Word word){
        HashMap<String, String> letterPositions = word.getLetterPositions();
        ArrayList<Integer> startingPos =  Word.numCoordinate(letterPositions.keySet().iterator().next());

        int rowSize = SIZE;
        int columnSize = SIZE;

        int rows;
        int column;

        int startingRow = startingPos.get(0);
        int startingCol = startingPos.get(1);

        Queue<Integer> rowQ = new LinkedList<>();
        Queue<Integer> columnQ = new LinkedList<>();

        int moveCount = 0;
        int nodesLeft = 1;
        int nodesNext = 0;

        boolean reachedEnd = false;

        boolean[][] visited = new boolean[SIZE][SIZE];

        int[] dr = {-1, +1, 0, 0};
        int[] dc = {0, 0, +1, -1};

        rowQ.add(startingRow);
        columnQ.add(startingCol);
        visited[startingRow][startingCol] = true;

        while (rowQ.size() > 0){
            rows = rowQ.poll();
            column = columnQ.poll();
            if((!(usedSquares[rows][column] == " "))&&(rows == 7 && column == 7)){
                reachedEnd = true;
                break;
            }
            for(int i = 0; i < 4; i++){
                int rr = rows + dr[i];
                int cc = column + dc[i];

                if(  ( (!(rr < 0 || cc < 0))  && (!(rr >= rowSize || cc >= columnSize)) ) &&   ( (!visited[rr][cc]) && (!usedSquares[rr][cc].equals(" ")) ) ){
                    rowQ.add(rr);
                    columnQ.add(cc);
                    visited[rr][cc] = true;
                    nodesNext++;
                }
            }
            nodesLeft -= 1;
            if(nodesLeft == 0){
                nodesLeft = nodesNext;
                nodesNext = 0;
                moveCount += 1;
            }

        }
        if (reachedEnd){
            return true;
        }

        return false;
    }






    public static void main(String[] args) {

        /*

        Word word2 = new Word("S", "H8");
        Word word1 = new Word("Apple", "G8");
        Word word3 = new Word("Slime", "A3");

        Board board = new Board();

        board.addWordToBoard(word1);
        board.addWordToBoard(word2);
        board.addWordToBoard(word3);

        System.out.println(board);

        System.out.println(board.usedSquares[7][7]);

        System.out.println(board.isWordConnectedToCenter(word3));
        */


    }

}
