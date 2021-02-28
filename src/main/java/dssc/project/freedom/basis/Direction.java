package dssc.project.freedom.basis;

/**
 * Enum that represents the direction in which to move.
 *
 * It is used to avoid working with two integers like `(1, 0)` to move right.
 */
public enum Direction {

    /** The right direction. */
    RIGHT(1, 0),
    /** The left direction. */
    LEFT(-1, 0),
    /** The up direction. */
    UP(0, 1),
    /** The down direction. */
    DOWN(0, -1),
    /** The up main diagonal direction. */
    UP_MAIN_DIAGONAL(1, 1),
    /** The down main diagonal direction. */
    DOWN_MAIN_DIAGONAL(-1, -1),
    /** The up anti-diagonal direction. */
    UP_ANTI_DIAGONAL(-1, 1),
    /** The down anti-diagonal direction. */
    DOWN_ANTI_DIAGONAL(1, -1);

    /** The x-coordinate of the direction. */
    private final int x;
    /** The y-coordinate of the direction. */
    private final int y;

    /**
     * Constructor. It creates a direction given a pair of integers.
     * @param x The x-coordinate of the direction.
     * @param y The y-coordinate of the direction.
     */
    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Getter for the x-coordinate.
     * @return The x-coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Getter for the y-coordinate.
     * @return The y-coordinate.
     */
    public int getY() {
        return y;
    }
}