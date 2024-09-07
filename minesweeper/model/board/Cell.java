package minesweeper.model.board;

public enum Cell {
    MINE("X"),
    SAFE(".");

    private final String seed;

    Cell(String seed) {
        this.seed = seed;
    }

    public String getSeed(){
        return this.seed;
    }
}
