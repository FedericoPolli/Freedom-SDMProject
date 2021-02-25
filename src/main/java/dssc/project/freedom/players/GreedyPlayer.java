package dssc.project.freedom.players;

import dssc.project.freedom.Utility;
import dssc.project.freedom.basis.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static dssc.project.freedom.Utility.getRandomInteger;

public class GreedyPlayer extends Player {

    private static Board board;
    private static Position previous;

    public static void updateBoardAndPreviousPosition(Board board, Position previous) {
        GreedyPlayer.board = board;
        GreedyPlayer.previous = previous;
    }

    public GreedyPlayer(String name, Colour colour) {
        super(name, colour);
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
        return Position.at(getRandomInteger(board.getBoardSize()) + 1, getRandomInteger(board.getBoardSize()) + 1);
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
            return freePositions.get(Utility.getRandomInteger(freePositions.size()));
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
