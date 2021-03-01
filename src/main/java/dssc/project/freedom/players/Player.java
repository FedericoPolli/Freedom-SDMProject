package dssc.project.freedom.players;

import dssc.project.freedom.basis.Colour;
import dssc.project.freedom.basis.Position;

/**
 * Class representing a generic player for the {@link dssc.project.freedom.games.Game}.
 */
public abstract class Player {

    /** Name of the Player. */
    private final String name;
    /** Colour of the Player. */
    protected Colour colour;

    /**
     * Class constructor. A {@link Player} has a name and a {@link Colour}.
     * @param name   The name of the Player.
     * @param colour The Colour of the Player.
     */
    public Player(String name, Colour colour) {
        this.name = name;
        this.colour = colour;
    }

    /**
     * Getter for the name of this Player.
     * @return The name of this Player.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the {@link Colour} associated to this Player.
     * @return The Colour of this Player.
     */
    public Colour getColour() {
        return colour;
    }


    /**
     * Setter for the {@link Colour} of this Player.
     * @param colour The Colour to substitute.
     */
    public void changeColour(Colour colour) {
        this.colour = colour;
    }

    /**
     * Checks whether this Player doesn't want to do the last move.
     * @return true if this Player doesn't want to do the last move, false otherwise.
     */
    public boolean doesNotWantToDoLastMove() {
        return true;
    }

    /**
     * Gets the {@link Position} in which the Player wants to place its {@link dssc.project.freedom.basis.Stone}.
     * @return The Position in which the Player wants to do its move.
     */
    public abstract Position getPlayerPosition();
}