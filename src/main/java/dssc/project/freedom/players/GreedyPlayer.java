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
    /** Represents the position of the previous move in the game being played by the other player. */
    private static Position previous;
    /** The generator of random numbers. */
    private final RandomGenerator randomGenerator;

    /**
     * Updates the {@link Board} of the {@link dssc.project.freedom.games.Game}
     * and the previous {@link Position}.
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
     * Computes the free {@link Position}s adjacent to the previous played one,
     * chooses one among them and returns it.
     * @return The Position that this {@link GreedyPlayer} decided to play in.
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
     * Computes a random {@link Position} in the {@link Board} and returns it.
     * @return The Position to play in.
     */
    private Position getRandomPosition() {
        return Position.at(randomGenerator.getRandomInteger(board.getBoardSize()) + 1,
                           randomGenerator.getRandomInteger(board.getBoardSize()) + 1);
    }

    /**
     * For each {@link Position} in input, it tries to find the most convenient one, i.e. the one
     * that forms the longest row of at most four {@link dssc.project.freedom.basis.Stone}s or
     * the one which hinders the other player. Otherwise it returns a random one.
     * @param freePositions The Positions in which to perform the search in.
     * @return A good Position in which to play.
     */
    private Position findPositionToPlayIn(List<Position> freePositions) {
        List<Integer> maxStonesInARowForEachPosition = new ArrayList<>();
        List<Position> nonInconvenientPosition = new ArrayList<>(freePositions);
        Position optimalPosition =
                getOptimalPositionAndUpdateLists(freePositions, maxStonesInARowForEachPosition, nonInconvenientPosition);
        if (optimalPosition != null)
            return optimalPosition;
        else if (notAllPositionsAreInconvenient(nonInconvenientPosition)) {
            int indexOfMax = maxStonesInARowForEachPosition.stream().max(Comparator.naturalOrder()).get();
            return nonInconvenientPosition.get(maxStonesInARowForEachPosition.indexOf(indexOfMax));
        } else
            return freePositions.get(randomGenerator.getRandomInteger(freePositions.size()));
    }

    /**
     * It searches, for each position, firstly if it can make a row of four, secondly if it can avoid a row of five,
     * thirdly if it can hinder the opposite Player by stopping one of his rows of three, finally it saves the
     * longest row for the given {@link Position}.
     * @param freePositions                  The Positions in which to perform the search in.
     * @param maxStonesInARowForEachPosition The list in which to save the longest rows for each Position.
     * @param nonInconvenientPositions       A copy of <code>freePositions</code> in which
     *                                       the position that result in rows of five are eliminated
     * @return The most convenient position, or null.
     */
    private Position getOptimalPositionAndUpdateLists(List<Position> freePositions, List<Integer> maxStonesInARowForEachPosition, List<Position> nonInconvenientPositions) {
        for (Position p : freePositions) {
            int maximumNumberOfStonesInARow = board.getMaximumNumberOfStonesInARow(p, colour);
            if (maximumNumberOfStonesInARow == 4)
                return p;
            else if (maximumNumberOfStonesInARow == 5)
                nonInconvenientPositions.remove(p);
            else if (oppositePlayerWouldMakeRowOf4In(p))
                return p;
            else
                maxStonesInARowForEachPosition.add(maximumNumberOfStonesInARow);
        }
        return null;
    }

    /**
     * Gets the opposite {@link Colour} and finds whether by playing in the given {@link Position}
     * it can stop a row of four for the other player.
     * @param p The position to perform the search on.
     * @return true if the opposite player would form a row of four by playing in <code>p</code>, false otherwise.
     */
    private boolean oppositePlayerWouldMakeRowOf4In(Position p) {
        Colour oppositeColour = (colour == Colour.WHITE) ? Colour.BLACK : Colour.WHITE;
        return board.getMaximumNumberOfStonesInARow(p, oppositeColour) == 4;
    }

    /**
     * Checks if there is at least a non inconvenient {@link Position}.
     * @param positionsInRowsOfAtMost4 The list of the non inconvenient Positions.
     * @return true if there is at least a non inconvenient Position, false otherwise.
     */
    private boolean notAllPositionsAreInconvenient(List<Position> positionsInRowsOfAtMost4) {
        return !positionsInRowsOfAtMost4.isEmpty();
    }
}