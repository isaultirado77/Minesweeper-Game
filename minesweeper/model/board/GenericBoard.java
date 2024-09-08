package minesweeper.model.board;

import minesweeper.io.Printer;

/**
 * Generic board is an abstract implementation of the Board interface, providing basic functionality
 * for initializing and displaying a game board. Specific game logic should be implemented in subclasses.
 */
public abstract class GenericBoard implements Board {

    protected final int ROWS;
    protected final int COLS;
    protected Cell[][] board;

    public GenericBoard(int rows, int cols) {
        board = new Cell[rows][cols];
        this.ROWS = rows;
        this.COLS = cols;
    }

    @Override
    public abstract void initBoard();

    @Override
    public void resetBoard() {
        initBoard();
    }

    @Override
    public abstract void displayBoard();

    @Override
    public Cell getCellState(int row, int col) {
        return board[row][col];
    }

    @Override
    public void updateBoard(int row, int col, Cell cell) {
        board[row][col] = cell;
    }

    @Override
    public abstract boolean isGameOver();
}
