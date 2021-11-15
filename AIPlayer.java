package connectfour;

import java.util.Random;

public class AIPlayer extends Player{

    public AIPlayer(char symbol, Board board, String name) {
        super(symbol, board, name);
    }

    /**
     * For AIPlayer.java, the makeMove method can do whatever you want as long as the following two criteria are met:
     * – If there is one or more winning moves available, the AI player will make one of them.
     * – If there is no winning move available, but their opponent has one or more winning
     * moves available for next turn, the AI player will block one of them.
     *
     * We're allowed to import java.util.Random for this
     * @param board
     */
    @Override
    public void makeMove(Board board) {
        Random random = new Random();
        int nextMoveColumn = random.nextInt(1, 8);
        int nextWinningMove = board.nextWinningMove(this.symbol);
        int nextBlockingMove = board.nextBlockingMove(this.symbol);

        if (nextWinningMove != -1){
            board.updateBoard(this.symbol, nextWinningMove);
        } else if (nextBlockingMove != -1) {
            board.updateBoard(this.symbol, nextBlockingMove);
        } else {
            board.updateBoard(this.symbol, nextMoveColumn);
        }
    }

}
