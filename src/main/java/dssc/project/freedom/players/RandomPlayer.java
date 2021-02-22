package dssc.project.freedom.players;

import dssc.project.freedom.RandomGenerator;
import dssc.project.freedom.basis.Colour;
import dssc.project.freedom.basis.Position;

public class RandomPlayer extends Player {

    private final int boardSize;
    private final RandomGenerator randomGenerator;

    public RandomPlayer(String name, Colour colour, int boardSize, RandomGenerator randomGenerator) {
        super(name, colour);
        this.boardSize = boardSize;
        this.randomGenerator = randomGenerator;
    }

    public Position getPlayerPosition() {
        return Position.at(randomGenerator.getRandomInteger(boardSize) + 1, randomGenerator.getRandomInteger(boardSize) + 1);
    }
}
