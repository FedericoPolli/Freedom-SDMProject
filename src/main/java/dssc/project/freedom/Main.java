package dssc.project.freedom;

import dssc.project.freedom.games.CommandLineGame;

import java.util.Scanner;

/**
 * Class that controls the execution of the game.
 */
public class Main {

    /** {@link Scanner} to read the input from the terminal. */
    private final static Scanner in = new Scanner(System.in);

    /** Main of the project. */
    public static void main(String[] args) {
        do {
            System.out.println("Game Start:");
            System.out.print("Enter the board size (minimum 4): ");
            int boardSize = getBoardSize();
            char player1 = getTypeOfPlayer();
            String name1 = "ComputerPlayer1";
            if (player1 == 'h')
                name1 = getHumanPlayerName();
            char player2 = getTypeOfPlayer();
            String name2 = "ComputerPlayer2";
            if (player2 == 'h')
                name2 = getHumanPlayerName();
            playGameWithGivenSettings(boardSize, player1, name1, player2, name2);
            System.out.print("Do you want to start a new game with new settings? (0 = no, 1 = yes) ");
        } while (Utility.getInteger(in) != 0);
        in.close();
    }

    /**
     * Asks the user to enter the size of the {@link dssc.project.freedom.basis.Board}.
     * @return The sie of the Board.
     */
    private static int getBoardSize() {
        int boardSize;
        boolean flag;
        do {
            flag = false;
            boardSize = Utility.getInteger(in);
            if (boardSize < 4) {
                flag = true;
                System.out.print("The size of the board must be an integer >= 4! ");
            }
        } while (flag);
        return boardSize;
    }

    /**
     * Asks to enter a {@link dssc.project.freedom.players.Player} with which
     * the user wants to play the {@link dssc.project.freedom.games.Game}:
     * <ul>
     *     <li>'h': {@link dssc.project.freedom.players.HumanPlayer}, so the user has to play. </li>
     *     <li>'r': {@link dssc.project.freedom.players.RandomPlayer}. </li>
     *     <li>'g': {@link dssc.project.freedom.players.GreedyPlayer}. </li>
     * </ul>
     * @return The type of the chosen Player.
     */
    private static char getTypeOfPlayer() {
        System.out.print("Choose the player: 'h' for a Human Player, 'r' for a Random Player or 'g' for a Greedy Player. ");
        char player;
        do {
            player = in.next().charAt(0);
            in.nextLine();  // throws away the \n not consumed by next()
            if (player == 'h' || player == 'r' || player == 'g')
                break;
            System.out.print("Wrong type of player! Reenter it: ");
        } while (true);
        return player;
    }

    /**
     * Asks the user to enter the name of the selected
     * {@link dssc.project.freedom.players.HumanPlayer}.
     * @return The name of the HumanPlayer.
     */
    private static String getHumanPlayerName() {
        System.out.print("Enter the name of the player: ");
        return in.nextLine();
    }

    /**
     * Keeps on playing {@link dssc.project.freedom.games.Game}s with the same
     * settings specified in input (eventually with the names of the
     * {@link dssc.project.freedom.players.Player}s switched if the user wants)
     * until the user decides to quit the {@link dssc.project.freedom.games.Game}.
     * @param boardSize The size of the Board.
     * @param player1   The white Player.
     * @param name1     The name of the white Player.
     * @param player2   The black Player.
     * @param name2     The name of the black Player.
     */
    private static void playGameWithGivenSettings(int boardSize, char player1, String name1, char player2, String name2) {
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
            } else
                break;
        } while (true);
    }
}