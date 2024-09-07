package minesweeper.controller;

import minesweeper.model.board.MinesweeperBoard;

public class GameEngine {

    private final MinesweeperBoard board;

    public GameEngine(int numberOfMines){
        this.board = new MinesweeperBoard(9, 9, numberOfMines);
    }

    public void displayBoard(){
        board.displayBoard();
    }

}
