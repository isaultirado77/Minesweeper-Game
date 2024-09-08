package minesweeper.controller;

import minesweeper.io.IOHandler;
import minesweeper.io.Printer;
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

    public int[] move() {
        return new int[]{0, 0};
    }

    private int[] getIntMove() {
        try {
            String lineMove = promptLineMove();
            String[] arrStringLineMove = getArrStringLineMove(lineMove);
            int[] intMove = parseToIntMove(arrStringLineMove);
        } catch (IllegalArgumentException e) {
            Printer.println(e.getMessage());
        }
        return new int[]{0, 0};
    }

    private int[] parseToIntMove(String[] arrStringLineMove) {
        try {
            return new int[]
                    {
                            Integer.parseInt(arrStringLineMove[0]),
                            Integer.parseInt(arrStringLineMove[1])
                    };
        } catch (NumberFormatException  e) {
            throw new NumberFormatException("Error! The input must be integers. ");
        }
    }

    private String[] getArrStringLineMove(String lineMove){
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

}
