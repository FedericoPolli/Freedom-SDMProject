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
        ++numberOfStonesPlaced;
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

    public Colour winner() {
        board.checkAllDirections();
        long whiteLiveStones = board.countLiveStones(Colour.WHITE);
        long blackLiveStones = board.countLiveStones(Colour.BLACK);
        if (whiteLiveStones > blackLiveStones) {
            System.out.println("White won with " + whiteLiveStones + " live stones against Black's " + blackLiveStones);
            return Colour.WHITE;
        } else if (blackLiveStones > whiteLiveStones) {
            System.out.println("Black won with " + blackLiveStones + " live stones against White's " + whiteLiveStones);
            return Colour.BLACK;
        }
        else {
            System.out.println("Draw: both players have the same number of live stones: " + whiteLiveStones);
            return Colour.NONE;
        }
    }

    public boolean isLastMoveConvenient(Position position, Colour colour) {
        board.checkAllDirections();
        long beforeLastMove = board.countLiveStones(colour);

        board.setAllStonesDead();
        board.updateStoneAt(position, colour);
        board.checkAllDirections();
        long afterLastMove = board.countLiveStones(colour);

        return afterLastMove >= beforeLastMove;

    }
}
