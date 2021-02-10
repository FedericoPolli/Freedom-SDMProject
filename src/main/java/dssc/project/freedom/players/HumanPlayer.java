package dssc.project.freedom.players;

import dssc.project.freedom.Colour;
import dssc.project.freedom.Position;

public class HumanPlayer implements Player {

    String name;
    Colour colour;

    public HumanPlayer(String name, Colour colour) {
        this.name = name;
        this.colour = colour;
    }

    @Override
    public void move(Position current) {

    }
}
