package minesweeper.model.board;

public enum Cell {
    MINE("X"),
    SAFE("."),
    MARKED("*"),
    FREE("/"),
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8")
    ;


    private final String seed;

    Cell(String seed) {
        this.seed = seed;
    }

    public String getSeed() {
        return this.seed;
    }

    public static Cell getCellFromString(String str) {
        for (Cell cell : Cell.values()){
            if (str.equalsIgnoreCase(cell.getSeed())) {
                return cell;
            }
        }
        return null;
    }
}
