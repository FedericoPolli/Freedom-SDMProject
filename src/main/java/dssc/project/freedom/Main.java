package dssc.project.freedom;

import java.util.Scanner;

public class Main {

    /** Main of the project. */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        GameHandler gameHandler = new GameHandler(in);
        gameHandler.handleGame();
        in.close();
    }
}