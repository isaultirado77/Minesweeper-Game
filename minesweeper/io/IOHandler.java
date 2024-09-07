package minesweeper.io;

import java.io.IOException;
import java.util.Scanner;

/** IOHandler is a utility class designed to handle input/output operations for CLI-based Java applications.
 * It simplifies reading user  input and clearing the console screen.
 */
public class IOHandler {
    /** Instance os scanner used to read user input from the console.  */
    private static final Scanner scanner = new Scanner(System.in);

    /** Read the next line of the input from the console.
     * @return the next line entered by the user as a String.  */
    @SuppressWarnings("unused")
    public static String readNextLine() {
        return scanner.nextLine();
    }

    /** Reads the next line of the input from the console and attempts to parse it as an integer.
     * If the input is not a valid integer, it returns Integer.MIN_VALUE.
     * @return the parsed integer from the user input or Integer.MIN_VALUE if parse fails.
     */
    @SuppressWarnings("unused")
    public static int readInteger() {
        try {
            return Integer.parseInt(readNextLine());
        } catch (NumberFormatException e) {
            return Integer.MIN_VALUE;
        }
    }

    /** Provides access to the Scanner instance for advanced input handling if needed.
     * @return the shared Scanner instance. */
    @SuppressWarnings("unused")
    public static Scanner getScanner() {
        return scanner;
    }

    /** Clears the console screen. This method works differently depending on the OS.
     * If the operation fails the exception message is printed.   */
    @SuppressWarnings("unused")
    public static void clearConsole() {
        try {
            var clearCommand = System.getProperty("os.name").contains("Windows")
                    ? new ProcessBuilder("cmd", "/c", "cls")
                    : new ProcessBuilder("clear");
            clearCommand.inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            Printer.println(e.getMessage());
        }
    }
}
