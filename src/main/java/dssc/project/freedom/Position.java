package dssc.project.freedom;

/**
 * Class that represents a position in a grid.
 *
 * This class will represent the position of a {@link Stone} in the {@link Board} of the {@link Game}.
 */
public final class Position {

    /** x coordinate of this {@link Position}. */
    private final int x;
    /** y coordinate of this {@link Position}. */
    private final int y;

    /**
     * Creates a new {@link Position} at the specified coordinates.
     * @param x The x-coordinate of the Position to be created.
     * @param y The y-coordinate of the Position to be created.
     * @return The Position created.
     */
    public static Position at(int x, int y) {
        return new Position(x, y);
    }

    /**
     * Class constructor. Creates a {@link Position} with x coordinate `x` and
     * with y-coordinate `y`.
     *
     * @param x The x coordinate of the Position.
     * @param y The y coordinate of the Position.
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Getter for the x coordinate of this {@link Position} object.
     * @return The x coordinate of this {@link Position} object.
     */
    public int getX() {
        return x;
    }

    /**
     * Getter for the y coordinate of this {@link Position} object.
     * @return The y coordinate of this {@link Position} object.
     */
    public int getY() {
        return y;
    }

    /**
     * Checks if this {@link Position} is adjacent to the {@link Position} taken in input.
     * @param p The Position to which this Position must be adjacent.
     * @return true if this Position is adjacent to the given Position, false otherwise.
     */
    public boolean isInAdjacentPositions(Position p){
        return x <= p.x +1 && y <= p.y +1 && x >= p.x -1 && y >= p.y -1 && !this.equals(p);
    }

    /**
     * Checks if this {@link java.lang.Object} is equal to the one in input.
     * @param o The object to be compared with.
     * @return true if this {@link Object} is equal to the input {@link Object}, false otherwise.
     * @see #hashCode()
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    /**
     * Returns a hash code value for this object.
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        final int prime=31;
        int result=17;
        result = result*prime+x;
        result = result*prime+y;
        return result;
    }

    /**
     * Returns a {@link String} representation of this object.
     * @return A String representing this object.
     */
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
