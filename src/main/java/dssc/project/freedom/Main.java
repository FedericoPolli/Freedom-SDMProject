package dssc.project.freedom;

import dssc.project.freedom.games.CommandLineGame;

import java.util.Scanner;

/**
 * Class that controls the execution of the game.
 */
public class Main {

    /**
     * Main of the project.
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        do {
            System.out.println("Game Start:");
            System.out.print("Enter the board size (minimum 4): ");
            int boardSize = getBoardSize(in);
            char player1 = getTypeOfPlayer(in);
            String name1;
            if (player1 == 'h')
                name1 = getHumanPlayerName(in);
            else
                name1 = "Computer Player1";
            char player2 = getTypeOfPlayer(in);
            String name2;
            if (player2 == 'h')
                name2 = getHumanPlayerName(in);
            else
                name2 = "Computer Player2";
            do {
                CommandLineGame clGame = new CommandLineGame(boardSize, player1, name1, player2, name2);
                clGame.play();
                System.out.print("Do you want to play again with the same settings? (0 = no, 1 = yes) ");
                if (Utility.getInteger(in) == 1) {
                    System.out.print("Do you want to switch colours? (0 = no, 1 = yes) ");
                    if (Utility.getInteger(in) == 1) {
                        String temp = name1;
                        name1 = name2;
                        name2 = temp;
                    }
                } else break;
            } while (true);
            System.out.print("Do you want to start a new game? (0 = no, 1 = yes) ");
        } while (Utility.getInteger(in) != 0);
        in.close();
    }

    private static int getBoardSize(Scanner in) {
        int boardSize;
        boolean flag;
        do {
            flag = false;
            boardSize = Utility.getInteger(in);
            if (boardSize < 4) {
                flag = true;
                System.out.print("The size of the board must be an integer >= 4. ");
            }
        } while (flag);
        return boardSize;
    }

    private static char getTypeOfPlayer(Scanner in) {
        System.out.print("Choose the player: h for a Human Player, r for a Random Player or g for a Greedy Player. ");
        char player;
        do {
            player = in.next().charAt(0);
            in.nextLine(); //throw away the \n not consumed by nextInt()
            if (player == 'h' || player == 'r' || player == 'g')
                break;
            System.out.print("Wrong type of player! Reenter it! ");
        } while (true);
        return player;
    }

    private static String getHumanPlayerName(Scanner in) {
        System.out.print("Enter the name of Human Player: ");
        return in.nextLine();
    }
}
