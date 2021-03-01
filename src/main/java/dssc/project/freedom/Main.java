package dssc.project.freedom;

import java.util.Scanner;

/**
 * Class that contains the main of the project.
 */
public class Main {

    /**
     * Main of the project.
     * @param args Possible arguments.
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        GameHandler gameHandler = new GameHandler(in);
        gameHandler.handleGame();
        in.close();
    }
}