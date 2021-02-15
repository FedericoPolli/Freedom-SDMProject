package dssc.project.freedom.players;

import dssc.project.freedom.Board;
import dssc.project.freedom.Colour;
import dssc.project.freedom.Direction;
import dssc.project.freedom.Position;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

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
        List<Position> freePositions = board.getAdjacentPositions(previous);
        if (freePositions.isEmpty())
            return findPositionToPlayIn(board.getFreePositions());
        else
            return findPositionToPlayIn(freePositions);
    }

    private Position getRandomPosition() {
        Random random = new Random();
        int x = random.nextInt(board.boardSize) + 1;
        int y = random.nextInt(board.boardSize) + 1;
        return Position.at(x, y);
    }

    private Position findPositionToPlayIn(List<Position> freePositions) {
        List<Position> freePositionsCopy = new ArrayList<>(freePositions);
        List<Integer> indexes = new ArrayList<>();
        for (Position p : freePositions) {
            board.updateStoneAt(p, colour);
            int i = 0;
            for (Direction dir : Direction.values()) {
                i = Math.max(i, board.countStonesInRow(dir, p));
                if (i == 5)
                    break;
            }
            board.updateStoneAt(p, Colour.NONE);
            if (i == 4)
                return p;
            if (i == 5)
                freePositionsCopy.remove(p);
            else
                indexes.add(i);
        }
        if (freePositionsCopy.isEmpty())
            return freePositions.get(new Random().nextInt(freePositions.size()));
        else {
            int j = indexes.stream().max(Comparator.naturalOrder()).get();
            return freePositionsCopy.get(indexes.indexOf(j));
        }

    }
}
