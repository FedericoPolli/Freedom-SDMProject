package dssc.project.freedom;

/**
 * Class that represents the game itself.
 */
public class Game {

    /** The board on which the game is played. */
    private final Board board;
    /** Auxiliary field to know the previous played position. */
    private Position previous = null;

    /**
     * Class constructor. A {@link Game} has a {@link Board} on which the players play.
     * @param boardSize The size of the Board to be created.
     */
    public Game(int boardSize) {
        this.board = new Board(boardSize);
    }

    /**
     * Represents the move of a player: adds a {@link Stone} of the given {@link Colour}
     * in the given {@link Position}.
     * @param current The Position in which adding the Stone.
     * @param colour  The Colour of the Stone to be added.
     */
    public void move(Position current, Colour colour) {
        board.updateStoneAt(current, colour);
        previous = current;
        board.printBoard();;
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
        if (!board.positionIsInsideTheBoard(current))
            return false;
        if (board.stoneIsAlreadyPlacedAt(current)) {
            return false;
        }
        if (anyPositionAdjacentToPreviousOneIsFree()) {
            return current.isInAdjacentPositions(previous);
        }
        return true;
    }

    /**
     * Checks if the {@link Stone}'s {@link Position} is adjacent to the previously played one.
     * @return true if the Stone is adjacent to the previously played one, false otherwise.
     */
    private boolean anyPositionAdjacentToPreviousOneIsFree() {
        return previous != null && !board.areAdjacentPositionOccupied(previous);
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
        int whiteLiveStones = board.countLiveStones(Colour.WHITE);
        int blackLiveStones = board.countLiveStones(Colour.BLACK);
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
     * Decides if in the last move of the whole game placing the {@link Stone}
     * at the given {@link Position} is convenient or not for the player of the
     * given {@link Colour}.
     * @param position The Position of the last move.
     * @param colour   The Colour of the player.
     * @return true if placing the last Stone is convenient for the player, false otherwise.
     */
    public boolean isLastMoveConvenient(Position position, Colour colour) {
        long pointsBeforeLastMove = getPointsAndResetAllStonesDead(colour);
        board.updateStoneAt(position, colour);
        long pointsAfterLastMove = getPointsAndResetAllStonesDead(colour);
        board.updateStoneAt(position, Colour.NONE);
        return pointsAfterLastMove >= pointsBeforeLastMove;
    }

    /**
     * Computes the points of the player of the given {@link Colour} and then
     * resets all the {@link Stone}s as not "live".
     * @param colour The Colour of the player.
     * @return The number of "live" Stones of the given player.
     */
    private int getPointsAndResetAllStonesDead(Colour colour) {
        board.checkBoardAndMakeStonesLive();
        int counter = board.countLiveStones(colour);
        board.setAllStonesDead();
        return counter;
    }
}
