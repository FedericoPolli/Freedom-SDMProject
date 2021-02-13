package dssc.project.freedom;

public enum Direction {

    /** The horizontal direction. */
    RIGHT(1, 0),
    /** The vertical direction. */
    UP(0, 1),
    /** The main diagonal direction. */
    UP_MAIN_DIAGONAL(1, 1),
    /** The off diagonal direction. */
    UP_ANTI_DIAGONAL(-1, 1),
    /** The horizontal direction. */
    LEFT(-1, 0),
    /** The vertical direction. */
    DOWN(0, -1),
    /** The main diagonal direction. */
    DOWN_MAIN_DIAGONAL(-1, -1),
    /** The off diagonal direction. */
    DOWN_ANTI_DIAGONAL(1, -1);

    public final int x;
    public final int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }
}