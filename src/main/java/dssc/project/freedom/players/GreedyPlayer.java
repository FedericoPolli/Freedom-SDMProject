package dssc.project.freedom.players;

import dssc.project.freedom.Colour;
import dssc.project.freedom.Position;

public class GreedyPlayer extends Player{

    public GreedyPlayer(String name, Colour colour) {
        super(name, colour);
    }
    public Position getPlayerPosition() {
        return Position.at(1, 1);
    }
}
