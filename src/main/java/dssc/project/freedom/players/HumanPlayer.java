package dssc.project.freedom.players;

import dssc.project.freedom.basis.Colour;
import dssc.project.freedom.basis.Position;

import java.util.Scanner;

import static dssc.project.freedom.utilities.Utility.getInteger;

/**
 * Class that represents a human {@link Player}.
 */
public class HumanPlayer extends Player {

    /**
     * Static member needed to get the user's input.
     */
    private static Scanner in;

    /**
     * Sets the {@link Scanner} to the one in input.
     * @param in The new Scanner.
     */
    public static void setScanner(Scanner in) {
        HumanPlayer.in = in;
    }

    /**
     * Class constructor.
     * @param name The name of this {@link Player}.
     * @param colour The colour of this {@link Player}.
     */
    public HumanPlayer(String name, Colour colour) {
        super(name, colour);
    }

    /**
     * Asks the user for the coordinates in which to play in, and returns the corresponding position.
     * @return The position in which to play.
     */
    @Override
    public Position getPlayerPosition() {
        System.out.println(" Enter the x and y coordinates of the stone:");
        int x = getInteger(in);
        int y = getInteger(in);
        return Position.at(x, y);
    }

    /**
     * Asks the user if he wants to do the last move.
     * @return true if the user does not want to do last move, false otherwise.
     */
    @Override
    public boolean doesNotWantToDoLastMove() {
        System.out.println("Do you want to do the last move? (0 = no, 1 = yes)");
        int d = getInteger(in);
        return d == 0;
    }
}
