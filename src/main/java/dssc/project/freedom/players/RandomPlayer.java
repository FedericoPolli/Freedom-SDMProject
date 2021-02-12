package dssc.project.freedom.players;

import dssc.project.freedom.Colour;
import dssc.project.freedom.Position;

public class RandomPlayer extends Player{

    public RandomPlayer(String name, Colour colour) {
        super(name, colour);
    }
    public Position getPlayerPosition() {
        return Position.at(1, 1);
    }
}
