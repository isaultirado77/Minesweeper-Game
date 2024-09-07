package minesweeper.model.board;

import java.util.ArrayList;
import java.util.Random;

public class MinesweeperBoard extends GenericBoard {

    public MinesweeperBoard(int rows, int cols) {
        super(rows, cols);
    }

    @Override
    public void initBoard() {
        int totalCells = ROWS * COLS;
        int amountOfMines = (int) totalCells / 2;
        board = generateRandomizedBoard(amountOfMines);
    }

    private Cell[][] generateRandomizedBoard(int amountOfCoordinates) {
        Cell[][] cells = generateSafeBoard();
        ArrayList<int[]> randomCoords = generateRandomCoords(amountOfCoordinates);
        for (int[] coord : randomCoords) {
            cells[coord[0]][coord[1]] = Cell.MINE;
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

    private ArrayList<int[]> generateRandomCoords(int amountOfCoordinates) {
        ArrayList<int[]> randomCoords = new ArrayList<>();
        for (int i = 0; i < amountOfCoordinates; i++) {
            int[] randomCoord = generateRandomCoordOnBoard();
            randomCoords.add(randomCoord);
        }
        return randomCoords;
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
