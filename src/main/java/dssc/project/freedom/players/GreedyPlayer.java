package dssc.project.freedom.players;

import dssc.project.freedom.basis.Board;
import dssc.project.freedom.basis.Colour;
import dssc.project.freedom.basis.Position;
import dssc.project.freedom.utilities.RandomGenerator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Class that represents a greedy computer {@link Player}.
 *
 * It chooses its moves by adopting a greedy strategy.
 */
public class GreedyPlayer extends Player {

    /** Represents the board of the game being played by this player. */
    private static Board board;
    /** Represents the position of the previous move in the game being played by this player. */
    private static Position previous;
    /** The generator of random numbers. */
    private final RandomGenerator randomGenerator;

    /**
     * Updates the {@link dssc.project.freedom.basis.Board} of the {@link dssc.project.freedom.games.Game}
     * and the previous {@link dssc.project.freedom.basis.Position}.
     * @param board    The Board of the Game
     * @param previous The last played-in Position.
     */
    public static void updateBoardAndPreviousPosition(Board board, Position previous) {
        GreedyPlayer.board = board;
        GreedyPlayer.previous = previous;
    }


    /**
     * Class constructor. It takes the name and the {@link Colour} of the {@link Player}
     * and a generator of random numbers.
     * @param name            The name of this Player.
     * @param colour          The colour of this Player.
     * @param randomGenerator The generator of random numbers.
     */
    public GreedyPlayer(String name, Colour colour, RandomGenerator randomGenerator) {
        super(name, colour);
        this.randomGenerator = randomGenerator;
    }

    /**
     * Computes the positions adjacent to the previous one, chooses one among
     * them and returns it.
     * @return The position that this {@link GreedyPlayer} decided to play in.
     */
    @Override
    public Position getPlayerPosition() {
        if (previous == null)
            return getRandomPosition();
        List<Position> freeAdjacentPositions = board.getFreeAdjacentPositions(previous);
        if (freeAdjacentPositions.isEmpty())
            return findPositionToPlayIn(board.getFreePositions());
        else
            return findPositionToPlayIn(freeAdjacentPositions);
    }

    /**
     * Computes a random {@link dssc.project.freedom.basis.Position} in the
     * {@link dssc.project.freedom.basis.Board} and returns it.
     * @return The Position to play in.
     */
    private Position getRandomPosition() {
        return Position.at(randomGenerator.getRandomInteger(board.getBoardSize()) + 1,
                randomGenerator.getRandomInteger(board.getBoardSize()) + 1);
    }

    /**
     * For each {@link Position} in input, it finds the most convenient one i.e. the one
     * that forms the longest row of at most four {@link dssc.project.freedom.basis.Stone}s.
     * @param freePositions The positions to perform the search in.
     * @return The best Position it can find.
     */
    private Position findPositionToPlayIn(List<Position> freePositions) {
        List<Integer> maxStonesInARowForPositions = new ArrayList<>();
        List<Position> freePositionsCopy = new ArrayList<>(freePositions);
        for (Position p : freePositions) {
            int maximumNumberOfStonesInARow = board.getMaximumNumberOfStonesInARow(p, colour);
            if (maximumNumberOfStonesInARow == 4)
                return p;
            else if (maximumNumberOfStonesInARow == 5)
                freePositionsCopy.remove(p);
            else if (oppositePlayerMakesRowOf4(p))
                return p;
            else
                maxStonesInARowForPositions.add(maximumNumberOfStonesInARow);
        }
        if (freePositionsCopy.isEmpty())
            return freePositions.get(randomGenerator.getRandomInteger(freePositions.size()));
        else {
            int indexOfMax = maxStonesInARowForPositions.stream().max(Comparator.naturalOrder()).get();
            return freePositionsCopy.get(maxStonesInARowForPositions.indexOf(indexOfMax));
        }
    }

    private boolean oppositePlayerMakesRowOf4(Position p) {
        Colour oppositeColour = (colour == Colour.WHITE) ? Colour.BLACK : Colour.WHITE;
        return board.getMaximumNumberOfStonesInARow(p, oppositeColour) == 4;
    }
}