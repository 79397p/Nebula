import java.util.ArrayList;

/**
 * Created by primer on 6/16/16.
 */
public class Play {

    public static void main(String[] args){

        System.out.println("A new beginning");

        int[] board = Game.board;
        Moves.printBoard(board);

        //System.out.println(Moves.isInCheck(board, 1));
        //System.out.println(Moves.isInCheck(board, -1));
        System.out.println(Rate.rateBoard(board));

        //ArrayList<int[]> kingMoves = Moves.addValidKingMoves(26, board, -1);

        //ArrayList<int[]> bishopMoves = Moves.addValidBisshopMoves(39, board, -1);


        /*
        for (int[] b: bishopMoves){
            Moves.printBoard(b);
        }
        */


        //System.out.println(search(0, board, 1));

        //while(! checkMate){

       // }


        int[] nextMove = choose(board, 1);
        Moves.printBoard(nextMove);
    }

    public static int[] choose(int[] board, int turn){
        ArrayList<int[]> moves = Moves.expand(board, turn);

        if(moves.size() == 0){
            //Moves.printBoard(board);
            System.out.println(turn);
            if(Moves.isInCheck(board, -turn)){
            }
            return board;
        }

        int[] result = moves.get(0);
        int evaluation = Rate.rateBoard(result);

        for(int[] b: moves){
            int e = search(0, b, turn);
            if (e > evaluation){
                evaluation = e;
                result = b;
            }
        }
        return result;
    }

    public static int search(int depth, int[] board, int turn){
        ArrayList<int[]> moves = Moves.expand(board, turn);

        if(moves.size() == 0){

            if(Moves.isInCheck(board, turn)){
                //Moves.printBoard(board);
                //System.out.println(turn);
                return -(999 - depth)*turn;
            }
            else{
                return 0;
            }
        }

        depth += 1;
        turn *= -1;

        int[] result = moves.get(0);
        int evaluation = Rate.rateBoard(result);

        if (depth >= 4){
            for(int[] b: moves){
                int e = Rate.rateBoard(b);
                if (e > evaluation){
                    evaluation = e;
                }
            }
            return evaluation;
        }
        for(int[] b: moves){
            int e = search(depth, b, turn);
            if (e > evaluation){
                evaluation = e;
            }
        }
        return evaluation;
    }
}
