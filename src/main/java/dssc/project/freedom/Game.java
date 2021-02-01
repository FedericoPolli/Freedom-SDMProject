package dssc.project.freedom;

public class Game {

    private Board board;
    private final int boardSize;
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
        if (isPositionNotInsideBoard(current))
            return false;
        if (isStoneAlreadyPlaced(current)) {
            return false;
        }
        if (isStoneAdjacentToPreviousOne()) {
            return current.isInSurroundingPositions(previous);
        }
        return true;
    }

    private boolean isStoneAdjacentToPreviousOne() {
        return previous != null && !board.areAdjacentPositionOccupied(previous);
    }

    private boolean isStoneAlreadyPlaced(Position current) {
        return ! board.getStoneAt(current).isOfColour(Colour.NONE);
    }

    private boolean isPositionNotInsideBoard(Position current) {
        return current.getX() < 1 || current.getX() > boardSize ||
                current.getY() < 1 || current.getY() > boardSize;
    }

    public Colour winner() {
        board.checkBoardAndMakeStonesLive();
        long whiteLiveStones = board.countLiveStones(Colour.WHITE);
        long blackLiveStones = board.countLiveStones(Colour.BLACK);
        if (whiteLiveStones > blackLiveStones) {
            System.out.println("White won with " + whiteLiveStones + " live stones against Black's " + blackLiveStones);
            return Colour.WHITE;
        } else if (blackLiveStones > whiteLiveStones) {
            System.out.println("Black won with " + blackLiveStones + " live stones against White's " + whiteLiveStones);
            return Colour.BLACK;
        } else {
            System.out.println("Draw: both players have the same number of live stones: " + whiteLiveStones);
            return Colour.NONE;
        }
    }

    public boolean isLastMoveConvenient(Position position, Colour colour) {
        long beforeLastMove = getPointsAndResetAllStonesDead(colour);
        board.updateStoneAt(position, colour);
        long afterLastMove = getPointsAndResetAllStonesDead(colour);
        board.updateStoneAt(position, Colour.NONE);
        return afterLastMove >= beforeLastMove;
    }

    private long getPointsAndResetAllStonesDead(Colour colour) {
        board.checkBoardAndMakeStonesLive();
        long counter = board.countLiveStones(colour);
        board.setAllStonesDead();
        return counter;
    }
}
