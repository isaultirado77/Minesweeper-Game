package minesweeper.model.board;

import minesweeper.io.Printer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MinesweeperBoard extends GenericBoard {

    private final List<Point> listOfMines;
    private final int numberOfMines;
    private int markedMines = 0;

    public MinesweeperBoard(int rows, int cols, int numberOfMines) {
        super(rows, cols);
        initBoard();
        this.numberOfMines = numberOfMines;
        listOfMines = generateRandomPointsOnBoard(numberOfMines);
    }

    @Override
    public void initBoard() {
        board = generateSafeBoard();
    }


    @Override
    public void displayBoard() {
        Printer.println("\n |123456789|");
        Printer.println("-|---------|");
        for (int i = 0; i < ROWS; i++) {
            Printer.print((i + 1) + "|");
            for (int j = 0; j < COLS; j++) {
                Printer.print(board[i][j].getSeed());
            }
            Printer.println("|");
        }
        Printer.println("-|---------|\n");
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

    public int countAdjacentMines(int row, int col) {
        int countX = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < ROWS && j >= 0 && j < COLS) {
                    final Point point = new Point(i, j);
                    if (listOfMines.contains(point)) {
                        countX++;
                    }
                }
            }
        }
        return countX;
    }

    public int countFreeCells() {
        int count = 0;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                final Point point = new Point(i, j);
                if (isFreeCell(point) || isNumberCell(point)) {
                    count++;
                }
            }
        }
        return count;
    }

    private List<Point> generateRandomPointsOnBoard(int numberOfMines) {
        List<Point> randomPoints = new ArrayList<>(numberOfMines);

        int validRandomCellCounter = 0;
        while (validRandomCellCounter < numberOfMines) {

            Point randomPoint = generateRandomCoordOnBoard();

            if (!randomPoints.contains(randomPoint)) {
               // System.out.println("MINE: " + randomPoint.toString());
                randomPoints.add(randomPoint);
                validRandomCellCounter++;
            }
        }
        return randomPoints;
    }

    private Point generateRandomCoordOnBoard() {
        return new Point(generateRandomNumber(ROWS), generateRandomNumber(COLS));
    }

    private int generateRandomNumber(int upper) {
        Random random = new Random();
        return random.nextInt(upper);
    }

    public boolean isMarkedCell(Point point) {
        return getCellState(point.x(), point.y()) == Cell.MARKED;
    }

    public boolean isNumberCell(Point point) {
        Cell cellState = getCellState(point.x(), point.y());
        return switch (cellState) {
            case ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT -> true;
            default -> false;
        };
    }

    public boolean isMineCell(Point point) {
        return listOfMines.contains(point);
    }

    public boolean isSafeCell(Point point) {
        return getCellState(point.x(), point.y()) == Cell.SAFE;
    }

    public boolean isFreeCell(Point point) {
        return getCellState(point.x(), point.y()) == Cell.FREE;
    }

    public List<Point> getListOfMines() {
        return listOfMines;
    }

    public void markMine() {
        markedMines++;
    }

    public void unMarkMine() {
        markedMines--;
    }

    public int getNumberOfMines() {
        return numberOfMines;
    }

    public int getMarkedMines() {
        return markedMines;
    }

}
