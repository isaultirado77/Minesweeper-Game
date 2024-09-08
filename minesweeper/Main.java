package minesweeper;

import minesweeper.controller.GameController;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        GameController gameController = new GameController();
        gameController.run();
    }
}
