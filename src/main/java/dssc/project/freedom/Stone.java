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
}
