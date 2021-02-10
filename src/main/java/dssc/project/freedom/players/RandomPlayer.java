package dssc.project.freedom.players;

import dssc.project.freedom.Position;

public class RandomPlayer implements Player{

    public RandomPlayer() {}
    public Position move() {
        return Position.at(1, 1);
    }
}
