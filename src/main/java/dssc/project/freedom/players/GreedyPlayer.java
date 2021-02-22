package dssc.project.freedom.players;

import dssc.project.freedom.RandomGenerator;
import dssc.project.freedom.basis.Board;
import dssc.project.freedom.basis.Colour;
import dssc.project.freedom.basis.Direction;
import dssc.project.freedom.basis.Position;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GreedyPlayer extends Player {

    private static Board board;
    private static Position previous;
    private final RandomGenerator randomGenerator;

    public static void setPrevious(Position previous) {
        GreedyPlayer.previous = previous;
    }

    public static void setBoard(Board board) {
        GreedyPlayer.board = board;
    }

    public GreedyPlayer(String name, Colour colour, RandomGenerator randomGenerator) {
        super(name, colour);
        this.randomGenerator = randomGenerator;
    }

    public Position getPlayerPosition() {
        if (previous == null)
            return getRandomPosition();
        List<Position> freeAdjacentPositions = board.getFreeAdjacentPositions(previous);
        if (freeAdjacentPositions.isEmpty())
            return findPositionToPlayIn(board.getFreePositions());
        else
            return findPositionToPlayIn(freeAdjacentPositions);
    }

    private Position getRandomPosition() {
        return Position.at(randomGenerator.getRandomInteger(board.getBoardSize()) + 1, randomGenerator.getRandomInteger(board.getBoardSize()) + 1);
    }

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
            return freePositions.get(randomGenerator.getRandomInteger(freePositions.size()));
        else {
            int indexOfMax = maxStonesInARowForPositions.stream().max(Comparator.naturalOrder()).get();
            return freePositionsCopy.get(maxStonesInARowForPositions.indexOf(indexOfMax));
        }
    }

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
