package dssc.project.freedom.basis;

import dssc.project.freedom.utilities.Utility;

import java.util.*;
import java.util.stream.Collectors;

import static dssc.project.freedom.basis.Position.at;
import static java.lang.System.lineSeparator;


/**
 * Class that represents the board of the {@link dssc.project.freedom.games.Game}.
 *
 * It has a set of {@link Stone}s and at the end of the game it checks
 * which are "live" and which not.
 */
public class Board {

    /** Dictionary that stores all the {@link Position}s and the corresponding {@link Stone}s in the {@link Board}. */
    private final Map<Position, Stone> board = new HashMap<>();
    /** The size of the {@link Board}. */
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
     * @param position The Position of the Stone to be retrieved.
     * @return The Stone at the given Position.
     */
    private Stone getStoneAt(Position position) {
        return board.get(position);
    }

    /**
     * Colours the {@link Stone} in the given {@link Position} in the {@link Board},
     * colouring it with the given {@link Colour}.
     * @param position The Position of the Stone to be coloured.
     * @param colour The Colour to be assigned to the Stone.
     */
    public void colourStoneAt(Position position, Colour colour) {
        getStoneAt(position).makeOfColour(colour);
    }

    /**
     * Counts the "live" {@link Stone}s of the given {@link Colour}.
     * @param colour The considered Colour.
     * @return The number of "live" Stones for that Colour.
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
     * Finds all the free {@link Position}s that are adjacent to the given {@link Position}
     * and returns them in a {@link List}.
     * @param position The Position to be checked.
     * @return A List with the free Positions adjacent to the given one.
     */
    public List<Position> getFreeAdjacentPositions(Position position) {
        return getFreePositions().stream()
                .filter(p -> p.isInAdjacentPositions(position))
                .collect(Collectors.toList());
    }

    /**
     * Checks if at least one of the {@link Position}s adjacent to the given one is free.
     * @param position The Position to be checked.
     * @return true if at least one Positions adjacent to the given one is free, false otherwise.
     */
    public boolean areAdjacentPositionFree(Position position) {
        return !getFreeAdjacentPositions(position).isEmpty();
    }

    /**
     * Checks if a {@link Stone} has already been placed in the {@link Position}
     * taken as input. This is done by checking the {@link Colour} of the {@link
     * Stone}: it has not been placed only if the {@link Colour} is <code>NONE</code>.
     * @param position The Position to be checked.
     * @return true if the Stone has already a Colour, false otherwise.
     */
    public boolean stoneIsAlreadyPlacedAt(Position position) {
        return !getStoneAt(position).isOfColour(Colour.NONE);
    }

    /**
     * Checks in the whole board for occurrences of exactly four {@link Stone}s of
     * the same {@link Colour} in a row, in the {@link Direction} specified by the input,
     * and makes the {@link Stone}s "live".
     * @param direction The direction in which to move.
     */
    private void check4StonesInDirection(Direction direction) {
        for (Position current : board.keySet()) {
            Position previous = current.moveInDirectionWithStep(direction, -1);
            if (positionIsInsideTheBoard(previous) && areStonesOfSameColourAt(current, previous))
                continue;
            if (countStonesInRow(direction, current) == 4)
                setStonesInRowOf4Live(direction, current);
        }
    }

    /**
     * Checks if the input {@link Position} is inside the {@link Board} or not.
     * @param position The Position to be checked.
     * @return true if the Position is in the Board, false otherwise.
     */
    public boolean positionIsInsideTheBoard(Position position) {
        return board.containsKey(position);
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
     * @param direction     The direction in which to move.
     * @param startingPosition The starting Position.
     * @return The number of Stones of the same Colour adjacent to the given one.
     */
    private int countStonesInRow(Direction direction, Position startingPosition) {
        int stonesInRow = 1;
        for (int step = 1; step < 5; ++step) {
            Position current = startingPosition.moveInDirectionWithStep(direction, step);
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
     * in the {@link Direction} given by the input.
     * @param direction The direction in which to move.
     * @param startingPosition The starting Position.
     */
    private void setStonesInRowOf4Live(Direction direction, Position startingPosition) {
        for (int step = 0; step < 4; ++step) {
            Position current = startingPosition.moveInDirectionWithStep(direction, step);
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

    /**
     * Returns the number of {@link Stone}s of the same {@link Colour}
     * of the given one in a certain row starting from the given {@link Stone}
     * for all the {@link Direction}s.
     * @param position The Position of the given Stone.
     * @param colour The Colour of the given Stone.
     * @return The maximum number of Stones of the same Colour in a row.
     */
    public List<Integer> getNumberOfStonesInRowForAllDirections(Position position, Colour colour) {
        colourStoneAt(position, colour);
        List<Integer> maximumNumberOfStonesInARow = new ArrayList<>();
        for (Direction dir : Direction.values())
            maximumNumberOfStonesInARow.add(countStonesInRow(dir, position));
        colourStoneAt(position, Colour.NONE);
        return maximumNumberOfStonesInARow;
    }

    /**
     * Returns the maximum number of {@link Stone}s in a row of a given
     * {@link Colour}, starting form a given {@link Position} among all the
     * possible {@link Direction}s.
     * @param position The Position on which to perform the search.
     * @param colour The Colour of the given Stone.
     * @return The maximum number of Stones of the same Colour in a row for the given Position.
     */
    public Integer getMaximumNumberOfStonesInARow(Position position, Colour colour) {
        return getNumberOfStonesInRowForAllDirections(position, colour)
                .stream()
                .max(Comparator.naturalOrder())
                .get();
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