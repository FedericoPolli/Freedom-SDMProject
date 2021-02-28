package dssc.project.freedom.basis;

/**
 * Class that represents a stone on the board's tile in the game.
 *
 * It has a {@link Colour} and a "live" status: a stone is "live" if it is
 * part of exactly four stones in a row (horizontal, vertical or diagonal).
 */
public class Stone {

    /** The {@link Colour} of the {@link Stone}. */
    private Colour colour;
    /** Status of the {@link Stone}: stores if the {@link Stone} is "live" (true) or not (false). */
    private boolean liveStatus = false;

    /**
     * Class constructor. Creates a {@link Stone} of the given {@link Colour}.
     * @param colour The Colour of the Stone.
     */
    public Stone(Colour colour) {
        this.colour = colour;
    }

    /**
     * Getter for the {@link Colour} of this {@link Stone}.
     * @return The Colour of this Stone.
     */
    public Colour getColour() {
        return this.colour;
    }

    /**
     * Checks if this {@link Stone} is of the given {@link Colour}.
     * @param colour The Colour to be used in the comparison.
     * @return true if the Stone is of the same Colour as the input, false otherwise.
     */
    public boolean isOfColour(Colour colour) {
        return this.colour.equals(colour);
    }

    /**
     * Checks if this {@link Stone} is of the same {@link Colour} as the given {@link Stone}.
     * @param other The Stone to be used in the comparison.
     * @return true if the Colour is the same, false otherwise.
     */
    public boolean isOfSameColourAs(Stone other) {
        return this.colour.equals(other.colour);
    }

    /**
     * Changes the {@link Colour} of this {@link Stone} to the given one.
     * @param colour The new Colour for this Stone.
     */
    public void makeOfColour(Colour colour) {
        this.colour = colour;
    }

    /**
     * Getter for the "live" status of this {@link Stone}.
     * @return true if this Stone is "live", false otherwise.
     */
    public boolean isLive() {
        return liveStatus;
    }

    /**
     * Changes the "live" status of this {@link Stone} to the value in input.
     * @param status The status to which update the "live" status of this Stone.
     */
    public void changeLiveStatusTo(boolean status) {
        liveStatus = status;
    }

    /**
     * Returns a {@link String} representation of this object.
     * @return A String representing this object.
     */
    @Override
    public String toString() {
        return colour.toString();
    }
}