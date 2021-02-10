package dssc.project.freedom.players;

import dssc.project.freedom.Colour;
import dssc.project.freedom.Position;

import java.util.Scanner;

import static dssc.project.freedom.Utility.getInteger;

public class HumanPlayer implements Player{

    String name;
    Colour colour;

    public HumanPlayer(String name, Colour colour) {
        this.name = name;
        this.colour = colour;
    }

    public Position move() {
        Scanner in = new Scanner(System.in);
        Position current;
        System.out.println(" Enter the x and y coordinates of the stone:");
        int x = getInteger(in);
        int y = getInteger(in);
        current = Position.at(x, y);
        return current;
    }
}
