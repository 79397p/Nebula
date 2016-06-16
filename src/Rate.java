/**
 * Created by primer on 6/15/16.
 */
public class Rate {
    //rate the board
    public static int rateBoard(int[] board){
        int value = 66*64-66*12*12;
        for (int i = 0; i < 12*12; i++){
            value += board[i];
        }
        return value;
    }
}
