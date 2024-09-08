package minesweeper.controller;

import minesweeper.io.IOHandler;
import minesweeper.io.Printer;
import minesweeper.model.board.Cell;
import minesweeper.model.board.MinesweeperBoard;

import java.util.InputMismatchException;

public class GameEngine {

    private final MinesweeperBoard board;

    public GameEngine(int numberOfMines) {
        this.board = new MinesweeperBoard(9, 9, numberOfMines);
    }

    public void displayBoard() {
        board.displayBoard();
    }

    public boolean isGameOver() {
        return board.isGameOver();
    }

    public void move() {
        int[] move = new int[]{-1, -1};
        try {
            move = getIntMove();
        } catch (IllegalArgumentException e) {
            Printer.println(e.getMessage());
        } finally {
            handleMove(move);
        }
    }

    private int[] getIntMove() {
        try {
            String lineMove = promptLineMove();
            String[] arrStringLineMove = getArrStringLineMove(lineMove);
            return parseToIntMove(arrStringLineMove);
        } catch (IllegalArgumentException e) {
            Printer.println(e.getMessage());
            return new int[]{-1, -1};
        }
    }

    private int[] parseToIntMove(String[] arrStringLineMove) {
        try {
            return new int[]
                    {
                            Integer.parseInt(arrStringLineMove[0]),
                            Integer.parseInt(arrStringLineMove[1])
                    };
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Error! The input must be integers. ");
        }
    }

    private String[] getArrStringLineMove(String lineMove) {
        String[] arrStringLineMove = lineMove.split(" ");

        if (lineMove.isEmpty()) {
            throw new IllegalArgumentException("Error! The line is empty. ");
        }
        if (arrStringLineMove.length != 2) {
            throw new IllegalArgumentException("Error! The input must contain two numbers separated by a space. ");
        }
        return arrStringLineMove;
    }

    private String promptLineMove() {
        Printer.print("Set/delete mines marks (x and y coordinates): ");
        return IOHandler.readNextLine();
    }

    private void handleMove(int[] move) {
        if (!isMarkedCell(move)) {
            board.updateBoard(move[0], move[1], Cell.MARKED);
            displayBoard();
        } else if (isNumberCell(move)) {
            Printer.println("There is a number here!");
        } else if (isMarkedCell(move)) {
            board.updateBoard(move[0], move[1], Cell.SAFE);
            displayBoard();
        } else if (isMineCell(move)) {
            board.removeMove(move);
        }
    }

    private boolean isMarkedCell(int[] move) {
        return board.getCellState(move[0], move[1]) == Cell.MARKED;
    }

    private boolean isNumberCell(int[] move) {
        Cell cellState = board.getCellState(move[0], move[1]);
        return switch (cellState) {
            case ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT -> true;
            default -> false;
        };
    }

    private boolean isMineCell(int[] move){
        return board.getCellState(move[0], move[1]) == Cell.MINE;
    }
}
