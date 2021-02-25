package dssc.project.freedom.players;

import dssc.project.freedom.Utility;
import dssc.project.freedom.basis.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static dssc.project.freedom.Utility.getRandomInteger;

/**
 *  Class that represents a greedy computer {@link Player}.
 *
 *  It chooses its moves by adopting a greedy strategy.
 */
public class GreedyPlayer extends Player {

    /**
     * Represents the board of the game being played by this player.
     */
    private static Board board;

    /**
     * Represents the position of the previous move in the game being played by this player.
     */
    private static Position previous;

    /**
     * Updates the {@link dssc.project.freedom.basis.Board}
     * and the previous {@link dssc.project.freedom.basis.Position}.
     * @param board The board of the game
     * @param previous The last played-in position.
     */
    public static void updateBoardAndPreviousPosition(Board board, Position previous) {
        GreedyPlayer.board = board;
        GreedyPlayer.previous = previous;
    }

    /**
     * Class constructor.
     * @param name The name of this {@link Player}.
     * @param colour The colour of this {@link Player}.
     */
    public GreedyPlayer(String name, Colour colour) {
        super(name, colour);
    }

    /**
     * Computes the positions adjacent to the previous one,
     * chooses one among them and returns it.
     * @return The position that this {@link GreedyPlayer} decided to play in.
     */
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
     * Computes a random {@link dssc.project.freedom.basis.Position}
     * in the {@link dssc.project.freedom.basis.Board} and returns it.
     * @return The position to play in.
     */
    private Position getRandomPosition() {
        return Position.at(getRandomInteger(board.getBoardSize()) + 1, getRandomInteger(board.getBoardSize()) + 1);
    }

    /**
     * For each position in input, it finds the most convenient one
     * i.e. the one that forms the longest row of at most four stones.
     * @param freePositions The positions to perform the search in.
     * @return The best position it can find.
     */
    private Position findPositionToPlayIn(List<Position> freePositions) {
        List<Integer> maxStonesInARowForPositions = new ArrayList<>();
        List<Position> freePositionsCopy = new ArrayList<>(freePositions);
        for (Position p : freePositions) {
            int maximumNumberOfStonesInARow = getMaximumNumberOfStonesInARow(p);
            switch (maximumNumberOfStonesInARow) {
                case 4:
                    return p;
                case 5:
                    freePositionsCopy.remove(p);
                    break;
                default:
                    maxStonesInARowForPositions.add(maximumNumberOfStonesInARow);
            }
        }
        if (freePositionsCopy.isEmpty())
            return freePositions.get(Utility.getRandomInteger(freePositions.size()));
        else {
            int indexOfMax = maxStonesInARowForPositions.stream().max(Comparator.naturalOrder()).get();
            return freePositionsCopy.get(maxStonesInARowForPositions.indexOf(indexOfMax));
        }
    }

    /**
     * It searches in all eight directions for the maximum number of stones in a row
     * that would be obtained by playing in the given position.
     * @param p The position on which to perform the search.
     * @return The maximum number of stones in a row for the given position.
     */
    private int getMaximumNumberOfStonesInARow(Position p) {
        board.updateStoneAt(p, colour);
        int maximumNumberOfStonesInARow = 0;
        for (Direction dir : Direction.values()) {
            maximumNumberOfStonesInARow = Math.max(maximumNumberOfStonesInARow, board.countStonesInRow(dir, p));
            if (maximumNumberOfStonesInARow == 5)
                break;
        }
        board.updateStoneAt(p, Colour.NONE);
        return maximumNumberOfStonesInARow;
    }
}
