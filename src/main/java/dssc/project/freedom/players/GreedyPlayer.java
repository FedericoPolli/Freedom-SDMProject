package dssc.project.freedom.players;

import dssc.project.freedom.Position;

public class GreedyPlayer implements Player{

    public GreedyPlayer() {}
    public Position move() {
        return Position.at(1, 1);
    }
}
