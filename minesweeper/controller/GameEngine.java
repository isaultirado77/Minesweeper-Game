package minesweeper.controller;

import minesweeper.io.IOHandler;
import minesweeper.io.Printer;
import minesweeper.model.board.Cell;
import minesweeper.model.board.MinesweeperBoard;
import minesweeper.model.board.Point;

public class GameEngine {

    private final MinesweeperBoard board;
    private boolean isGameOver;

    public GameEngine(int numberOfMines) {
        this.board = new MinesweeperBoard(9, 9, numberOfMines);
        this.isGameOver = false;
    }

    public void displayBoard() {
        board.displayBoard();
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void move() {
    }

    private String promptLineMove() {
        Printer.print("Set/delete mines marks (x and y coordinates): ");
        return IOHandler.readNextLine();
    }

    private void handleMove(Move move) {
        String action = move.getAction().toLowerCase();
        switch (action) {
            case "mine":
                mineAction(move.getPoint());
                break;
            case "free":
                break;
            default:
                Printer.println("Error! The action isn't valid. ");
        }
    }

    private void mineAction(Point point) {
        toggleMark(point);
    }

    private void toggleMark(Point point) {
        if (isMarkedCell(point)) {
            board.updateBoard(point.x(), point.y(), Cell.SAFE);
        } else if (!isMarkedCell(point)) {
            board.updateBoard(point.x(), point.y(), Cell.MARKED);
        }
    }

    private boolean isValidPointMove(Point point) {
        int moveX = point.x();
        int moveY = point.y();
        return moveX >= 1 && moveX <= 9 && moveY >= 1 && moveY <= 9;
    }

    private Point getValidPointMove(Point point) {
        int moveX = point.x();
        int moveY = point.y();
        return new Point(moveY - 1, moveX - 1);
    }

    private boolean isMarkedCell(Point point) {
        return board.isMarkedCell(point);
    }

    private boolean isNumberCell(Point point) {
        return board.isNumberCell(point);
    }

    private boolean isMineCell(Point point) {
        return board.isMineCell(point);
    }

    private boolean isListOfMinesEmpty() {
        return board.isListOfMinesEmpty();
    }
}
