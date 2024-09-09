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
        String line = promptLineMove();
        Move move = new Move(line);
        handleMove(move);
    }

    private String promptLineMove() {
        Printer.print("Set/delete mines marks (x and y coordinates): ");
        return IOHandler.readNextLine();
    }

    private void handleMove(Move move) {
        Point point = getValidPointMove(move.getPoint());
        String action = move.getAction().toLowerCase();
        switch (action) {
            case "mine":
                mineAction(point);
                break;
            case "free":
                freeAction(point);
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
        } else if (isMineCell(point)) {
            board.updateBoard(point.x(), point.y(), Cell.MARKED);
            board.removeMine();
        } else if (isFreeCell(point)) {
            Printer.println("Error! There is a free cell. ");
        } else if (isSafeCell(point)) {
            board.updateBoard(point.x(), point.y(), Cell.MARKED);
        } else if (isNumberCell(point)) {
            Printer.println("Error! There is a number cell. ");
        }
    }

    private void freeAction(Point point) {
        if (isMineCell(point)) {
            isGameOver = true;
            placeTheMines();
            displayBoard();
        } else if (isSafeCell(point)) {
            discover(point);
        }
    }

    private void placeTheMines() {
        for (Point p : board.getListOfMines()) {
            board.updateBoard(p.x(), p.y(), Cell.MINE);
        }
    }

    private void discover(Point point) {
        if (isNumberCell(point) || isFreeCell(point) || isMarkedCell(point)) {
            return;
        }
        int adjacentMines = board.countAdjacentMines(point.x(), point.y());

        if (adjacentMines == 0) {
            board.updateBoard(point.x(), point.y(), Cell.FREE);

            for (Directions dir : Directions.values()) {
                Point neighbor = new Point(point.x() + dir.point.x(), point.y() + dir.point.y());
                if (isValidPoint(neighbor)) {
                    discover(neighbor);
                }
            }
        } else {
            board.updateBoard(point.x(), point.y(), Cell.values()[adjacentMines]);
        }
    }

    private boolean isValidPoint(Point point) {
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

    private boolean isSafeCell(Point point) {
        return board.isSafeCell(point);
    }

    private boolean isFreeCell(Point point) {
        return board.isFreeCell(point);
    }

    private boolean isListOfMinesEmpty() {
        return board.isListOfMinesEmpty();
    }
}

enum Directions {
    N(0, -1),
    NE(1, -1),
    E(1, 0),
    SE(1, 1),
    S(0, 1),
    SW(-1, 1),
    W(-1, 0),
    NW(-1, -1);

    final Point point;

    Directions(int x, int y) {
        point = new Point(x, y);
    }
}
