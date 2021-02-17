package dssc.project.freedom.players;

import dssc.project.freedom.Colour;
import dssc.project.freedom.Position;

import java.util.Scanner;

import static dssc.project.freedom.Utility.getInteger;

public class HumanPlayer extends Player {

    private static Scanner in = new Scanner(System.in);

    public static void setScanner(Scanner in) {
        HumanPlayer.in = in;
    }

    public HumanPlayer(String name, Colour colour) {
        super(name, colour);
    }

    @Override
    public Position getPlayerPosition() {
        System.out.println(" Enter the x and y coordinates of the stone:");
        int x = getInteger(in);
        int y = getInteger(in);
        return Position.at(x, y);
    }

    @Override
    public boolean doesNotWantToDoLastMove() {
        System.out.println("Do you want to do the last move? (0 = no, 1 = yes)");
        int d = getInteger(in);
        return d == 0;
    }
}
