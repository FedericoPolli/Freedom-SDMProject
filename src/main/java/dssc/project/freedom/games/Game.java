package dssc.project.freedom.games;

import dssc.project.freedom.basis.*;

import java.util.List;

/**
 * Class that represents the game itself.
 */
public abstract class Game {

    /** The board on which the game is played. */
    protected final Board board;
    /** Auxiliary field to know the previously played position. */
    protected Position previous = null;

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
        board.colourStoneAt(current, colour);
        previous = current;
    }


    /**
     * Checks if the move of the player is valid. A move is valid if the {@link Position}
     * in which the {@link Stone} is placed is inside the {@link Board}, if it is not on
     * an already occupied {@link Position} and if it is adjacent to the previously played
     * {@link Stone}, in the case in which the adjacent {@link Position}s of the previously
     * played {@link Stone} are not all occupied, otherwise the player has the freedom
     * of placing it in any non-occupied {@link Position}.
     * The function raises an {@link Exception} if the move is not valid with a message
     * explaining the reason why it is not valid.
     * @param current The Position of the Stone placed in the move that has to be checked.
     * @throws Exception Exception with an error message regarding the invalid move.
     */
    public void isMoveValid(Position current) throws Exception {
        if (!board.positionIsInsideTheBoard(current))
            throw new Exception("The position is not inside the board!");
        if (board.stoneIsAlreadyPlacedAt(current))
            throw new Exception("The position is already occupied!");
        if (anyPositionAdjacentToPreviousOneIsFree() && !(current.isInAdjacentPositions(previous)))
            throw new Exception("The position is not adjacent to the previous one!");
    }

    /**
     * Checks if the {@link Stone}'s {@link Position} is adjacent to the previously played one.
     * @return true if the Stone is adjacent to the previously played one, false otherwise.
     */
    private boolean anyPositionAdjacentToPreviousOneIsFree() {
        return previous != null && board.areAdjacentPositionFree(previous);
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
        List<Integer> numberOfStonesInRowForAllDirections = board.getNumberOfStonesInRowForAllDirections(position, colour);
        long numberOfFourRows = numberOfStonesInRowForAllDirections.stream().filter(x -> x == 4).count();
        long numberOfFiveRows = numberOfStonesInRowForAllDirections.stream().filter(x -> x == 5).count();
        return numberOfFourRows >= numberOfFiveRows;
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
        printWinner(whiteLiveStones, blackLiveStones);
        if (whiteLiveStones > blackLiveStones) {
            return Colour.WHITE;
        } else if (blackLiveStones > whiteLiveStones) {
            return Colour.BLACK;
        } else {
            return Colour.NONE;
        }
    }

    /**
     * Helper function that has to be implemented in the subclass that handles the printings.
     * @param whiteLiveStones The number of live Stones of the white player.
     * @param blackLiveStones The number of live Stones of the black player.
     */
    protected void printWinner(int whiteLiveStones, int blackLiveStones) {
    }
}