package minesweeper.controller;

import minesweeper.io.Printer;
import minesweeper.model.board.Point;

public class Move {

    private Point point;
    private String action;

    public Move(String line) {
        try {
            String[] arrLine = getArrLine(line);
            this.point = parseToPointFromString(arrLine);
            this.action = arrLine[2];
        } catch (IllegalArgumentException e) {
            Printer.println(e.getMessage());
        }
    }

    private Point parseToPointFromString(String[] line) {
        try {
            return new Point(
                    Integer.parseInt(line[0]),
                    Integer.parseInt(line[1])
            );
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Error! The input must be integers. ");
        }
    }

    private String[] getArrLine(String line) {

        if (line.isEmpty()) {
            throw new IllegalArgumentException("Error! The line is empty. ");
        }
        String[] arrLine = line.split(" ");
        if (arrLine.length != 3) {
            throw new IllegalArgumentException("Error! The input must contain two numbers separated by a space. ");
        }
        return arrLine;
    }

    public Point getPoint() {
        return point;
    }

    public String getAction() {
        return action;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}


