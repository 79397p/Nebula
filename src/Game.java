/**
 * Created by primer on 6/15/16.
 */
public class Game {
    //holds state of the game

    public static int[] board = {
            66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66,
            66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66,
            66, 66,  0,  0,  0,  0,  0,  0,  0,  0, 66, 66,
            66, 66,  0,  0,  0,-99,  0,  0,  0,  5, 66, 66,
            66, 66,  0,  0,  0,  0,  0,  0,  0,  0, 66, 66,
            66, 66,  5,  0,  0, 99,  0,  0,  0,  0, 66, 66,
            66, 66,  0,  0,  0,  0,  0,  0,  0,  0, 66, 66,
            66, 66,  0,  0,  0,  0,  0,  0,  0,  0, 66, 66,
            66, 66,  0,  0,  0,  0,  0,  0,  0,  0, 66, 66,
            66, 66,  0,  0,  0,  0,  0,  0,  0,  0, 66, 66,
            66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66,
            66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66
    };

    public static int turn;
    public static int enPassant;
    // + other variables

}
