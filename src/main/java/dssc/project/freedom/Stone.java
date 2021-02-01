package dssc.project.freedom;

public class Stone {

    private Colour colour;
    private boolean liveStatus = false;

    public static Stone createEmpty() {
        return new Stone(Colour.NONE);
    }

    public Stone(Colour colour) {
        this.colour = colour;
    }

    public boolean isOfColour(Colour colour) { return this.colour.equals(colour); }

    public void makeColoured(Colour colour) {
        this.colour = colour;
    }

    public boolean isOfSameColourAs(Stone s) {
        return this.colour.equals(s.colour);
    }

    public boolean isLive() {
        return liveStatus;
    }

    /**
     * This function changes the variable @ref liveStatus to the value of the input.
     *
     * @param status The status to which update the variable @ref liveStatus.
     */
    public void changeLiveStatusTo(boolean status){ liveStatus = status; }

    @Override
    public String toString() {
        return colour.toString();
    }

}
