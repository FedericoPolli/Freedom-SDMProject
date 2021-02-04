package dssc.project.freedom;

/**
 * Enum that represents the colours of a {@link Stone}.
 *
 * This {@link Enum} represents the colours of a {@link Stone}: it can be white, black
 * or without colour.
 * It is used to avoid using and working with a {@link String} all the time.
 */
public enum Colour {
    /** White {@link Stone}s player. */
    WHITE,
    /** Black {@link Stone}s player. */
    BLACK,
    /** The {@link Stone} has no {@link Colour}. */
    NONE
}
