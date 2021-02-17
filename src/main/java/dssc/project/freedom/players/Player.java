package dssc.project.freedom.players;

import dssc.project.freedom.basis.Colour;
import dssc.project.freedom.basis.Position;

public abstract class Player {

    private final String name;
    protected final Colour colour;

    public Player(String name, Colour colour) {
        this.name = name;
        this.colour = colour;
    }

    public String getName() {
        return name;
    }

    public Colour getColour() {
        return colour;
    }

    public boolean doesNotWantToDoLastMove() {
        return true;
    }

    public abstract Position getPlayerPosition();
}
