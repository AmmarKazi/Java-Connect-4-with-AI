package connectfour;

/**
 * We can import ArrayList, but it's probably better to use regular arrays
 */
public class Board {

    private final int NUM_OF_COLUMNS = 7; //Initialize the number of columns
    private final int NUM_OF_ROW = 6; //Initialize the number of rows

    private char[][] board; //Create a 2D array called board

    /*
     * The board object must contain the board state in some manner.
     * You must decide how you will do this.
     *
     * You may add addition private/public methods to this class is you wish.
     * However, you should use best OO practices. That is, you should not expose
     * how the board is being implemented to other classes. Specifically, the
     * Player classes.
     *
     */

    public Board() {
        this.board = new char[NUM_OF_ROW][NUM_OF_COLUMNS];
    } //Board method creates the 2D array using the columns and rows initialized earlier

    public void printBoard() {
        for (int i = 0; i < NUM_OF_ROW; i++) {
            System.out.print("|");
            for (int j = 0; j < NUM_OF_COLUMNS; j++) {

                if (i == NUM_OF_ROW - 1 && this.board[i][j] == '\0') {
                    System.out.print("_");
                } else if (this.board[i][j] == '\0') {
                    System.out.print(" ");
                } else {
                    System.out.print(this.board[i][j]);
                }
                System.out.print("|");
            }
            System.out.println();
        }
    }

    public boolean containsWin() {
        for (int i = 0; i < NUM_OF_ROW; i++) { //Iterate through the rows of the board
            for (int j = 0; j < NUM_OF_COLUMNS; j++) { //Iterate through the columns of the board
                if (board[i][j] != '\0') { //Checks to make sure that the board is not empty
                    char symbol = board[i][j];
                    if (containsHorizontalWin(symbol, i, j) ||
                            containsVerticalWin(symbol, i, j) ||
                            containsDiagonalWin(symbol, i, j)) {
                        return true; // Checks if the symbol found has any type of wins
                    }
                }
            }
        }
        return false;
    }

    public boolean isTie() {
        for (int i = 0; i < NUM_OF_ROW; i++) {
            for (int j = 0; j < NUM_OF_COLUMNS; j++) {
                if (board[i][j] == '\0') {
                    return false;
                }
            }
        }
        return true;
    }

    public void reset() {
        this.board = new char[NUM_OF_ROW][NUM_OF_COLUMNS];
    }

    public boolean isColumnFull(int nextMoveColumn) {
        return board[0][nextMoveColumn - 1] == '\0';
    }

    public void updateBoard(char symbol, int nextMoveColumn) {
        for (int i = NUM_OF_ROW - 1; i >= 0; i--) {
            if (board[i][nextMoveColumn - 1] == '\0') {
                board[i][nextMoveColumn - 1] = symbol;
                break;
            }
        }
    }

    private boolean containsHorizontalWin(char symbol, int row, int col) {
        if (col + 3 < NUM_OF_COLUMNS) {
            return board[row][col + 1] == symbol &&
                    board[row][col + 2] == symbol &&
                    board[row][col + 3] == symbol;
        }
        return false;
    }

    private boolean containsVerticalWin(char symbol, int row, int col) {
        if (row + 3 < NUM_OF_ROW) {
            return board[row + 1][col] == symbol &&
                    board[row + 2][col] == symbol &&
                    board[row + 3][col] == symbol;
        }
        return false;
    }

    private boolean containsDiagonalWin(char symbol, int row, int col) {
        boolean containsWin = false;

        // top-left to bottom-right
        if (row + 3 < NUM_OF_ROW && col + 3 < NUM_OF_COLUMNS ) {
            if (board[row + 1][col + 1] == symbol &&
                    board[row + 2][col + 2] == symbol &&
                    board[row + 3][col + 3] == symbol) {
                containsWin = true;
            }
        }

        // top-right to bottom-left
        if (row + 3 < NUM_OF_ROW && col - 3 >= 0 ) {
            if (board[row + 1][col - 1] == symbol &&
                    board[row + 2][col - 2] == symbol &&
                    board[row + 3][col - 3] == symbol) {
                containsWin = true;
            }
        }

        return containsWin;
    }

    public int nextWinningMove(char symbol) {
        for (int j = 0; j < NUM_OF_COLUMNS; j++) {
            if (isNextWinningMove(symbol, j + 1)) {
                return j + 1;
            }
        }
        return -1;
    }

    private boolean isNextWinningMove(char symbol, int nextMoveColumn) {
        int nextMoveRow = -1;

        for (int i = NUM_OF_ROW - 1; i >= 0; i--) {
            if (board[i][nextMoveColumn - 1] == '\0') {
                board[i][nextMoveColumn - 1] = symbol;
                nextMoveRow = i + 1;
                break;
            }
        }

        if (nextMoveRow != -1) {
            if (this.containsWin()) {
                board[nextMoveRow - 1][nextMoveColumn - 1] = '\0';
                return true;
            }
        }

        if (nextMoveRow != -1) {
            board[nextMoveRow - 1][nextMoveColumn - 1] = '\0';
        }
        return false;
    }

    public int nextBlockingMove(char symbol) {
        // find opponent's symbol
        char opponentSymbol = getOpponentSymbol(symbol);

        if (opponentSymbol == '\0') {
            return -1;
        }

        for (int j = 0; j < NUM_OF_COLUMNS; j++) {
            if (isNextWinningMove(opponentSymbol, j + 1)) {
                return j + 1;
            }
        }
        return -1;
    }

    private char getOpponentSymbol(char symbol) {
        for (int i = 0; i < NUM_OF_ROW; i++) {
            for (int j = 0; j < NUM_OF_COLUMNS; j++) {
                if (board[i][j] != '\0' && board[i][j] != symbol) {
                    return board[i][j];
                }
            }
        }
        return '\0';
    }

}
