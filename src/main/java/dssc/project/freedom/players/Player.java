package dssc.project.freedom.players;

import dssc.project.freedom.basis.Colour;
import dssc.project.freedom.basis.Position;

/**
 * Class representing a generic Player for the Game.
 */
public abstract class Player {

    /**
     * Name of the Player.
     */
    private final String name;
    /**
     * Colour of the Player.
     */
    protected final Colour colour;

    /**
     * Class constructor. A {@link dssc.project.freedom.players.Player} has a name and an associated
     * {@link dssc.project.freedom.basis.Colour}.
     * @param name Name of the Player.
     * @param colour Colour of the Player.
     */
    public Player(String name, Colour colour) {
        this.name = name;
        this.colour = colour;
    }

    /**
     * Getter for the name of this Player.
     * @return The name of the Player.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the {@link dssc.project.freedom.basis.Colour} associated to this Player.
     * @return the Colour of the Player.
     */
    public Colour getColour() {
        return colour;
    }

    /**
     * Checks whether the Player don't want to do the last move
     * @return True
     */
    public boolean doesNotWantToDoLastMove() {
        return true;
    }

    /**
     * Get the {@link dssc.project.freedom.basis.Position} in which the Player wants to place its Stone.
     * @return
     */
    public abstract Position getPlayerPosition();
}
