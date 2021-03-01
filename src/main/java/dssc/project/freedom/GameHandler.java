package dssc.project.freedom;

import dssc.project.freedom.basis.Colour;
import dssc.project.freedom.games.CommandLineGame;
import dssc.project.freedom.players.*;
import dssc.project.freedom.utilities.RandomInteger;
import dssc.project.freedom.utilities.Utility;

import java.util.Scanner;

/**
 * Class that controls the execution of the {@link CommandLineGame}.
 */
public class GameHandler {

    /** The {@link Scanner} to read the input from the terminal. */
    protected final Scanner in;

    /**
     * Class constructor.
     * @param in The Scanner.
     */
    public GameHandler(Scanner in) {
        this.in = in;
    }

    /**
     * Function that handles the execution of the {@link CommandLineGame}.
     */
    public void handleGame() {
        HumanPlayer.setScanner(in);
        do {
            System.out.println("Game Start:");
            int boardSize = getBoardSizeFromUser();
            Player player1 = createPlayer("ComputerPlayer1", Colour.WHITE, boardSize);
            Player player2 = createPlayer("ComputerPlayer2", Colour.BLACK, boardSize);
            playGameWithGivenSettings(boardSize, player1, player2);
            System.out.print("Do you want to start a new game with new settings? (0 = no, 1 = yes) ");
        } while (Utility.getInteger(in) != 0);
    }

    /**
     * Asks the user to enter the size of the {@link dssc.project.freedom.basis.Board}.
     * @return The sie of the Board.
     */
    protected int getBoardSizeFromUser() {
        System.out.print("Enter the board size (minimum 4): ");
        int boardSize;
        do {
            boardSize = Utility.getInteger(in);
            if (boardSize < 4)
                System.out.print("The size of the board must be an integer >= 4! ");
            else
                break;
        } while (true);
        return boardSize;
    }

    /**
     * Asks to the user the type of {@link Player}, and if it is a {@link HumanPlayer}
     * it also asks its name, then it build the desired {@link Player} with the given
     * name, the right {@link Colour} and the size of the {@link dssc.project.freedom.basis.Board}.
     * @param name      The name of the Player.
     * @param colour    The Colour of the Player.
     * @param boardSize The size of the Board.
     * @return A Player of the CommandLineGAme.
     */
    private Player createPlayer(String name, Colour colour, int boardSize) {
        Player player;
        char typeOfPlayer = getTypeOfPlayerFromUser();
        switch (typeOfPlayer) {
            case 'h' -> {
                name = getHumanPlayerNameFromUser();
                player = new HumanPlayer(name, colour);
            }
            case 'r' -> player = new RandomPlayer(name, colour, boardSize, new RandomInteger());
            case 'g' -> player = new GreedyPlayer(name, colour, new RandomInteger());
            default -> throw new IllegalStateException("Unexpected value: " + typeOfPlayer);
        }
        return player;
    }

    /**
     * Asks to enter the type of {@link Player} with which the user wants to play the {@link CommandLineGame}:
     * <ul>
     *     <li>'h': {@link HumanPlayer}, so the user has to play. </li>
     *     <li>'r': {@link RandomPlayer}. </li>
     *     <li>'g': {@link GreedyPlayer}. </li>
     * </ul>
     * @return The type of the chosen Player.
     */
    protected char getTypeOfPlayerFromUser() {
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
     * Asks the user to enter the name of the selected {@link HumanPlayer}.
     * @return The name of the HumanPlayer.
     */
    private String getHumanPlayerNameFromUser() {
        System.out.print("Enter the name of the player: ");
        return in.nextLine();
    }

    /**
     * Keeps on playing {@link CommandLineGame}s with the same settings specified
     * in input (eventually with the {@link Colour}s of the {@link Player}s switched
     * if the user wants) until the user decides to quit the {@link CommandLineGame}.
     * @param boardSize The size of the Board.
     * @param player1   The white Player.
     * @param player2   The black Player.
     */
    protected void playGameWithGivenSettings(int boardSize, Player player1, Player player2) {
        do {
            CommandLineGame clGame = new CommandLineGame(boardSize, player1, player2);
            clGame.playGame();
            System.out.print("Do you want to play again with the same settings? (0 = no, 1 = yes) ");
            if (Utility.getInteger(in) == 1) {
                System.out.print("Do you want to switch colours? (0 = no, 1 = yes) ");
                if (Utility.getInteger(in) == 1)
                    swapColours(player1, player2);
            } else
                break;
        } while (true);
    }

    /**
     * Swaps two {@link Player}s among them swapping their {@link Colour}s.
     * @param player1 The first Player.
     * @param player2 The second Player.
     */
    protected void swapColours(Player player1, Player player2) {
        Colour temp = player1.getColour();
        player1.changeColour(player2.getColour());
        player2.changeColour(temp);
    }
}