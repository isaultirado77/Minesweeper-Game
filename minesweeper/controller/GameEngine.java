package minesweeper.controller;

import minesweeper.model.board.MinesweeperBoard;

public class GameEngine {

    private final MinesweeperBoard board;

    public GameEngine(){
        this.board = new MinesweeperBoard(9, 9);
    }

    public void displayBoard(){
        board.displayBoard();
    }

}
