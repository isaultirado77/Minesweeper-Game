package minesweeper.controller;

public class GameController implements Runnable{

    GameEngine gameEngine = new GameEngine();
    @Override
    public void run() {
        gameEngine.displayBoard();
    }
}
