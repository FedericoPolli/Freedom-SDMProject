package dssc.project.freedom.players;

import dssc.project.freedom.Colour;
import dssc.project.freedom.Position;

import java.util.Scanner;

import static dssc.project.freedom.Utility.getInteger;

public class HumanPlayer extends Player {

    private static Scanner in = new Scanner(System.in);

    public HumanPlayer(String name, Colour colour) {
        super(name, colour);
    }

    public static void setScanner(Scanner in) {
        HumanPlayer.in = in;
    }

    @Override
    public Position getPlayerPosition() {
        Position current;
        System.out.println(" Enter the x and y coordinates of the stone:");
        int x = getInteger(in);
        int y = getInteger(in);
        current = Position.at(x, y);
        return current;
    }

    @Override
    public boolean doesNotWantToDoLastMove() {
        System.out.println("Do you want to do the last move? (0 = yes, 1 = no)");
        int d = getInteger(in);
        return d != 0;
    }
}
