package dssc.project.freedom;

/**
 * Class that represents the game itself.
 */
public class Game {

    /** The board on which the game is played. */
    private Board board;
    /** The dimension of the board. */
    private final int boardSize;
    /** Auxiliary field to know the previous played position. */
    private Position previous = null;

    /**
     * Class constructor. A {@link Game} has a {@link Board} on which the players play.
     * @param boardSize The size of the Board to be created.
     */
    public Game(int boardSize) {
        this.boardSize = boardSize;
        this.board = new Board(boardSize);
    }

    /**
     * Represents the move of a player: adds a {@link Stone} of the given {@link Colour}
     * in the given {@link Position}.
     * @param current The Position in which adding the Stone.
     * @param colour  The Colour of the Stone to be added.
     */
    public void play(Position current, Colour colour) {
        board.updateStoneAt(current, colour);
        previous = current;
    }

    /**
     * Checks if the move of the player is valid. A move is valid if the {@link Position}
     * in which the {@link Stone} is placed is inside the {@link Board}, if it is not on
     * an already occupied {@link Position} and if it is adjacent to the previous played
     * {@link Stone} in the case in which the adjacent {@link Position}s of the previous
     * played {@link Stone} are not all occupied, otherwise the player has the freedom
     * of placing it in any non-occupied {@link Position}.
     * @param current The Position of the Stone placed in the move that has to be checked.
     * @return true if the move of the player is valid, false otherwise.
     */
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

    /**
     * Checks if the {@link Stone}'s {@link Position} is adjacent to the one of
     * the previous played one.
     * @return true if the Stone is adjacent to the previous played one, false otherwise.
     */
    private boolean isStoneAdjacentToPreviousOne() {
        return previous != null && !board.areAdjacentPositionOccupied(previous);
    }

    /**
     * Checks if a {@link Stone} has already been placed in the {@link Position}
     * taken as input. This is done by checking the {@link Colour} of the {@link
     * Stone}: it has to be `NONE`.
     * @param current The Position to be checked.
     * @return true if the Stone has already a Colour, false otherwise.
     */
    private boolean isStoneAlreadyPlaced(Position current) {
        return !board.getStoneAt(current).isOfColour(Colour.NONE);
    }

    /**
     * Checks if the given {@link Position} is inside the {@link Board}.
     * It checks if every coordinate is between 1 and the size of the {@link Board}.
     * @param current The Position to be checked.
     * @return true if the Position is inside the Board, false otherwise.
     */
    private boolean isPositionNotInsideBoard(Position current) {
        return current.getX() < 1 || current.getX() > boardSize ||
                current.getY() < 1 || current.getY() > boardSize;
    }

    /**
     * Decides the winner of the game.
     * Checks the {@link Stone}s that are "live" and changes its status, then
     * computes the number of "live" {@link Stone}s for each player: the one
     * with more "live" {@link Stone}s wins the game.
     * @return The Colour of the player who wins the game.
     */
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

    /**
     * Decides if in the last move of the entire game placing the {@link Stone}
     * at the given {@link Position} is convenient or not for the player of the
     * given {@link Colour}.
     * @param position The Position of the last move.
     * @param colour   The Colour of the player.
     * @return true if placing the last Stone is convenient for the player, false otherwise.
     */
    public boolean isLastMoveConvenient(Position position, Colour colour) {
        long beforeLastMove = getPointsAndResetAllStonesDead(colour);
        board.updateStoneAt(position, colour);
        long afterLastMove = getPointsAndResetAllStonesDead(colour);
        board.updateStoneAt(position, Colour.NONE);
        return afterLastMove >= beforeLastMove;
    }

    /**
     * Computes the points of the player of the given {@link Colour} and then
     * resets all the {@link Stone}s as not "live".
     * @param colour The Colour of the player.
     * @return The number of "live" Stones of the given player.
     */
    private long getPointsAndResetAllStonesDead(Colour colour) {
        board.checkBoardAndMakeStonesLive();
        long counter = board.countLiveStones(colour);
        board.setAllStonesDead();
        return counter;
    }
}
