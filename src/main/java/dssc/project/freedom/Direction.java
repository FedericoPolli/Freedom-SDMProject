package dssc.project.freedom;

public enum Direction {

    /** The horizontal direction. */
    HORIZONTAL(1, 0),
    /** The vertical direction. */
    VERTICAL(0, 1),
    /** The main diagonal direction. */
    MAIN_DIAGONAL(1, 1),
    /** The off diagonal direction. */
    OFF_DIAGONAL(-1, 1);
    public final int x;
    public final int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }
}