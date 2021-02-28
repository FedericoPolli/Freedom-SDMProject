package dssc.project.freedom.basis;

import dssc.project.freedom.utilities.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static dssc.project.freedom.basis.Position.at;
import static java.lang.System.lineSeparator;


/**
 * Class that represents the board of the game.
 *
 * It has a set of {@link Stone}s and at the end of the game it checks
 * which are "live" and which not.
 */
public class Board {

    /** Dictionary that stores all the {@link Position}s and the corresponding {@link Stone}s in the {@link Board}. */
    private final Map<Position, Stone> board = new HashMap<>();
    /** The size of the board. */
    private final int boardSize;

    /**
     * Class constructor. Creates a {@link Board} of the given size, then fills
     * it with empty {@link Stone}s.
     * @param boardSize The size of the Board.
     */
    public Board(int boardSize) {
        this.boardSize = boardSize;
        for (int i = 1; i <= boardSize; ++i)
            for (int j = 1; j <= boardSize; ++j)
                board.put(at(i, j), new Stone(Colour.NONE));
    }

    /**
     * Getter for the size of this {@link Board}.
     * @return The size of this Board.
     */
    public int getBoardSize() {
        return boardSize;
    }

    /**
     * Returns the {@link Stone} in the {@link Board} at the given {@link Position}.
     * @param p The Position of the Stone to be retrieved.
     * @return The Stone at the given Position.
     */
    private Stone getStoneAt(Position p) {
        return board.get(p);
    }

    /**
     * Updates a {@link Stone} in a given {@link Position} in the {@link Board},
     * colouring it with the given {@link Colour}.
     * @param p The Position of the Stone to be updated.
     * @param c The Colour to be assigned to the Stone.
     */
    public void updateStoneAt(Position p, Colour c) {
        getStoneAt(p).makeOfColour(c);
    }

    /**
     * Counts the "live" {@link Stone}s of the player of the given {@link Colour}.
     * @param colour The Colour of the player.
     * @return The number of "live" Stones for that player.
     */
    public int countLiveStones(Colour colour) {
        return (int) board.values()
                .stream()
                .filter(s -> s.isOfColour(colour))
                .filter(Stone::isLive)
                .count();
    }

    /**
     * Finds all the free {@link Position}s in the {@link Board} and returns them in a {@link List}.
     * @return A List with all the free Positions.
     */
    public List<Position> getFreePositions() {
        return board.keySet()
                .stream()
                .filter(p -> getStoneAt(p).isOfColour(Colour.NONE))
                .collect(Collectors.toList());
    }

    /**
     * Finds all the free {@link Position}s that are adjacent to the given {@link Position}.
     * @param pos The Position to be checked.
     * @return The free Positions adjacent to the given one.
     */
    public List<Position> getFreeAdjacentPositions(Position pos) {
        return getFreePositions().stream()
                .filter(p -> p.isInAdjacentPositions(pos))
                .collect(Collectors.toList());
    }

    /**
     * Checks if all the {@link Position}s adjacent to the given one are occupied.
     * @param pos The Position to be checked.
     * @return true if all the Positions adjacent to the given one are occupied, false otherwise.
     */
    public boolean areAdjacentPositionFree(Position pos) {
        return !getFreeAdjacentPositions(pos).isEmpty();
    }

    /**
     * Checks if a {@link Stone} has already been placed in the {@link Position}
     * taken as input. This is done by checking the {@link Colour} of the {@link
     * Stone}: it has not been placed only if the {@link Colour} is <code>NONE</code>.
     * @param current The Position to be checked.
     * @return true if the Stone has already a Colour, false otherwise.
     */
    public boolean stoneIsAlreadyPlacedAt(Position current) {
        return !getStoneAt(current).isOfColour(Colour.NONE);
    }

    /**
     * Checks in the whole board for occurrences of exactly four {@link Stone}s of
     * the same {@link Colour} in a row, in the direction specified by the input
     * {@link Direction}, and makes the {@link Stone}s "live".
     * @param dir The direction in which to move.
     */
    private void check4StonesInDirection(Direction dir) {
        for (Position current : board.keySet()) {
            Position previous = current.moveInDirectionWithStep(dir, -1);
            if (positionIsInsideTheBoard(previous) && areStonesOfSameColourAt(current, previous))
                continue;
            if (countStonesInRow(dir, current) == 4)
                setStonesInRowOf4Live(dir, current);
        }
    }

    /**
     * Checks if the input {@link Position} is inside the {@link Board} or not.
     * @param p The Position to be checked.
     * @return true if the Position is in the Board, false otherwise.
     */
    public boolean positionIsInsideTheBoard(Position p) {
        return board.containsKey(p);
    }

    /**
     * Checks if the {@link Stone} in the next {@link Position} is of the same
     * {@link Colour} as the {@link Stone} in the current one.
     * @param current The current Position.
     * @param next    The Position next to the current one.
     * @return true if the next Position is of the same Colour as the current one, false otherwise.
     */
    private boolean areStonesOfSameColourAt(Position current, Position next) {
        return getStoneAt(next).isOfSameColourAs(getStoneAt(current));
    }

    /**
     * Counts the number of {@link Stone}s of the same {@link Colour} in a row
     * starting from the <code>startingPosition</code> {@link Position}, according to the
     * direction specified by the input {@link Direction}.
     * @param dir     The direction in which to move.
     * @param startingPosition The starting Position.
     * @return The number of Stones of the same Colour adjacent to the given one.
     */
    private int countStonesInRow(Direction dir, Position startingPosition) {
        int stonesInRow = 1;
        for (int i = 1; i < 5; ++i) {
            Position current = startingPosition.moveInDirectionWithStep(dir, i);
            if (positionIsInsideTheBoard(current) && areStonesOfSameColourAt(startingPosition, current))
                stonesInRow++;
            else
                break;
        }
        return stonesInRow;
    }

    /**
     * Sets as "live" the {@link Stone}s that are part of a row of exactly four {@link Stone}s
     * of the same {@link Colour}, starting from the {@link Position} <code>startingPosition</code>,
     * in the direction given by the input <code>xDir</code> and <code>yDir</code>.
     * @param dir     The direction in which to move.
     * @param startingPosition The starting Position.
     */
    private void setStonesInRowOf4Live(Direction dir, Position startingPosition) {
        for (int i = 0; i < 4; ++i) {
            Position current = startingPosition.moveInDirectionWithStep(dir, i);
            getStoneAt(current).changeLiveStatusTo(true);
        }
    }

    /**
     * Checks the whole {@link Board} in the horizontal, vertical and diagonal
     * {@link Direction}s to find the {@link Stone}s which are part of a row of
     * exactly four {@link Stone}s of the same {@link Colour}, then makes them "live".
     */
    public void checkBoardAndMakeStonesLive() {
        check4StonesInDirection(Direction.RIGHT);
        check4StonesInDirection(Direction.UP);
        check4StonesInDirection(Direction.UP_MAIN_DIAGONAL);
        check4StonesInDirection(Direction.UP_ANTI_DIAGONAL);
    }

    public List<Integer> getNumberOfStonesInRowForAllDirections(Position p, Colour colour) {
        updateStoneAt(p, colour);
        List<Integer> maximumNumberOfStonesInARow = new ArrayList<>();
        for (Direction dir : Direction.values())
            maximumNumberOfStonesInARow.add(countStonesInRow(dir, p));
        updateStoneAt(p, Colour.NONE);
        return maximumNumberOfStonesInARow;
    }

    /**
     * Returns a {@link String} representation of this object.
     * @return A String representing this object.
     */
    @Override
    public String toString() {
        String white = Utility.getWhite();
        String black = Utility.getBlack();
        String line = "  " + "+---".repeat(boardSize) + "+";
        String boardString = line + lineSeparator();
        for (int j = boardSize; j > 0; --j) {
            boardString += j + " ";
            for (int i = 1; i <= boardSize; ++i) {
                switch (getStoneAt(at(i, j)).getColour()) {
                    case WHITE -> boardString += "| " + white;
                    case BLACK -> boardString += "| " + black;
                    case NONE -> boardString += "|  ";
                }
                boardString += " ";
            }
            boardString += "|" + lineSeparator() + line + lineSeparator();
        }
        boardString += "  ";
        for (int i = 1; i <= boardSize; ++i)
            boardString += "  " + i + " ";
        boardString += " " + lineSeparator();
        return boardString;
    }
}