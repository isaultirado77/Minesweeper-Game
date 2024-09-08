package minesweeper.model.board;

/** Board is an interface that defines the basic operations for managing a game board. */
public interface Board {

    /** Initializes the game board with the necessary starting values*/
    void initBoard();

    /** Resets the board to its initial state */
    void resetBoard();

    /** Updates the board at the specified row and column with a given cell
     ** @param row the row to update.
     * @param col the column to update.
     * @param cell the new Cell value to place on the board. */
    void updateBoard(int row, int col, Cell cell);

    /** Displays the current state of the board. */
    void displayBoard();

    /** Return the state of a specific cell on the board.
     * @param row
     * @param col
     * @return the current state of the cell*/
    Cell getCellState(int row, int col);

    /** Check if the game is over based on the board's current state
     * @return true if the game is over, false otherwise. */
    boolean isGameOver();
}
