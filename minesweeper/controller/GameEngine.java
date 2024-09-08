package minesweeper.controller;

import minesweeper.io.IOHandler;
import minesweeper.io.Printer;
import minesweeper.model.board.Cell;
import minesweeper.model.board.MinesweeperBoard;

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
            return;
        }

        handleMove(move);
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
        if (!isValidMove(move)) {
            Printer.println("Invalid move. Please enter coordinates between 1 and 9.");
            return;
        }

        int[] validMove = getValidMove(move);

        if (isNumberCell(validMove)) {
            Printer.println("There is a number here!");
            return;
        }

        if (isMineCell(validMove)) {
            board.removeMine();
            if (!isGameOver()) {
                toggleMark(validMove);
                displayBoard();
            }
            return;
        }

        toggleMark(validMove);
        displayBoard();
    }

    private void toggleMark(int[] validMove) {
        if (isMarkedCell(validMove)) {
            board.updateBoard(validMove[0], validMove[1], Cell.SAFE);
        } else {
            board.updateBoard(validMove[0], validMove[1], Cell.MARKED);
        }
    }


    private boolean isValidMove(int[] move) {
        int moveX = move[0];
        int moveY = move[1];
        return moveX >= 1 && moveX <= 9 && moveY >= 1 && moveY <= 9;
    }

    private int[] getValidMove(int[] move) {
        int moveX = move[0];
        int moveY = move[1];
        return new int[]{moveY - 1, moveX - 1};
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

    private boolean isMineCell(int[] move) {
        return board.getCellState(move[0], move[1]) == Cell.MINE;
    }
}
