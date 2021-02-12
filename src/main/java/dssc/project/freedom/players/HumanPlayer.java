package dssc.project.freedom.players;

import dssc.project.freedom.Colour;
import dssc.project.freedom.Position;

import java.util.Scanner;

import static dssc.project.freedom.Utility.getInteger;

public class HumanPlayer extends Player{

    public HumanPlayer(String name, Colour colour) {
        super(name, colour);
    }

    @Override
    public Position getPlayerPosition() {
        Scanner in = new Scanner(System.in);
        Position current;
        System.out.println(" Enter the x and y coordinates of the stone:");
        int x = getInteger(in);
        int y = getInteger(in);
        current = Position.at(x, y);
        return current;
    }
}
