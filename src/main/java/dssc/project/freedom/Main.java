package dssc.project.freedom;

import java.util.Scanner;

/**
 * Class that controls the execution of the game.
 */
public class Main {

    /**
     * Main of the project.
     */
    public static void main() {
        System.out.println("Game Start:");
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the board size (minimum 4):");
        int boardSize = getBoardSize(in);
        CommandLineGame clGame = new CommandLineGame(boardSize);
        clGame.play(in);
    }

    private static int getBoardSize(Scanner in) {
        int boardSize;
        boolean flag;
        do {
            flag = false;
            boardSize = Utility.getInteger(in);
            if (boardSize < 4) {
                flag = true;
                System.out.println("The size of the board must be an integer >= 4");
            }
        } while (flag);
        return boardSize;
    }
}
