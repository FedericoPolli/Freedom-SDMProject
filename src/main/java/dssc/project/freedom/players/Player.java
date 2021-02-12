package dssc.project.freedom.players;

import dssc.project.freedom.Colour;
import dssc.project.freedom.Position;

public abstract class Player {

    private final String name;
    private  final Colour colour;

    public Player(String name, Colour colour) {
        this.name = name;
        this.colour = colour;
    }

    public abstract Position getPlayerPosition();

    public String getName() {
        return name;
    }

    public Colour getColour() {
        return colour;
    }


}
