/**
 * Created by primer on 6/15/16.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Moves {

    public static boolean isInCheck(int[] board, int turn){

        int i = 12;
        for (int j = 0; j < 12*12; j++){
            if (board[j]*turn == 99){
                i = j;
                break;
            }
        }

        if (i == 12){
            Moves.printBoard(board);
            System.out.println("king is captured");
            System.out.print(turn);
            return false;
        }

        //ROOK and queen:
        int u = i;
        int d = i;
        int l = i;
        int r = i;

        while (board[u + 12] == 0){
            u += 12;
        }
        if(board[u + 12]*turn == -5 || board[u + 12]*turn == -9){
            return true;
        }
        while (board[d - 12] == 0){
            d -= 12;
        }
        if(board[d - 12]*turn == -5 || board[d - 12]*turn == -9){
            return true;
        }
        while (board[l + 1] == 0){
            l += 1;
        }
        if(board[l + 1]*turn == -5 || board[l + 1]*turn == -9){
            return true;
        }
        while (board[r - 1] == 0){
            r -= 1;
        }
        if(board[r-1]*turn == -5 || board[r-1]*turn == -9){
            return true;
        }


        //BISHOP and queen
        u = i;
        d = i;
        l = i;
        r = i;

        while (board[u + 11] == 0){
            u += 11;
        }
        if(board[u + 11]*turn == -4 || board[u + 11]*turn == -9) {
            return true;
        }
        while (board[d + 13] == 0){
            d += 13;
        }
        if(board[d + 13]*turn == -4 || board[d + 13]*turn == -9){
            return true;
        }
        while (board[l - 11] == 0){
            l -= 11;
        }
        if(board[l - 11]*turn == -4 || board[l - 11]*turn == -9){
            return true;
        }

        while (board[r - 13] == 0){
            r -= 13;
        }
        if(board[r - 13]*turn == -4 || board[r - 13]*turn == -9){
            return true;
        }


        //PAWN;
        if(board[i-11*turn]*turn == -1){
            return true;
        }
        if (board[i-13*turn]*turn == -1){
            return true;
        }

        //HORSIE:
        if (board[i + 14]*turn == -3){
            return true;
        }
        if (board[i + 10]*turn == -3){
            return true;
        }
        if (board[i + 25]*turn == -3){
            return true;
        }
        if (board[i + 23]*turn == -3){
            return true;
        }
        if (board[i - 14]*turn == -3){
            return true;
        }
        if (board[i - 25]*turn == -3){
            return true;
        }
        if (board[i -23]*turn == -3){
            return true;
        }
        if (board[i -10]*turn == -3){
            return true;
        }


        //System.out.println(i);
        //printBoard(board);

        /**
         * Again, this is redundant, and should be avoided
         */
        //KING;
        if(board[i + 1]*turn == -99){
            return true;
        }
        if(board[i - 1]*turn == -99){
            return true;
        }
        if(board[i + 12]*turn == -99){
            return true;
        }
        if(board[i - 12]*turn == -99){
            return true;
        }
        if(board[i + 13]*turn == -99){
            return true;
        }
        if(board[i - 13]*turn == -99){
            return true;
        }
        if(board[i + 11]*turn == -99){
            return true;
        }
        if(board[i + 11]*turn == -99){
            return true;
        }

        return false;
    }

    public static ArrayList expand(int[] board, int turn){

        ArrayList<int[]> results = new ArrayList<>();

        for (int i = 0; i < 12*12; i++){

            int piece = board[i];

            if (piece*turn == 1){
                //System.out.println("addedPawn");
                //results.addAll(addValidPawnMoves(i, board, turn));
            }
            else if (piece*turn == 5){
                //System.out.println("addedRook");
                results.addAll(addValidRookMoves(i, board, turn));
            }
            else if (piece*turn == 4){
                //System.out.println("addedBishop");
                results.addAll(addValidBisshopMoves(i, board, turn));
            }
            else if (piece*turn == 3){
                //System.out.println("addedKnight");
                results.addAll(addValidKnightMoves(i, board, turn));
            }
            else if (piece*turn == 99){
                //System.out.println("addedKing");
                results.addAll(addValidKingMoves(i, board, turn));
            }
            else if (piece*turn == 9){
                //results.addAll(addValidQueenMoves(i, board, turn));
            }
        }
        return results;
    }

    public static int[] copy(int [] board){
        int[] b = new int[12*12];
        for (int t = 0; t < 12*12; t++){
            b[t] = board[t];
        }
        return b;
    }

    public static ArrayList addValidPawnMoves(int i, int[] board, int turn){

        ArrayList<int[]> results = new ArrayList<>();

        int direction = turn;
        int up = i+direction*(-12);
        int left = i+direction*(-12) + 1;
        int right = i+direction*(-12) - 1;

        if (board[up] == 0){
            int [] b = copy(board);

            b[up] = b[i];
            b[i] = 0;
            if(! isInCheck(b, turn)) {
                results.add(b);
            }
        }
        if (direction*board[left]< 0 && board[left] != 66){
            int[] b = copy(board);

            b[left] = b[i];
            b[i] = 0;
            if(! isInCheck(b, turn)) {
                results.add(b);
            }
        }
        if (direction*board[right]< 0 && board[right] != 66){
            int[] b = copy(board);

            b[right] = b[i];
            b[i] = 0;
            if(! isInCheck(b, turn)) {
                results.add(b);
            }
        }
        return results;
    }

    public static ArrayList addValidRookMoves(int i, int[] board, int turn){

        ArrayList<int[]> results = new ArrayList<>();

        int u = i;
        int d = i;
        int l = i;
        int r = i;

        while (board[u + 12] == 0){
            int[] b = copy(board);
            b[u + 12] = b[i];
            b[i] = 0;
            if(! isInCheck(b, turn)) {
                results.add(b);
            }
            u += 12;
        }
        if(board[u + 12] != 66 && board[u + 12]*turn < 0){
            int[] b = copy(board);
            b[u + 12] = b[i];
            b[i] = 0;
            if(! isInCheck(b, turn)) {
                results.add(b);
            }
        }
        while (board[d - 12] == 0){
            int[] b = copy(board);
            b[d - 12] = b[i];
            b[i] = 0;
            if(! isInCheck(b, turn)) {
                results.add(b);
            }
            d -= 12;
        }
        if(board[d - 12] != 66 && board[d - 12]*turn < 0){
            int[] b = copy(board);
            b[d - 12] = b[i];
            b[i] = 0;
            if(! isInCheck(b, turn)) {
                results.add(b);
            }
        }
        while (board[l + 1] == 0){
            int[] b = copy(board);
            b[l + 1] = b[i];
            b[i] = 0;
            if(! isInCheck(b, turn)) {
                results.add(b);
            }
            l += 1;
        }
        if(board[l + 1] != 66 && board[l + 1]*turn < 0){
            int[] b = copy(board);
            b[l + 1] = b[i];
            b[i] = 0;
            if(! isInCheck(b, turn)) {
                results.add(b);
            }
        }
        while (board[r - 1] == 0){
            int[] b = copy(board);
            b[r - 1] = b[i];
            b[i] = 0;
            if(! isInCheck(b, turn)) {
                results.add(b);
            }
            r -= 1;
        }
        if(board[r-1] != 66 && board[r - 1]*turn < 0){
            int[] b = copy(board);
            b[r - 1] = b[i];
            b[i] = 0;
            if(! isInCheck(b, turn)) {
                results.add(b);
            }
        }
        return results;
    }

    public static ArrayList addValidKnightMoves(int i, int[] board, int turn){

        ArrayList<int[]> results = new ArrayList<>();

        if (board[i + 14] != 66 && board[i + 14]*turn <= 0){
            int [] b = copy(board);
            b[i + 14] = b[i];
            b[i] = 0;
            if(! isInCheck(b, turn)) {
                results.add(b);
            }
        }
        if (board[i + 10] != 66 && board[i + 10]*turn <= 0){
            int [] b = copy(board);
            b[i + 10] = b[i];
            b[i] = 0;
            if(! isInCheck(b, turn)) {
                results.add(b);
            }
        }
        if (board[i + 25] != 66 && board[i + 25]*turn <= 0){
            int[] b = copy(board);
            b[i + 25] = b[i];
            b[i] = 0;
            if(! isInCheck(b, turn)) {
                results.add(b);
            }
        }
        if (board[i + 23] != 66 && board[i + 23]*turn <= 0){
            int[] b = copy(board);
            b[i + 23] = b[i];
            b[i] = 0;
            if(! isInCheck(b, turn)) {
                results.add(b);
            }
        }
        if (board[i - 14] != 66 && board[i - 14]*turn <= 0){
            int[] b = copy(board);
            b[i - 14] = b[i];
            b[i] = 0;
            if(! isInCheck(b, turn)) {
                results.add(b);
            }
        }
        if (board[i - 25] != 66 && board[i - 25]*turn <= 0){
            int[] b = copy(board);
            b[i - 25] = b[i];
            b[i] = 0;
            if(! isInCheck(b, turn)) {
                results.add(b);
            }
        }
        if (board[i -23] != 66 && board[i -23]*turn <= 0){
            int[] b = copy(board);
            b[i - 23] = b[i];
            b[i] = 0;
            if(! isInCheck(b, turn)) {
                results.add(b);
            }
        }
        if (board[i -10] != 66 && board[i -10]*turn <= 0){
            int[]  b = copy(board);
            b[i -10] = b[i];
            b[i] = 0;
            if(! isInCheck(b, turn)) {
                results.add(b);
            }
        }
        return results;
    }

    public static ArrayList addValidBisshopMoves(int i, int[] board, int turn){

        ArrayList<int[]> results = new ArrayList<>();

        int u = i;
        int d = i;
        int l = i;
        int r = i;

        while (board[u + 11] == 0){
            int[] b = copy(board);
            b[u + 11] = b[i];
            b[i] = 0;
            if(! isInCheck(b, turn)) {
                results.add(b);
            }
            u += 11;
        }
        if(board[u + 11] != 66 && board[u + 11]*turn < 0) {
            int[] b = copy(board);
            b[u + 11] = b[i];
            b[i] = 0;
            if(! isInCheck(b, turn)) {
                results.add(b);
            }
        }
        while (board[d + 13] == 0){
            int[] b = copy(board);
            b[d + 13] = b[i];
            b[i] = 0;
            if(! isInCheck(b, turn)) {
                results.add(b);
            }
            d += 13;
        }
        if(board[d + 13] != 66 && board[d + 13]*turn < 0){
            int[] b = copy(board);
            b[d + 13] = b[i];
            b[i] = 0;
            if(! isInCheck(b, turn)) {
                results.add(b);
            }
        }
        while (board[l - 11] == 0){
            int[] b = copy(board);
            b[l - 11] = b[i];
            b[i] = 0;
            if(! isInCheck(b, turn)) {
                results.add(b);
            }
            l -= 11;
        }
        if(board[l - 11] != 66 && board[l - 11]*turn < 0) {
            int[] b = copy(board);
            b[l - 11] = b[i];
            b[i] = 0;
            if(! isInCheck(b, turn)) {
                results.add(b);
            }
        }
        while (board[r - 13] == 0){
            int[] b = copy(board);
            b[r - 13] = b[i];
            b[i] = 0;
            if(! isInCheck(b, turn)) {
                results.add(b);
            }
            r -= 13;
        }
        if(board[r - 13] != 66 && board[r - 13]*turn < 0){
            int[] b = copy(board);
            b[r - 13] = b[i];
            b[i] = 0;
            if(! isInCheck(b, turn)) {
                results.add(b);
            }
        }
        return results;
    }

    public static ArrayList addValidQueenMoves(int i, int[] board, int turn){

        ArrayList<int[]> results = new ArrayList<>();

        results.addAll(addValidBisshopMoves(i, board, turn));
        results.addAll(addValidRookMoves(i, board, turn));

        return results;
    }

    public static ArrayList addValidKingMoves(int i, int[] board, int turn){

        ArrayList<int[]> results = new ArrayList<>();

        if (board[i+1]*turn <= 0 && board[i+1] != 66) {
            int [] b = copy(board);
            b[i+1] = b[i];
            b[i] = 0;
            if(! isInCheck(b, turn)) {
                results.add(b);
            }
        }
        if (board[i-1]*turn <= 0 && board[i-1] != 66){
            int [] b = copy(board);
            b[i-1] = b[i];
            b[i] = 0;
            if(! isInCheck(b, turn)) {
                results.add(b);
            }
        }
        if (board[i+12]*turn <= 0 && board[i+12] != 66) {
            int[] b = copy(board);
            b[i + 12] = b[i];
            b[i] = 0;
            if(! isInCheck(b, turn)) {
                results.add(b);
            }
        }
        if (board[i-12]*turn <= 0 && board[i-12] != 66){
            int [] b = copy(board);
            b[i-12] = b[i];
            b[i] = 0;
            if(! isInCheck(b, turn)) {
                results.add(b);
            }
        }
        if (board[i+13]*turn <= 0 && board[i+13] != 66){
            int [] b = copy(board);
            b[i+13] = b[i];
            b[i] = 0;
            if(! isInCheck(b, turn)) {
                results.add(b);
            }
        }
        if (board[i-13]*turn <= 0 && board[i-13] != 66){
            int [] b = copy(board);
            b[i-13] = b[i];
            b[i] = 0;
            if(! isInCheck(b, turn)) {
                results.add(b);
            }
        }
        if (board[i+11]*turn <= 0 && board[i+11] != 66){
            int [] b = copy(board);
            b[i+11] = b[i];
            b[i] = 0;
            if(! isInCheck(b, turn)) {
                results.add(b);
            }
        }
        if (board[i-11]*turn <= 0 && board[i-11] != 66){
            int [] b = copy(board);
            b[i-11] = b[i];
            b[i] = 0;
            if(! isInCheck(b, turn)) {
                results.add(b);
            }
        }
        return results;
    }

    public static void printBoard(int[] board){
        System.out.println();
        System.out.println();
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                System.out.format("%2d", board[2 + j + (i+2)*12]);

            }
            System.out.println();
        }
    }

    public String toString(int [] b, int turn){
        String s = Integer.toString(turn);
        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                s += Integer.toString(b[2 + j + ((i + 2) * 12)]);
            }
        }
        return s;
    }
}

