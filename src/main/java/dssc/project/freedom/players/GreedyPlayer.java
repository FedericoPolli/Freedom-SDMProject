package dssc.project.freedom.players;

import dssc.project.freedom.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static dssc.project.freedom.Utility.getRandomInteger;

public class GreedyPlayer extends Player{

    private static Board board;
    private static Position previous;

    public static void setPrevious(Position previous) {
        GreedyPlayer.previous = previous;
    }

    public static void setBoard(Board board) {
        GreedyPlayer.board = board;
    }

    public GreedyPlayer(String name, Colour colour) {
        super(name, colour);
    }

    public Position getPlayerPosition() {
        if (previous == null) {
            return getRandomPosition();
        }
        List<Position> freeAdjacentPositions = board.getAdjacentPositions(previous);
        if (freeAdjacentPositions.isEmpty())
            return findPositionToPlayIn(board.getFreePositions());
        else
            return findPositionToPlayIn(freeAdjacentPositions);
    }

    public Position getRandomPosition() {
        return Position.at(getRandomInteger(board.getBoardSize()) + 1, getRandomInteger(board.getBoardSize()) + 1);
    }

    private Position findPositionToPlayIn(List<Position> freePositions) {
        List<Position> freePositionsCopy = new ArrayList<>(freePositions);
        List<Integer> maxStonesInARowForPositions = new ArrayList<>();
        for (Position p : freePositions) {
            board.updateStoneAt(p, colour);
            int maximumNumberOfStonesInARow = getMaximumNumberOfStonesInARow(p);
            board.updateStoneAt(p, Colour.NONE);
            if (maximumNumberOfStonesInARow == 4)
                return p;
            if (maximumNumberOfStonesInARow == 5)
                freePositionsCopy.remove(p);
            else
                maxStonesInARowForPositions.add(maximumNumberOfStonesInARow);
        }
        if (freePositionsCopy.isEmpty())
            return freePositions.get(Utility.getRandomInteger(freePositions.size()));
        else {
            int indexOfMax = maxStonesInARowForPositions.stream().max(Comparator.naturalOrder()).get();
            return freePositionsCopy.get(maxStonesInARowForPositions.indexOf(indexOfMax));
        }

    }

    private int getMaximumNumberOfStonesInARow(Position p) {
        int maximumNumberOfStonesInARow = 0;
        for (Direction dir : Direction.values()) {
            maximumNumberOfStonesInARow = Math.max(maximumNumberOfStonesInARow, board.countStonesInRow(dir, p));
            if (maximumNumberOfStonesInARow == 5)
                break;
        }
        return maximumNumberOfStonesInARow;
    }
}
