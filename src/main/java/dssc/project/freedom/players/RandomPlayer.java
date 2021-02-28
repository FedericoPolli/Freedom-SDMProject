package dssc.project.freedom.players;

import dssc.project.freedom.RandomGenerator;
import dssc.project.freedom.basis.Colour;
import dssc.project.freedom.basis.Position;

/**
 * Class that represents a {@link Player} which plays with random moves.
 *
 * The class {@link dssc.project.freedom.games.Game} or a subclass of it
 * are in charge of checking if the move is valid, if it is not, another
 * move is asked to this {@link Player}.
 */
public class RandomPlayer extends Player {

    /** The size of the {@link dssc.project.freedom.basis.Board}. */
    private final int boardSize;
    private final RandomGenerator randomGenerator;

    /**
     * Class constructor.
     * @param name      The name of the player.
     * @param colour    The Colour of the player.
     * @param boardSize The size of the Board.
     */
    public RandomPlayer(String name, Colour colour, int boardSize, RandomGenerator randomGenerator) {
        super(name, colour);
        this.boardSize = boardSize;
        this.randomGenerator = randomGenerator;
    }

    /**
     * It randomly chooses a position inside the {@link dssc.project.freedom.basis.Board}
     * in which this {@link Player} will play its {@link dssc.project.freedom.basis.Stone}.
     * @return The position in which to play.
     */
    @Override
    public Position getPlayerPosition() {
        return Position.at(randomGenerator.getRandomInteger(boardSize) + 1, randomGenerator.getRandomInteger(boardSize) + 1);
    }
}