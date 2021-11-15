package connectfour;

/**
 * A class used to test our work (i.e., run the program and verify all this works)
 */
public class Runner {

    public static void main(String[] args) {
        Board board = new Board();
        ConnectFour game = new ConnectFour(board);
        game.setPlayer1(new AIPlayer('A', board, "AI"));
        game.setPlayer2(new HumanPlayer('S', board, "HUMAN"));
//        game.setPlayer2(new AIPlayer('B', board, "JARVIS"));
        game.playGame();
    }

}
