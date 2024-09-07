package minesweeper.model.board;

import java.util.Random;

public class MinesweeperBoard extends GenericBoard {

    private final int numberOfMines;

    public MinesweeperBoard(int rows, int cols, int numberOfMines) {
        super(rows, cols);
        this.numberOfMines = numberOfMines;
        initBoard();
    }

    @Override
    public void initBoard() {
        board = generateRandomizedBoard(numberOfMines);
    }

    private Cell[][] generateRandomizedBoard(int amountOfCoordinates) {
        Cell[][] cells = generateSafeBoard();

        int validRandomCellCounter = 0;
        while (validRandomCellCounter < amountOfCoordinates) {

            int[] randomCoord = generateRandomCoordOnBoard();

            if (cells[randomCoord[0]][randomCoord[1]] != Cell.MINE) {
                cells[randomCoord[0]][randomCoord[1]] = Cell.MINE;
                validRandomCellCounter++;
            }
        }
        return cells;
    }

    private Cell[][] generateSafeBoard() {
        Cell[][] safeBoard = new Cell[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                safeBoard[i][j] = Cell.SAFE;
            }
        }
        return safeBoard;
    }

    private int[] generateRandomCoordOnBoard() {
        return new int[]{generateRandomNumber(ROWS), generateRandomNumber(COLS)};
    }

    private int generateRandomNumber(int upper) {
        Random random = new Random();
        return random.nextInt(upper);
    }

    @Override
    public boolean isGameOver(int[] move) {
        return getCellState(move[0], move[1]) == Cell.MINE;
    }
}
