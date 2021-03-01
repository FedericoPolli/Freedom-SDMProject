package dssc.project.freedom.players;

import dssc.project.freedom.basis.Colour;
import dssc.project.freedom.basis.Position;
import dssc.project.freedom.utilities.RandomGenerator;

/**
 * Class that represents a random computer {@link Player}.
 *
 * It plays with random moves.
 */
public class RandomPlayer extends Player {

    /** The size of the {@link dssc.project.freedom.basis.Board}. */
    private final int boardSize;
    /** The generator of random numbers. */
    private final RandomGenerator randomGenerator;

    /**
     * Class constructor.
     * @param name            The name of the Player.
     * @param colour          The Colour of the Player.
     * @param boardSize       The size of the Board.
     * @param randomGenerator The generator of random numbers.
     */
    public RandomPlayer(String name, Colour colour, int boardSize, RandomGenerator randomGenerator) {
        super(name, colour);
        this.boardSize = boardSize;
        this.randomGenerator = randomGenerator;
    }

    /**
     * It randomly chooses a {@link Position} inside the {@link dssc.project.freedom.basis.Board}
     * in which this {@link Player} will put its {@link dssc.project.freedom.basis.Stone}.
     * @return The position in which to play.
     */
    @Override
    public Position getPlayerPosition() {
        return Position.at(randomGenerator.getRandomInteger(boardSize) + 1,
                           randomGenerator.getRandomInteger(boardSize) + 1);
    }
}