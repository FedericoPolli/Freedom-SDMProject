package dssc.project.freedom.basis;

/**
 * Enum that represents the colours of a {@link Stone}.
 *
 * They can be white, black or empty, thus without colour.
 * It is used to avoid working with a {@link String} all the time.
 */
public enum Colour {

    /** White {@link Stone}s. */
    WHITE,
    /** Black {@link Stone}s. */
    BLACK,
    /** Empty {@link Stone}s. */
    NONE
}