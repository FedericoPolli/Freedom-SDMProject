package dssc.project.freedom;

public class Game {

    private Board board;
    private final int boardSize;
    private int numberOfStonesPlaced = 0;
    private Position previous = null;

    public Game(int boardSize) {
        this.boardSize = boardSize;
        this.board = new Board(boardSize);
    }

    public void play(Position current, Colour colour) {
        board.updateStoneAt(current, colour);
        previous = current;

    }

    public boolean isMoveValid(Position current) {
        // check if the position is inside the board
        if (isPositionNotValid(current))
            return false;

        // check if the stone is already coloured
        if (! board.getStoneAt(current).isNotColored()) {
            return false;
        }

        // check if the new stone is adjacent to the previous one
        if (previous != null && !board.areAdjacentPositionOccupied(previous)) {
            return current.isInSurroundingPositions(previous);
        }
        return true;
    }

    private boolean isPositionNotValid(Position current) {
        return current.getX() < 1 || current.getX() > boardSize || current.getY() < 1 || current.getY() > boardSize;
    }
}
