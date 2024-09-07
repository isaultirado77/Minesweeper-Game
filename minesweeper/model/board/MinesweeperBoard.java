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
        board = generateGradedBoard(numberOfMines);
    }

    private Cell[][] generateGradedBoard(int numberOfMines) {
        Cell[][] cells = generateRandomizedBoard(numberOfMines);

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (cells[i][j] == Cell.SAFE) {
                    cells[i][j] = gradeCell(cells, i, j);
                }
            }
        }

        return cells;
    }

    private Cell gradeCell(Cell[][] cells, int row, int col) {
        int countX = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < ROWS && j >= 0 && j < COLS) {
                    if (cells[i][j] == Cell.MINE) {
                        countX++;
                    }
                }
            }
        }
        if (countX == 0){
            return Cell.SAFE;
        }
        return Cell.getCellFromString(String.valueOf(countX));
    }

    private Cell[][] generateRandomizedBoard(int numberOfMines) {
        Cell[][] cells = generateSafeBoard();

        int validRandomCellCounter = 0;
        while (validRandomCellCounter < numberOfMines) {

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
