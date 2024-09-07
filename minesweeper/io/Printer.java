package minesweeper.io;

/** Printer is a utility class that simplifies printing to the console. */
public class Printer {

    /** Prints a string to the console without a newline.
     * @param string the string to print. */
    public static void print(String string) {
        System.out.print(string);
    }

    /**
     * Prints a string to the console followed by a newline.
     * @param string the string to print. */
    public static void println(String string) {
        System.out.println(string);
    }

    /**
     * Prints a formatted string to the console.
     * @param string the format string.
     * @param insert the string to insert into the format. */
    public static void printf(String string, String insert) {
        System.out.printf(string, insert);
    }
}

