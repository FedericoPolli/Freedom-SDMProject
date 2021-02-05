package dssc.project.freedom;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that represents the board of the game.
 *
 * It has a set of {@link Stone}s and at the end of the {@link Game} it checks
 * which are "live" and which not.
 */
public class Board {

    /** Dictionary that stores all the {@link Stone}s and their {@link Position} in the {@link Board}. */
    private final Map<Position, Stone> board = new HashMap<>();

    /**
     * Class constructor. Creates a {@link Board} of the given size, then fills
     * it with empty {@link Stone}s, thus with {@link Stone}s of {@link Colour}
     * equal to {@link Colour#NONE}.
     * @param boardSize The size of the Board.
     */
    public Board(int boardSize) {
        for (int i = 1; i <= boardSize; ++i) {
            for (int j = 1; j <= boardSize; ++j) {
                board.put(Position.at(i, j), Stone.createEmpty());
            }
        }
    }

    /**
     * Finds the {@link Stone} at the {@link Position} given by the input parameters.
     * @param x The x-coordinate of the Stone's Position.
     * @param y The y-coordinate of the Stone's Position.
     * @return The Position of the Stone to be retrieved.
     */
    public Position positionAt(int x, int y) {
        return board.keySet().stream().filter(p -> p.isAt(x, y)).findAny().orElse(null);
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
     * Updates a {@link Stone} in a given {@link Position} in the {@link Board}
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
    public long countLiveStones(Colour colour) {
        return board.values().stream()
                             .filter(s -> s.isOfColour(colour))
                             .filter(Stone::isLive)
                             .count();
    }

    /**
     * Checks if the {@link Position}s adjacent to the given one are all occupied.
     * @param pos The Position to be checked.
     * @return true if the Positions adjacent to the given one are all occupied, false otherwise.
     */
    public boolean areAdjacentPositionOccupied(Position pos){
        return board.keySet().stream()
                .filter(p -> p.isInSurroundingPositions(pos))
                .filter(p -> getStoneAt(p).isOfColour(Colour.NONE))
                .findAny().isEmpty();
    }

    /**
     * Checks if there are exactly four {@link Stone}s of the same {@link Colour}
     * in the direction specified by the inputs <code>xDir</code> and <code>yDir</code>
     * and makes them "live".
     * @param xDir The x-coordinate of the direction in which to move.
     * @param yDir The y-coordinate of the direction in which to move.
     */
    public void check4InDirection(int xDir, int yDir){
        Position previous;
        for (Map.Entry<Position, Stone> entry : board.entrySet()){
            previous = positionAt(entry.getKey().getX() - xDir, entry.getKey().getY() - yDir);
            if (!isPositionNotInTheBoard(previous) && isNextPositionOfSameColourAs(entry, previous)) {
                continue;
            }
            int counter = countAdjacentEqualStones(xDir, yDir, entry);
            if (counter == 4){
                setStonesLive(xDir, yDir, entry);
            }
        }
    }

    /**
     * Checks if the next {@link Position} is of the same {@link Colour} of the current one.
     * @param entry The current Position.
     * @param next The Position next to the current one.
     * @return true if the next Position is of the same Colour of the current one, false otherwise.
     */
    private boolean isNextPositionOfSameColourAs(Map.Entry<Position, Stone> entry, Position next) {
        return getStoneAt(next).isOfSameColourAs(entry.getValue());
    }

    /**
     * Finds and counts the {@link Stone}s of the same {@link Colour} that are in
     * an adjacent {@link Position} horizontally, vertically or diagonally,
     * according to the direction specified by the inputs <code>xDir</code> and
     * <code>yDir</code>.
     * @param xDir  The x-coordinate of the direction in which to move.
     * @param yDir  The y-coordinate of the direction in which to move.
     * @param entry The starting Position.
     * @return The number of Stones of the same Colour adjacent to the given one.
     */
    private int countAdjacentEqualStones(int xDir, int yDir, Map.Entry<Position, Stone> entry) {
        int counter = 1;
        Position next;
        for (int i = 1; i < 5; ++i) {
            next = positionAt(entry.getKey().getX() + i * xDir,
                              entry.getKey().getY() + i * yDir);
            if (isPositionNotInTheBoard(next))
                break;
            if (isNextPositionOfSameColourAs(entry, next))
                counter++;
            else
                break;
        }
        return counter;
    }

    /**
     * Checks if the input {@link Position} is in the {@link Board} or not.
     * @param p The Position to be checked.
     * @return true if the Position is in the Board, false otherwise.
     */
    private boolean isPositionNotInTheBoard(Position p) {
        return p == null;
    }

    /**
     * Sets as "live" the {@link Stone}s that are part of some horizontal, vertical
     * or diagonal row of exactly four {@link Stone}s of the same {@link Colour}. The
     * direction in which performing the search is given by the input <code>xDir</code>
     * and <code>yDir</code>.
     * @param xDir The x-coordinate of the direction in which to move.
     * @param yDir The y-coordinate of the direction in which to move.
     * @param entry The starting Position.
     */
    private void setStonesLive(int xDir, int yDir, Map.Entry<Position, Stone> entry) {
        Position next;
        entry.getValue().changeLiveStatusTo(true);
        for (int i = 1; i < 4; ++i){
            // check message chain entry.getKey().getX()
            next = positionAt(entry.getKey().getX() + i * xDir,
                    entry.getKey().getY() + i * yDir);
            getStoneAt(next).changeLiveStatusTo(true);
        }
    }

    /**
     * Checks the {@link Board} in all directions (horizontally, vertically and
     * diagonally) to find the {@link Stone}s which are part of exactly four adjacent
     * {@link Stone}s of the same {@link Colour}, then makes them "live".
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
