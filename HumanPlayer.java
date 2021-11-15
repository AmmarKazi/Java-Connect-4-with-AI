package connectfour;

import java.util.Scanner;

/**
 * We can import java.util.Scanner to get human input
 */
public class HumanPlayer extends Player{

    public HumanPlayer(char symbol, Board board, String name) {
        super(symbol, board, name);
    }

    /**
     * We can assume that the player will always enter a valid number [1,7]
     *
     * BUT, if the column is full (or some other invalid move), retry.
     * @param board
     */
    @Override
    public void makeMove(Board board) {
        int nextMoveColumn;

        Scanner input = new Scanner(System.in);

        System.out.print(this.name + ", please input your move: ");

        nextMoveColumn = input.nextInt();

        if (board.isColumnFull(nextMoveColumn)) {
            board.updateBoard(this.symbol, nextMoveColumn);
        } else {
            System.out.println("THIS IS INVALID! TRY AGAIN!");
            makeMove(board);
        }

    }

}
