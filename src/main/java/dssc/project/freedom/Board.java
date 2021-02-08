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

    /** Dictionary that stores all the {@link Position}s and the corresponding {@link Stone}s in the {@link Board}. */
    private final Map<Position, Stone> board = new HashMap<>();

    /**
     * Class constructor. Creates a {@link Board} of the given size, then fills
     * it with empty {@link Stone}s.
     * @param boardSize The size of the Board.
     */
    public Board(int boardSize) {
        for (int i = 1; i <= boardSize; ++i) {
            for (int j = 1; j <= boardSize; ++j) {
                board.put(at(i, j), Stone.createEmpty());
            }
        }
    }

    /**
     * Returns the {@link Stone} in the {@link Board} at the given {@link Position}.
     * @param p The Position of the Stone to be retrieved.
     * @return The Stone at the given Position.
     */
    public Stone getStoneAt(Position p) {
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
                .filter(p -> p.isInSurroundingPositions(pos))
                .filter(p -> getStoneAt(p).isOfColour(Colour.NONE))
                .findAny()
                .isEmpty();
    }

    /**
     * Checks in the whole board for occurrences of exactly four {@link Stone}s of
     * the same {@link Colour} in a row, in the direction specified by the inputs
     * <code>xDir</code> and <code>yDir</code>, and makes the {@link Stone}s "live".
     * @param xDir The x-coordinate of the direction in which to move.
     * @param yDir The y-coordinate of the direction in which to move.
     */
    public void check4InDirection(int xDir, int yDir){
        for (Position current : board.keySet()){
            Position previous = at(current.getX() - xDir, current.getY() - yDir);
            if (!positionIsNotInTheBoard(previous) && arePositionsOfSameColour(current, previous)) {
                continue;
            }
            int counter = countStonesInRow(xDir, yDir, current);
            if (counter == 4){
                setStonesInRowOf4Live(xDir, yDir, current);
            }
        }
    }

    /**
     * Checks if the next {@link Position} is of the same {@link Colour} as the current one.
     * @param current The current Position.
     * @param next The Position next to the current one.
     * @return true if the next Position is of the same Colour as the current one, false otherwise.
     */
    private boolean arePositionsOfSameColour(Position current, Position next) {
        return getStoneAt(next).isOfSameColourAs(getStoneAt(current));
    }

    /**
     * Counts the number of {@link Stone}s of the same {@link Colour} in a row
     * starting from the <code>current</code> {@link Position}, according to the
     * direction specified by the inputs <code>xDir</code> and <code>yDir</code>.
     * @param xDir  The x-coordinate of the direction in which to move.
     * @param yDir  The y-coordinate of the direction in which to move.
     * @param current The starting Position.
     * @return The number of Stones of the same Colour adjacent to the given one.
     */
    private int countStonesInRow(int xDir, int yDir, Position current) {
        int counter = 1;
        for (int i = 1; i < 5; ++i) {
            Position next = at(current.getX() + i * xDir, current.getY() + i * yDir);
            if (positionIsNotInTheBoard(next) || !arePositionsOfSameColour(current, next))
                break;
            else
                counter++;
        }
        return counter;
    }

    /**
     * Checks if the input {@link Position} is in the {@link Board} or not.
     * @param p The Position to be checked.
     * @return true if the Position is in the Board, false otherwise.
     */
    private boolean positionIsNotInTheBoard(Position p) {
        return !board.containsKey(p);
    }

    /**
     * Sets as "live" the {@link Stone}s that are part of a row of exactly four {@link Stone}s
     * of the same {@link Colour}, starting from the {@link Position} <code>current</code>,
     * in the direction given by the input <code>xDir</code> and <code>yDir</code>.
     * @param xDir The x-coordinate of the direction in which to move.
     * @param yDir The y-coordinate of the direction in which to move.
     * @param current The starting Position.
     */
    private void setStonesInRowOf4Live(int xDir, int yDir, Position current) {
        getStoneAt(current).changeLiveStatusTo(true);
        for (int i = 1; i < 4; ++i){
            Position next = at(current.getX() + i * xDir, current.getY() + i * yDir);
            getStoneAt(next).changeLiveStatusTo(true);
        }
    }

    /**
     * Checks the whole {@link Board} in all directions (horizontal, vertical and
     * diagonal) to find the {@link Stone}s which are part of a row of exactly
     * four {@link Stone}s of the same {@link Colour}, then makes them "live".
     */
    public void checkBoardAndMakeStonesLive(){
        // Check tiles horizontally
        check4InDirection(1, 0);
        // Check tiles vertically
        check4InDirection(0, 1);
        // Check tiles diagonally
        check4InDirection(1 ,1);
        check4InDirection(-1 , 1);
    }

    /**
     * Prints the {@link Board} in a graphical way.
     */
    public void printBoard(){
        PrintWriter printWriter = new PrintWriter(System.out,true);
        char white = '\u26AA';
        char black = '\u26AB';
        printWriter.println("White: " + white + "\tBlack: " + black);
    }
}
