package minesweeper.controller;

import minesweeper.io.IOHandler;
import minesweeper.io.Printer;

public class GameController implements Runnable {

    GameEngine gameEngine;

    public GameController() {
        int numberOfMines = getNumberOfMines();
        gameEngine = new GameEngine(numberOfMines);
    }

    @Override
    public void run() {
        gameEngine.displayBoard();
    }

    private int getNumberOfMines() {
        while (true) {
            int numberOfMines = promptNumberOFMines();
            if (numberOfMines != Integer.MIN_VALUE) {
                return numberOfMines;
            }
        }
    }

    private int promptNumberOFMines() {
        Printer.print("How many mines do you want on the field? ");
        return IOHandler.readInteger();
    }
}
