package dssc.project.freedom;

public class Stone {

    private Colour colour;
    private boolean isLive = false;

    public static Stone createEmpty() {
        return new Stone(Colour.NONE);
    }

    public Stone(Colour colour) {
        this.colour = colour;
    }

    // functions to remove
    public boolean isWhite() {
        return colour == Colour.WHITE;
    }

    public boolean isBlack() {
        return colour == Colour.BLACK;
    }
    //

    public boolean isOfColour(Colour colour) { return this.colour.equals(colour); }

    public boolean isNotColored(){ return this.isOfColour(Colour.NONE); }

    public Colour getColour() {
        return colour;
    }

    public boolean isLive() {
        return isLive;
    }

    public void makeLive(){ this.isLive = true; }

    public void makeDead(){ this.isLive = false; }

    public boolean makeColoured(Colour colour) {
        if (this.colour.equals(Colour.NONE)) {
            this.colour = colour;
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public String toString() {
        return colour.toString();
    }

}
