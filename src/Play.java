import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by primer on 6/16/16.
 */
public class Play {


    public static boolean gameOn = true;
    public static int[] thirdLastBoard;
    public static int[] secondLastBoard;
    public static int[] lastBoard;


    public static int count = 0;


    public static HashMap<String, int[]> memory;

    public static void main(String[] args){

        memory = new HashMap<>();

        System.out.println("A new beginning");

        int[] board = Game.board;
        thirdLastBoard = board;
        Moves.printBoard(board);

        //System.out.println(Moves.isInCheck(board, 1));
        //System.out.println(Moves.isInCheck(board, -1));
        System.out.println(Rate.rateBoard(board));

        //ArrayList<int[]> moves = Moves.addValidKingMoves(65, board, 1);
        //ArrayList<int[]> moves = Moves.expand(board, 1);

        //ArrayList<int[]> bishopMoves = Moves.addValidBisshopMoves(39, board, -1);



        //ArrayList<int[]> moves = Moves.addValidKnightMoves(66, board, -1);
        //for(int[] m: moves){
        //    Moves.printBoard(m);
        //}


        gameOn= true;
        int[] mv = board;
        int i = 1;
        int turn = -1;
        while (i < 6 & gameOn){
            mv = choose(mv, turn);
            Moves.printBoard(mv);
            System.out.println(Rate.rateBoard(mv));
            turn *= -1;
            i ++;
        }

        System.out.println("number of calls to search(): " + count);

    }

    public static int[] choose(int[] board, int turn){
        lastBoard = board;
        ArrayList<int[]> moves = Moves.expand(board, turn);



        if(moves.size() == 0){
            gameOn = false;
            return board;
        }

        int[] result = moves.get(0);
        int evaluation = Rate.rateBoard(result);

        for(int[] b: moves){
            int e = search(0, b, -turn);
            if (turn == 1 & e > evaluation){
                //System.out.println(e + " " + evaluation + " " + turn);
                result = b;
            }
            else if (turn == -1 & e < evaluation){
                //System.out.println(e + " " + evaluation + " " + turn);
                result = b;
            }
        }
        return result;
    }


    public static int search(int depth, int[] board, int turn){

        count += 1;

        /**
         * The hashmap (memory) is golden!
         * With a depth of 5 it reduces the number of searches from about 400,000 to about 300,000
         */
        String key = Moves.toString(board, turn);
        if(memory.containsKey(key) && memory.get(key)[0] <= depth){
            //System.out.println(key);
            return memory.get(key)[1];
        }


        ArrayList<int[]> moves = Moves.expand(board, turn);

        if(moves.size() == 0){
            if(Moves.isInCheck(board, turn)){
                //Moves.printBoard(board);
                //System.out.println("this is it mate");
                //System.out.println(turn);
                return -turn*(999 - depth);
            }
            return 0;
        }

        depth += 1;

        int[] result = moves.get(0);
        int evaluation = Rate.rateBoard(result);

        if (depth >= 5){
            for(int[] b: moves){
                int e = Rate.rateBoard(b);
                if (turn == 1 & e > evaluation){
                    evaluation = e;
                }
                else if (turn == -1 & e < evaluation){
                    evaluation = e;
                }
            }
            return evaluation;
        }
        for(int[] b: moves){

            secondLastBoard = board;
            lastBoard = b;
            int e = search(depth, b, -turn);

            if (turn == 1 & e > evaluation){
                //System.out.println(e + " " + evaluation + " " + turn);
                evaluation = e;
            }
            else if (turn == -1 & e < evaluation){
                //System.out.println(e + " " + evaluation + " " + turn);
                evaluation = e;
            }
        }
        //System.out.println(evaluation);

        if (! memory.containsKey(key) || memory.get(key)[0] > depth) {
            memory.put(key, new int[] {depth, evaluation});
        }
        return evaluation;
    }
}
