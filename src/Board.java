import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Board {
    enum scores{DL, TL, DW, TW}

    private final int SIZE = 15;
    private char[][] usedSquares;
    public Enum[][] specialSquares;
    public Board(){
        usedSquares = new char[SIZE][SIZE];
        specialSquares = new scores[SIZE][SIZE];
        setSpecialSquaresStandard();
    }

    public void output(){

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
}
