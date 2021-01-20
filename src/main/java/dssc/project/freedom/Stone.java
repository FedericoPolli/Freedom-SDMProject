package dssc.project.freedom;

public class Stone {
    private Colour colour;
    private boolean isLive = false;

    public Stone(Colour colour) {
        this.colour = colour;
    }

    public boolean isWhite() {
        return colour == Colour.WHITE;
    }

    public boolean isBlack() {
        return colour == Colour.BLACK;
    }

    public boolean isLive() {
        return isLive;
    }

    public boolean makeColoured(Colour colour) {
        if (this.colour.equals(Colour.NONE)) {
            this.colour = colour;
            return true;
        }
        else {
            return false;
        }
    }

}
