package dssc.project.freedom;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import static dssc.project.freedom.Position.at;


/**
 * Class that represents the board of the game.
 *
 * It has a set of {@link Stone}s and at the end of the {@link Game} it checks
 * which are "live" and which not.
 */
public class Board {

    private enum Direction {
        /** The horizontal direction. */
        HORIZONTAL(1, 0),
        /** The vertical direction. */
        VERTICAL(0, 1),
        /** The main diagonal direction. */
        MAIN_DIAGONAL(1, 1),
        /** The off diagonal direction. */
        OFF_DIAGONAL(-1, 1);
        private final int x;
        private final int y;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /** Dictionary that stores all the {@link Position}s and the corresponding {@link Stone}s in the {@link Board}. */
    private final Map<Position, Stone> board = new HashMap<>();
    private final int boardsize;

    /**
     * Class constructor. Creates a {@link Board} of the given size, then fills
     * it with empty {@link Stone}s.
     * @param boardSize The size of the Board.
     */
    public Board(int boardSize) {
        this.boardsize = boardSize;
        for (int i = 1; i <= boardSize; ++i) {
            for (int j = 1; j <= boardSize; ++j) {
                board.put(at(i, j), new Stone(Colour.NONE));
            }
        }
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
     * Sets all the {@link Stone}s of the {@link Board} as not "live".
     */
    public void setAllStonesDead(){
        board.values().forEach(value -> value.changeLiveStatusTo(false));
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
     * Checks if all the {@link Position}s adjacent to the given one are occupied.
     * @param pos The Position to be checked.
     * @return true if all the Positions adjacent to the given one are occupied, false otherwise.
     */
    public boolean areAdjacentPositionOccupied(Position pos){
        return board.keySet()
                .stream()
                .filter(p -> p.isInAdjacentPositions(pos))
                .filter(p -> getStoneAt(p).isOfColour(Colour.NONE))
                .findAny()
                .isEmpty();
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
     * the same {@link Colour} in a row, in the direction specified by the inputs
     * <code>xDir</code> and <code>yDir</code>, and makes the {@link Stone}s "live".
     * @param dir The direction in which to move.
     */
    private void check4StonesInDirection(Direction dir){
        for (Position current : board.keySet()){
            Position previous = current.moveInDirection(-dir.x, -dir.y);
            if (positionIsInsideTheBoard(previous) && areStonesOfSameColourAt(current, previous)) {
                continue;
            }
            if (countStonesInRow(dir, current) == 4){
                setStonesInRowOf4Live(dir, current);
            }
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
     * @param next The Position next to the current one.
     * @return true if the next Position is of the same Colour as the current one, false otherwise.
     */
    private boolean areStonesOfSameColourAt(Position current, Position next) {
        return getStoneAt(next).isOfSameColourAs(getStoneAt(current));
    }

    /**
     * Counts the number of {@link Stone}s of the same {@link Colour} in a row
     * starting from the <code>current</code> {@link Position}, according to the
     * direction specified by the inputs <code>xDir</code> and <code>yDir</code>.
     * @param dir  The direction in which to move.
     * @param current The starting Position.
     * @return The number of Stones of the same Colour adjacent to the given one.
     */
    private int countStonesInRow(Direction dir, Position current) {
        int counter = 1;
        for (int i = 1; i < 5; ++i) {
            Position next = current.moveInDirection(i * dir.x, i * dir.y);
            if (!positionIsInsideTheBoard(next) || !areStonesOfSameColourAt(current, next))
                break;
            else
                counter++;
        }
        return counter;
    }

    /**
     * Sets as "live" the {@link Stone}s that are part of a row of exactly four {@link Stone}s
     * of the same {@link Colour}, starting from the {@link Position} <code>current</code>,
     * in the direction given by the input <code>xDir</code> and <code>yDir</code>.
     * @param dir The direction in which to move.
     * @param current The starting Position.
     */
    private void setStonesInRowOf4Live(Direction dir, Position current) {
        getStoneAt(current).changeLiveStatusTo(true);
        for (int i = 1; i < 4; ++i){
            Position next = current.moveInDirection(i * dir.x, i * dir.y);
            getStoneAt(next).changeLiveStatusTo(true);
        }
    }

    /**
     * Checks the whole {@link Board} in all directions (horizontal, vertical and
     * diagonal) to find the {@link Stone}s which are part of a row of exactly
     * four {@link Stone}s of the same {@link Colour}, then makes them "live".
     */
    public void checkBoardAndMakeStonesLive(){
        check4StonesInDirection(Direction.HORIZONTAL);
        check4StonesInDirection(Direction.VERTICAL);
        check4StonesInDirection(Direction.MAIN_DIAGONAL);
        check4StonesInDirection(Direction.OFF_DIAGONAL);
    }

    /**
     * Prints the {@link Board} in a graphical way.
     */
    public void printBoard(){
        PrintWriter printWriter = new PrintWriter(System.out,true);
        char white = '\u26AA';
        char black = '\u26AB';
        String line = "+---".repeat(boardsize) + "+";
        printWriter.println(line);
        for (int i=boardsize; i>0; --i){
            for (int j=1; j<=boardsize; ++j){
                printWriter.print("| ");
                switch (getStoneAt(at(i,j)).getColour()){
                    case WHITE -> printWriter.print(white);
                    case BLACK -> printWriter.print(black);
                    case NONE -> printWriter.print(" ");
                }
                printWriter.print(" ");
            }
            printWriter.println("|");
            printWriter.println(line);
        }
    }
}
