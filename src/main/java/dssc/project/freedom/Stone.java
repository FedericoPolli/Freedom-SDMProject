package dssc.project.freedom;

/**
 * Class that represents a stone on the board's tile in the game.
 *
 * It has a {@link Colour} and at the end of the game is made "live" if it is
 * part of 4 adjacent stones (horizontally, vertically or diagonally).
 * All the Stones are stored in the {@link Board} of the {@link Game}.
 */
public class Stone {

    /** The {@link Colour} of the {@link Stone}. */
    private Colour colour;
    /** Status of the {@link Stone}: stores if the {@link Stone} is "live" (true) or not (false). */
    private boolean liveStatus = false;

    /**
     * Creates an empty {@link Stone}, thus a {@link Stone} with {@link Colour} equal to {@link Colour#NONE}.
     * @return The Stone with Colour NONE created.
     */
    public static Stone createEmpty() {
        return new Stone(Colour.NONE);
    }

    /**
     * Class constructor. Creates a {@link Stone} of the given {@link Colour}.
     * @param colour The Colour of the Stone.
     */
    public Stone(Colour colour) {
        this.colour = colour;
    }

    /**
     * Checks if this {@link Stone} is of the given {@link Colour}.
     * @param colour The Colour to be used in the comparison.
     * @return true if the Stone is of the same Colour of the input, false otherwise.
     */
    public boolean isOfColour(Colour colour) { return this.colour.equals(colour); }

    /**
     * Checks if this {@link Stone} is of the same {@link Colour} of the given {@link Stone}.
     * @param s The Stone to be used in the comparison.
     * @return true if the Colour is the same, false otherwise.
     */
    public boolean isOfSameColourAs(Stone s) {
        return this.colour.equals(s.colour);
    }

    /**
     * Changes the {@link Colour} of this {@link Stone} to the given one.
     * @param colour The new Colour for this Stone.
     */
    public void makeOfColour(Colour colour) {
        this.colour = colour;
    }

    /**
     * Getter for the "live" status of the {@link Stone}.
     * @return true if the Stone is "live", false otherwise.
     */
    public boolean isLive() {
        return liveStatus;
    }

    /**
     * Changes the "live" status of this {@link Stone} to the value in input.
     * @param status The status to which update the "live" status of this Stone.
     */
    public void changeLiveStatusTo(boolean status){ liveStatus = status; }

    /**
     * Returns a {@link String} representation of this object.
     * @return A String representing this object.
     */
    @Override
    public String toString() {
        return colour.toString();
    }
}
